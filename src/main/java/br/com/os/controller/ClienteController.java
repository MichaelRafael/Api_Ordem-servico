package br.com.os.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.os.dtos.ClienteDTO;
import br.com.os.model.Cliente;
import br.com.os.services.ClienteService;

@CrossOrigin("*")
@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
		ClienteDTO clienteDTO = new ClienteDTO(clienteService.findById(id));
		return ResponseEntity.ok().body(clienteDTO);
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAllClientes() {
		
		List<ClienteDTO> clienteDTO = clienteService.findAllClientes().stream()
				.map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(clienteDTO);
		
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> saveCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
		Cliente cliente = clienteService.saveCliente(clienteDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> upDateCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
		ClienteDTO newObj = new ClienteDTO(clienteService.upDateCliente(id, clienteDTO));
		return ResponseEntity.status(HttpStatus.OK).body(newObj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
		clienteService.deleteCliente(id);
		return ResponseEntity.noContent().build();
	}
	
}







