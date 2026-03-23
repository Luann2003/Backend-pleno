package BackendPleno.example.Backend.Pleno.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import BackendPleno.example.Backend.Pleno.dto.BoardDTO;
import BackendPleno.example.Backend.Pleno.entities.Board;
import BackendPleno.example.Backend.Pleno.repository.BoardRepository;
import BackendPleno.example.Backend.Pleno.service.exceptions.DatabaseException;
import BackendPleno.example.Backend.Pleno.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository repository;
	
	@Transactional(readOnly = true)
	public List<BoardDTO> findAll(){
		List<Board> result = repository.findAll();
		return result.stream().map(x -> new BoardDTO(x)).toList();
	}
	
	@Transactional
	public BoardDTO boardInsert(BoardDTO dto) {
		
		Board entity = new Board();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity = repository.save(entity);
		
		return new BoardDTO(entity);	
	}
	
	@Transactional
	public BoardDTO update(UUID id, BoardDTO dto) {
		
		try {
			Board entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new BoardDTO(entity);
			
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
