package mg.ratombotsoa.gamecollection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.ratombotsoa.gamecollection.model.Console;

public interface ConsoleRepository extends JpaRepository<Console, Long> {

	Console findByName(String name);
	
	List<Console> findAllByNameContaining(String name);
}
