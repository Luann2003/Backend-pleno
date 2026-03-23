package BackendPleno.example.Backend.Pleno.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "columns")
public class Column {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;
	
	private String name;
	private Integer position;
	
	@ManyToOne
	@JoinColumn(name = "board_id")
	private Board boards;
	
	public Column() {
	}

	public Column(UUID id, String name, Integer position, Board boards) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.boards = boards;
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

	public Board getBoards() {
		return boards;
	}

	public void setBoards(Board boards) {
		this.boards = boards;
	}
	
}
