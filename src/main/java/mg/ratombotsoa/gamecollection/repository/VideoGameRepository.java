package mg.ratombotsoa.gamecollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.ratombotsoa.gamecollection.model.VideoGame;

public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {

	VideoGame findByName(String name);
}
