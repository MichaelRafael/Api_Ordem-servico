package br.com.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.os.dtos.TecnicoDTO;
import br.com.os.exceptions.DataIntegratyViolationException;
import br.com.os.exceptions.ObjectNotFoundException;
import br.com.os.model.Pessoa;
import br.com.os.model.Tecnico;
import br.com.os.repository.PessoaRepository;
import br.com.os.repository.TecnicoRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	

	public Tecnico findById(Long id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id " + id));
	}

	public List<Tecnico> findAllTecnicos() {
		return tecnicoRepository.findAll();
	}

	public Tecnico saveTecnico(TecnicoDTO tecnicoDTO) {
		if (findByCpf(tecnicoDTO) != null) {
			throw new DataIntegrityViolationException("CPF já cadastrado na base de dados!");
		}
		return tecnicoRepository
				.save(new Tecnico(null, tecnicoDTO.getNome(), tecnicoDTO.getCpf(), tecnicoDTO.getTelefone()));
	}

	public Tecnico upDateTecnico(Long id, TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id);
		
		if(findByCpf(objDTO) != null && findByCpf(objDTO).getId() != id) {
			throw new DataIntegrityViolationException("CPF já cadastrado na base de dados!");
		}
		
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		return tecnicoRepository.save(oldObj);
		
	}

	public void deleteTecnico(Long id) {
		Tecnico tecnico = findById(id);
		
		if(tecnico.getOndensServicos().size() > 0) {
			throw new DataIntegratyViolationException("Técnico possui ordens de serviço, não pode ser deletado!");
		}
		
		tecnicoRepository.deleteById(id);
		
	}

	private Pessoa findByCpf(TecnicoDTO tecnicoDTO) {
		Pessoa obj = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}


}
