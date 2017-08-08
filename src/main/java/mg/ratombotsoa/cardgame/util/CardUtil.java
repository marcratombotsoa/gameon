package mg.ratombotsoa.cardgame.util;

import java.util.Collection;

import mg.ratombotsoa.cardgame.model.Card;

public class CardUtil {

	public static void printCards(Collection<Card> cards) {
		int index = 1;
		for (Card card : cards) {
			System.out.print(card);
			if (index++ % 4 == 0) {
				System.out.println();
			}
		}
	}
}
