package mg.ratombotsoa.gamecollection.service;

import java.util.Comparator;
import java.util.List;

import mg.ratombotsoa.gamecollection.model.VideoGame;

public interface GameService {

	/**
	 * Sort the list of games by using the specified comparator
	 * 
	 * @param games
	 * @param comparator
	 * @param ascending
	 */
	void sortGames(List<VideoGame> games, Comparator<VideoGame> comparator, boolean ascending);
}
