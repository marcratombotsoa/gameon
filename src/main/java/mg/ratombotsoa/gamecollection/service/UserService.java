package mg.ratombotsoa.gamecollection.service;

import mg.ratombotsoa.gamecollection.model.User;

public interface UserService {

	/*
	 * Load the user and fetch the video games and initialize the corresponding Ids
	 */
	User loadUserWithGames(Long userId);
	
	/*
	 * Grab the user entity by the user DTO id
	 * if password is not empty, update and encrypt password using bcrypt
	 * remove all associated videogames
	 * insert all from the DTO
	 */
	void saveUser(User user);

	void deleteUser(Long id);
}
