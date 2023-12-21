package br.com.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.os.dtos.ClienteDTO;
import br.com.os.exceptions.DataIntegratyViolationException;
import br.com.os.exceptions.ObjectNotFoundException;
import br.com.os.model.Cliente;
import br.com.os.model.Pessoa;
import br.com.os.repository.ClienteRepository;
import br.com.os.repository.PessoaRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	

	public Cliente findById(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id " + id));
	}

	public List<Cliente> findAllClientes() {
		return clienteRepository.findAll();
	}

	
	
	
	
	public Cliente saveCliente(ClienteDTO clienteDTO) {
		if (findByCpf(clienteDTO) != null) {
			throw new DataIntegrityViolationException("CPF já cadastrado na base de dados!");
		}
		return clienteRepository
				.save(new Cliente(null, clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getTelefone()));
	}

	
	
	
	
	
	public Cliente upDateCliente(Long id, ClienteDTO objDTO) {
		Cliente oldObj = findById(id);
		
		if(findByCpf(objDTO) != null && findByCpf(objDTO).getId() != id) {
			throw new DataIntegrityViolationException("CPF já cadastrado na base de dados!");
		}
		
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		return clienteRepository.save(oldObj);
		
	}

	public void deleteCliente(Long id) {
		Cliente cliente = findById(id);
		
		if(cliente.getOrdensSevicos().size() > 0) {
			throw new DataIntegratyViolationException("Cliente possui ordens de serviço, não pode ser deletado!");
			
		}
		
		clienteRepository.deleteById(id); 
		
	}

	private Pessoa findByCpf(ClienteDTO clienteDTO) {
		Pessoa obj = pessoaRepository.findByCpf(clienteDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}


}
