package BackendPleno.example.Backend.Pleno.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import BackendPleno.example.Backend.Pleno.entities.Column;

public interface ColumnRepository extends JpaRepository<Column, UUID>{
	
	@Query("SELECT c FROM Column c WHERE c.boardId = :id")
	List<Column> findAllByBoard(@Param("id") UUID id);

}
