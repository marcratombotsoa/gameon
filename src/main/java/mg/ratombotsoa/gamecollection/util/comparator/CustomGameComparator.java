package mg.ratombotsoa.gamecollection.util.comparator;

import java.util.Comparator;

import mg.ratombotsoa.gamecollection.model.VideoGame;

/**
 * Comparator class. It will be used to sort a list of {@link VideoGame} by
 * console.<br>
 * If the console is the same (Natural order by name), the items will be
 * compared using the release date
 * 
 * @author ratombotsoam
 *
 */
public class CustomGameComparator implements Comparator<VideoGame> {

	@Override
	public int compare(VideoGame o1, VideoGame o2) {
		if (o1.getConsole().equals(o2.getConsole())) {
			return o1.getReleaseDate().compareTo(o2.getReleaseDate());
		}

		return o1.getConsole().compareTo(o2.getConsole());
	}

}
