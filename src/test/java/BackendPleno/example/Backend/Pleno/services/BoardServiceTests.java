package BackendPleno.example.Backend.Pleno.services;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import BackendPleno.example.Backend.Pleno.dto.BoardDTO;
import BackendPleno.example.Backend.Pleno.entities.Board;
import BackendPleno.example.Backend.Pleno.repository.BoardRepository;
import BackendPleno.example.Backend.Pleno.service.BoardService;
import BackendPleno.example.Backend.Pleno.service.exceptions.ResourceNotFoundException;
import BackendPleno.example.Backend.Pleno.tests.BoardFactory;

@ExtendWith(MockitoExtension.class) 
public class BoardServiceTests {
	
	@InjectMocks
	private BoardService service;

	@Mock
	private BoardRepository repository;
	
	private UUID existingId, nonExistingId;
	
	private Board board;
	private BoardDTO dto;
	
	private String boardName;

	@BeforeEach
	void setup() throws Exception {
		existingId = UUID.fromString("bab0953e-253c-4edc-87fb-9948eedb05d8");
		nonExistingId = UUID.fromString("39c16af2-e30f-43c6-ac08-e65e647b07ee");
		
		board = BoardFactory.createBoardEntity();
		dto = BoardFactory.createBoardDTO();
		
		boardName = "board1";

		
	}
	
	@Test
	public void insertBoardReturnBoardDTO() {
		Mockito.when(repository.save(Mockito.any())).thenReturn(board);

	    BoardDTO result = service.boardInsert(dto);
	    
	    Assertions.assertNotNull(result);
	    Assertions.assertEquals(existingId, result.getId());
	    Assertions.assertEquals(board.getName(), result.getName());
	}
	
	@Test
	public void updateBoardReturnBoardDTO() {
		Mockito.when(repository.save(Mockito.any())).thenReturn(board);
		Mockito.when(repository.getReferenceById(existingId)).thenReturn(board);
	    BoardDTO result = service.update(existingId,dto);
	    
	    Assertions.assertNotNull(result);
	    Assertions.assertEquals(existingId, result.getId());
	    Assertions.assertEquals(board.getName(), result.getName());
	}
	
	@Test
	public void updateShouldThrowResorceNotFoundException() {
		
		Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			BoardDTO result = service.update(nonExistingId, dto);
		});
		
	}
	
	
	
	
	
}
