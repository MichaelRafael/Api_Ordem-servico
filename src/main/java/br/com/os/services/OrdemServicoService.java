package br.com.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.os.dtos.OrdemServicoDTO;
import br.com.os.enuns.Prioridade;
import br.com.os.enuns.Status;
import br.com.os.exceptions.ObjectNotFoundException;
import br.com.os.model.Cliente;
import br.com.os.model.OrdemServico;
import br.com.os.model.Tecnico;
import br.com.os.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	
	public OrdemServico findById(Long id) {
		Optional<OrdemServico> obj = ordemServicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Obejto não encontrado! ID: " + id));
	}
	
	public List<OrdemServico> findAll() {
		return ordemServicoRepository.findAll();
	}

	public OrdemServico create(@Valid OrdemServicoDTO obj) {
		return fromDTO(obj);
		
	}
	
	public OrdemServico upDate(OrdemServicoDTO objDTO) {
		findById(objDTO.getId());
		return fromDTO(objDTO);
	}
	
	private OrdemServico fromDTO(OrdemServicoDTO objDTO) {
		OrdemServico newOS = new OrdemServico();
		
		newOS.setId(objDTO.getId());
		newOS.setObservacoes(objDTO.getObservacoes());
		newOS.setPrioridade(Prioridade.toEnum(objDTO.getPrioridade().getCod()));
		newOS.setStatus(Status.toEnum(objDTO.getStatus().getCod()));
		
		if(newOS.getStatus().getCod() == 2) {
			newOS.setDataFechamento(LocalDateTime.now());
		}
		
		Tecnico tec = tecnicoService.findById(objDTO.getTecnico());
		Cliente cli = clienteService.findById(objDTO.getCliente());
		
		newOS.setTecnico(tec);
		newOS.setCliente(cli);
		
		return ordemServicoRepository.save(newOS);
	}
	
}
