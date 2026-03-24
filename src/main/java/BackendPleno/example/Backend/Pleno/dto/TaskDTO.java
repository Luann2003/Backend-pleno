package BackendPleno.example.Backend.Pleno.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import BackendPleno.example.Backend.Pleno.entities.Task;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskDTO {
	
	private UUID id;
	
	@NotBlank
	private String name;
	
	@NotNull
	private Integer position;
	
	private LocalDateTime createdAt;
	
	@FutureOrPresent(message = "A data de entrega não pode estar no passado")
	@NotNull
	private LocalDateTime dueDate;
	
	private Boolean completed;
	
	private List<String> tags = new ArrayList<>(); 
	
	@NotNull
	private UUID columnId;
	
	public TaskDTO() {
	}

	public TaskDTO(UUID id, String name, Integer position, LocalDateTime createdAt, LocalDateTime dueDate,
			Boolean completed, UUID columnId) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.createdAt = createdAt;
		this.dueDate = dueDate;
		this.completed = completed;
		this.columnId = columnId;
	}
	
	public TaskDTO(Task entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.position = entity.getPosition();
		this.createdAt = entity.getCreatedAt();
		this.dueDate = entity.getDueDate();
		this.completed = entity.getCompleted();
		this.columnId = entity.getColumnId();
		
		for(String tag : entity.getTags()) {
			tags.add(tag);
		}
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public List<String> getTags() {
		return tags;
	}

	public UUID getColumnId() {
		return columnId;
	}

}
