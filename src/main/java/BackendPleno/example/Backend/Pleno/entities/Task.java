package BackendPleno.example.Backend.Pleno.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "task")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;
	
	@NotBlank
	private String name;
	
	@NotNull
	private Integer position;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	private LocalDateTime dueDate;
	
	private Boolean completed;
	
	@NotNull
	private List<String> tags = new ArrayList<>(); 
	
	@NotNull
	private UUID columnId;
	
	public Task() {
	}

	public Task(UUID id, String name, Integer position, LocalDateTime createdAt, LocalDateTime dueDate,
			Boolean completed, List<String> tags, UUID columnId) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.createdAt = createdAt;
		this.dueDate = dueDate;
		this.completed = completed;
		this.tags = tags;
		this.columnId = columnId;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public List<String> getTags() {
		return tags;
	}
	
	public void addTag(String tag) {
		tags.add(tag);
	}

	public UUID getColumnId() {
		return columnId;
	}

	public void setColumnId(UUID columId) {
		this.columnId = columId;
	}
	
}
