package BackendPleno.example.Backend.Pleno.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import BackendPleno.example.Backend.Pleno.dto.ColumnDTO;
import BackendPleno.example.Backend.Pleno.entities.Column;
import BackendPleno.example.Backend.Pleno.repository.ColumnRepository;

@Service
public class ColumnService {
	
	@Autowired
	private ColumnRepository repository;
	
	@Transactional(readOnly = true)
	public List<ColumnDTO> findAllByBoard(UUID boardId){
		List<Column> result = repository.findAllByBoard(boardId);
		return result.stream().map(x -> new ColumnDTO(x)).toList();
	}
	
	@Transactional
	public ColumnDTO ColumnInsert(ColumnDTO dto) {
		Column entity = new Column();	
		entity.setName(dto.getName());
		entity.setPosition(dto.getPosition());
		entity.setBoardId(dto.getBoardId());
		entity = repository.save(entity);
	
		return new ColumnDTO(entity);
	}

}
