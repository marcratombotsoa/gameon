package mg.ratombotsoa.gamecollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.ratombotsoa.gamecollection.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
