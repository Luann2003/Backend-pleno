package BackendPleno.example.Backend.Pleno.dto;

import java.util.UUID;

import BackendPleno.example.Backend.Pleno.entities.Board;
import jakarta.validation.constraints.NotBlank;

public class BoardDTO {

	private UUID id;
	
	@NotBlank
	private String name;
	
	public BoardDTO() {
	}
	
	public BoardDTO(UUID id, String name) {
		this.id = id;
		this.name = name;
	}
	

	public BoardDTO(Board entity) {
		id = entity.getId();
		name = entity.getName();
	}


	public UUID getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
}
