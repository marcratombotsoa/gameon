package mg.ratombotsoa.gamecollection.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUtil {

	public static <T> void sortCollections(List<T> entities, Comparator<T> comparator, boolean ascending) {
		Collections.sort(entities, comparator);

		if (!ascending) {
			Collections.reverse(entities);
		}
	}
}
