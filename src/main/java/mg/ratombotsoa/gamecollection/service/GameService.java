package mg.ratombotsoa.gamecollection.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface GameService {

	/**
	 * Creates or overwrite the existing cover photo for the game
	 * 
	 * @param file
	 * @throws IOException 
	 */
	void storeCover(MultipartFile file, Long gameId) throws IOException;

	/**
	 * Returns the cover image of the game by its Id
	 * 
	 * @param gameId
	 * @return
	 * @throws IOException
	 */
	byte[] getImage(Long gameId) throws IOException;

	/**
	 * Removes the image cover of the game by its Id
	 * 
	 * @param gameId
	 */
	void deleteGameCover(Long gameId) throws IOException;

	/**
	 * Deletes the game<br/>
	 * - Step 1: Remove all links with users for the game
	 * - Step 2: Remove the game cover photo
	 * - Step 3: Remove the game entity
	 * 
	 * @param gameId
	 * @throws IOException 
	 */
	void deleteGame(Long gameId) throws IOException;
}
