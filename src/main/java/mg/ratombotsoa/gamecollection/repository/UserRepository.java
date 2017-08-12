package mg.ratombotsoa.gamecollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import mg.ratombotsoa.gamecollection.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u left join fetch u.games where u.id = ?1")
	User findByIdAndFetchGames(Long id);

	@Transactional
	@Modifying
	@Query(value = "delete from user_game where user_id = ?1", nativeQuery = true)
	void clearGames(Long id);

	@Transactional
	@Modifying
	@Query(value = "insert into user_game (game_id, user_id) values (?1, ?2)", nativeQuery = true)
	void linkUserGames(Long gameId, Long userId);	
}
