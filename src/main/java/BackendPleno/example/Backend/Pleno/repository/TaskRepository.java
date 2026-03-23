package BackendPleno.example.Backend.Pleno.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import BackendPleno.example.Backend.Pleno.entities.Task;

public interface TaskRepository extends JpaRepository<Task, UUID>{
	
	@Query("SELECT obj FROM Task obj WHERE obj.columnId = :id")
    List<Task> findAllByColumn(@Param("id") UUID id);

}
