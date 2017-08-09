package mg.ratombotsoa.cardgame.service;

import java.util.Collection;

import mg.ratombotsoa.cardgame.model.Card;

public interface CardService {

	/**
	 * Shuffles the collection of cards in random order and return a copy of the shuffled collection
	 * @param cards
	 * @return
	 */
	Collection<Card> shuffleCards(Collection<Card> cards);

	/**
	 * Sort the collection of cards according to the comparator specified<br/>
	 * If the ascending parameter is set to false, the collection is reversed
	 * 
	 * @param cards
	 * @param comparatorType
	 * @param ascending
	 * @return
	 */
	Collection<Card> sortCards(Collection<Card> cards, String comparatorType, boolean ascending);

	/**
	 * Return a random card amongst the collection of cards
	 * 
	 * @param cards
	 * @return
	 */
	Card getRandomCard(Collection<Card> cards);

	/**
	 * Generate a card game collection
	 * @return
	 */
	Collection<Card> generateCardGame();

}