package mg.ratombotsoa.gamecollection.util.comparator;

import java.util.Comparator;

import mg.ratombotsoa.gamecollection.model.Console;

public class ReleaseDateComparator implements Comparator<Console> {

	@Override
	public int compare(Console o1, Console o2) {
		if (o1.getReleaseDate() == null) {
			return -1;
		}
		
		if (o2.getReleaseDate() == null) {
			return 1;
		}
		
		return o1.getReleaseDate().compareTo(o2.getReleaseDate());
	}

}
