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
	private BoardDTO boards;

		
	public ColumnDTO() {
	}
	
	
	public ColumnDTO(UUID id, @NotBlank String name, @NotNull Integer position, BoardDTO boards) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.boards = boards;
	}


	public ColumnDTO(Column entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.position = entity.getPosition();
		this.boards = new BoardDTO(entity.getBoards());
	
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

	public BoardDTO getBoards() {
		return boards;
	}

}
