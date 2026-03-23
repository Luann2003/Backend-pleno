package BackendPleno.example.Backend.Pleno.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import BackendPleno.example.Backend.Pleno.dto.TaskDTO;
import BackendPleno.example.Backend.Pleno.entities.Task;
import BackendPleno.example.Backend.Pleno.repository.TaskRepository;
import BackendPleno.example.Backend.Pleno.service.exceptions.DatabaseException;
import BackendPleno.example.Backend.Pleno.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService {
	
	
	@Autowired
	private TaskRepository repository;
	
	@Transactional(readOnly = true)
	public List<TaskDTO> findAllByTask(UUID ColumnId){
		List<Task> result = repository.findAllByColumn(ColumnId);
		return result.stream().map(x -> new TaskDTO(x)).toList();
	}
	
	@Transactional
	public TaskDTO insertTask(TaskDTO dto) {

		Task entity = new Task();
		entity.setName(dto.getName());
		entity.setCompleted(dto.getCompleted());
		entity.setPosition(dto.getPosition());
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setDueDate(dto.getDueDate());
		entity.setColumnId(dto.getColumnId());

		for (String tag : dto.getTags()) {
			entity.addTag(tag);
		}

		entity = repository.save(entity);

		return new TaskDTO(entity);
	}
	
	@Transactional
	public TaskDTO update(UUID id, TaskDTO dto) {
		
		try {
			Task entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity.setCompleted(dto.getCompleted());
			entity.setPosition(dto.getPosition());
			entity.setDueDate(dto.getDueDate());
			entity.setColumnId(dto.getColumnId());

			entity.getTags().clear();
			for (String tag : dto.getTags()) {
				entity.addTag(tag);
			}
			
			entity = repository.save(entity);
			return new TaskDTO(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(UUID id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}
	}

}
