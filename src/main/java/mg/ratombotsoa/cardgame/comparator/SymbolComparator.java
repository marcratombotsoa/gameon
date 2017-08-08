package mg.ratombotsoa.cardgame.comparator;

import java.util.Comparator;

import mg.ratombotsoa.cardgame.model.Card;

public class SymbolComparator implements Comparator<Card> {

	public int compare(Card card1, Card card2) {
		return card1.getSymbol().compareTo(card2.getSymbol());
	}

}
