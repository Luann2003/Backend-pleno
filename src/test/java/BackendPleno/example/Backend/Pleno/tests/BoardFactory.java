package BackendPleno.example.Backend.Pleno.tests;

import java.util.UUID;

import BackendPleno.example.Backend.Pleno.dto.BoardDTO;
import BackendPleno.example.Backend.Pleno.entities.Board;

public class BoardFactory {
	
	public static Board createBoardEntity() {
		Board board = new Board(UUID.fromString("bab0953e-253c-4edc-87fb-9948eedb05d8"), "board1");
		return board;
	}
	
	public static BoardDTO createBoardDTO() {
		Board board = createBoardEntity();
		return new BoardDTO(board);
	}

}
