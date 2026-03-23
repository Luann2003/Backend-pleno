package BackendPleno.example.Backend.Pleno.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import BackendPleno.example.Backend.Pleno.entities.Board;

public interface BoardRepository extends JpaRepository<Board, UUID>{

}
