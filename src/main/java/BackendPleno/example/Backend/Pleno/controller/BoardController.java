package BackendPleno.example.Backend.Pleno.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import BackendPleno.example.Backend.Pleno.dto.BoardDTO;
import BackendPleno.example.Backend.Pleno.service.BoardService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping
	public ResponseEntity<List<BoardDTO>> findall(){
		List<BoardDTO> result = service.findAll();
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<BoardDTO> insert(@Valid @RequestBody BoardDTO dto){
		
		dto = service.boardInsert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<BoardDTO> Update(@Valid @PathVariable UUID id, @RequestBody BoardDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	

}
