package mg.ratombotsoa.gamecollection.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import mg.ratombotsoa.gamecollection.model.VideoGame;

public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {

	VideoGame findByName(String name);
	
	@Query("select v from VideoGame v where v.console.id = ?1")
	List<VideoGame> findAllByConsoleId(Long id);

	List<VideoGame> findAllByNameContaining(String criteria, Sort sort);
	
	@Query("select v from VideoGame v left join fetch v.users where v.name = ?1")
	VideoGame findByNameAndFetchUsers(String name);

	List<VideoGame> findAllByUsersId(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "delete from user_game where game_id = ?1", nativeQuery = true)
	void unlinkGameFromUsers(Long gameId);
}
