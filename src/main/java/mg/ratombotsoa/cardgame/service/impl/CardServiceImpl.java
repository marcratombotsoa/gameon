package mg.ratombotsoa.cardgame.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import mg.ratombotsoa.cardgame.comparator.ComparatorTypeEnum;
import mg.ratombotsoa.cardgame.model.Card;
import mg.ratombotsoa.cardgame.model.enums.SymbolEnum;
import mg.ratombotsoa.cardgame.service.CardService;

public class CardServiceImpl implements CardService {

	public Collection<Card> shuffleCards(Collection<Card> cards) {
		List<Card> shuffled = Lists.newArrayList(cards);
		Collections.shuffle(shuffled);
		return shuffled;
	}
	
	public Collection<Card> sortCards(Collection<Card> cards, String comparatorType, boolean ascending) {
		Comparator<Card> comparator = ComparatorTypeEnum.valueOf(comparatorType.toUpperCase()).getComparator();
		List<Card> sorted = Lists.newArrayList(cards);
		Collections.sort(sorted, comparator);
		
		if (!ascending) {
			Collections.reverse(sorted);
		}
		
		return sorted;
	}
	
	public Card getRandomCard(Collection<Card> cards) {
		return Lists.newArrayList(cards).get(new Random().nextInt(cards.size()));
	}
	
	public Collection<Card> generateCardGame() {
		Collection<Card> cards = Lists.newArrayList();
		
		for (SymbolEnum symbol: SymbolEnum.values()) {
			for (int i = 1; i<=10; i++) {
				cards.add(new Card(symbol, i));
			}
		}
		
		return cards;
	}
}
