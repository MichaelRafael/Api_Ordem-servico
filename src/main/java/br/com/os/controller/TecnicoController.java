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

import br.com.os.dtos.TecnicoDTO;
import br.com.os.model.Tecnico;
import br.com.os.services.TecnicoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/tecnico")
public class TecnicoController {
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Long id) {
		TecnicoDTO tecnicoDTO = new TecnicoDTO(tecnicoService.findById(id));
		return ResponseEntity.ok().body(tecnicoDTO);
	}

	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAllTecnicos() {
		
		List<TecnicoDTO> tecnicoDTO = tecnicoService.findAllTecnicos().stream()
				.map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(tecnicoDTO);
		
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> saveTecnico(@Valid @RequestBody TecnicoDTO tecnicoDTO) {
		Tecnico tecnico = tecnicoService.saveTecnico(tecnicoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> upDateTecnico(@PathVariable Long id, @Valid @RequestBody TecnicoDTO tecnicoDTO) {
		TecnicoDTO newObj = new TecnicoDTO(tecnicoService.upDateTecnico(id, tecnicoDTO));
		return ResponseEntity.status(HttpStatus.OK).body(newObj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteTecnico(@PathVariable Long id) {
		tecnicoService.deleteTecnico(id);
		return ResponseEntity.noContent().build();
	}
}







