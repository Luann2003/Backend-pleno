package BackendPleno.example.Backend.Pleno.controller;

import java.util.List;
import java.util.UUID;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import BackendPleno.example.Backend.Pleno.dto.ColumnDTO;
import BackendPleno.example.Backend.Pleno.service.ColumnService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/column/from")
public class ColumnController {
	
	@Autowired
	private ColumnService service;
	
	@Transactional(readOnly = true)
	@GetMapping(value = "/{id}")
	public ResponseEntity<List<ColumnDTO>> findall(@PathVariable UUID id){
		List<ColumnDTO> result = service.findAllByBoard(id);
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping
	public ResponseEntity<ColumnDTO> insert(@Valid @RequestBody ColumnDTO dto){
		
		dto = service.ColumnInsert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

}
