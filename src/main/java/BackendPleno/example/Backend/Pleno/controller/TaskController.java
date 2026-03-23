package BackendPleno.example.Backend.Pleno.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import BackendPleno.example.Backend.Pleno.dto.TaskDTO;
import BackendPleno.example.Backend.Pleno.service.TaskService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/task/from")
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@Transactional(readOnly = true)
	@GetMapping(value = "/{id}")
	public ResponseEntity<List<TaskDTO>> findall(@PathVariable UUID id){
		List<TaskDTO> result = service.findAllByTask(id);
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping
	public ResponseEntity<TaskDTO> insert(@Valid @RequestBody TaskDTO dto){
		
		dto = service.insertTask(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TaskDTO> Update(@Valid @PathVariable UUID id, @RequestBody TaskDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	

}
