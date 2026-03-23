package BackendPleno.example.Backend.Pleno.dto;

import java.util.UUID;

import BackendPleno.example.Backend.Pleno.entities.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ColumnDTO {
	
	private UUID id;
	
	@NotBlank
	private String name;
	
	@NotNull
	private Integer position;
	
	@NotNull
	private UUID boardId;
		
	public ColumnDTO() {
	}
	
	public ColumnDTO(UUID id, String name, Integer position, UUID boardId) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.boardId = boardId;
	}
	
	public ColumnDTO(Column entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.position = entity.getPosition();
		this.boardId = entity.getBoardId();
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getPosition() {
		return position;
	}

	public UUID getBoardId() {
		return boardId;
	}
	
}
