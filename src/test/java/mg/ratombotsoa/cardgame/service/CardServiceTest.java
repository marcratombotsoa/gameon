package mg.ratombotsoa.cardgame.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import mg.ratombotsoa.cardgame.comparator.ComparatorTypeEnum;
import mg.ratombotsoa.cardgame.model.Card;
import mg.ratombotsoa.cardgame.model.enums.ColorEnum;
import mg.ratombotsoa.cardgame.model.enums.SymbolEnum;
import mg.ratombotsoa.cardgame.service.impl.CardServiceImpl;

public class CardServiceTest {

	CardService service;
	
	@Before
	public void setUp() {
		service = new CardServiceImpl();
	}
	
	@Test
	public void testGenerateCardGame() {
		Collection<Card> cards = service.generateCardGame();
		
		assertNotNull(cards);
		assertFalse(cards.isEmpty());
		assertEquals(40, cards.size());
		
		Card firstCard = cards.iterator().next();
		assertEquals(SymbolEnum.CLUBS, firstCard.getSymbol());
		assertEquals(Integer.valueOf(1), firstCard.getNumber());
	}
	
	@Test
	public void testShuffleCards() {
		Collection<Card> cards = service.generateCardGame();
		System.out.println(cards);
		
		Collection<Card> shuffled = service.shuffleCards(cards);
		System.out.println(shuffled);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSortCardsWhenTypeIsInvalid() {
		Collection<Card> cards = service.generateCardGame();
		service.sortCards(cards, "invalid", false);
	}
	
	@Test
	public void testSortCardsBySymbolAscending() {
		testSortCardsBySymbol(true);
	}
	
	@Test
	public void testSortCardsBySymbolDescending() {
		testSortCardsBySymbol(false);
	}
	
	@Test
	public void testSortCardsByColorAscending() {
		testSortCardsByColor(true);
	}
	
	@Test
	public void testSortCardsByColorDescending() {
		testSortCardsByColor(false);
	}
	
	@Test
	public void testSortCardsByNumberAscending() {
		testSortCardsByNumber(true);
	}
	
	@Test
	public void testSortCardsByNumberDescending() {
		testSortCardsByNumber(false);
	}
	
	@Test
	public void testGetRandomCard() {
		Collection<Card> cards = service.generateCardGame();
		Card random = service.getRandomCard(cards);
		
		assertNotNull(random);
	}
	
	private void testSortCardsBySymbol(boolean ascending) {
		Collection<Card> cards = service.generateCardGame();
		Collection<Card> sorted = service.sortCards(cards, ComparatorTypeEnum.SYMBOL.name(), ascending);
		
		List<Card> list = Lists.newArrayList(sorted);
		int expectedFirst = ascending ? 0 : sorted.size() - 1;;
		Card firstCard = list.get(expectedFirst);
		assertEquals(SymbolEnum.CLUBS, firstCard.getSymbol());
		int expectedLast = ascending ? sorted.size() - 1 : 0;
		Card lastCard = list.get(expectedLast);
		assertEquals(SymbolEnum.SPADES, lastCard.getSymbol());
	}
	
	private void testSortCardsByColor(boolean ascending) {
		Collection<Card> cards = service.generateCardGame();
		Collection<Card> sorted = service.sortCards(cards, ComparatorTypeEnum.COLOR.name(), ascending);
		
		List<Card> list = Lists.newArrayList(sorted);
		int expectedFirst = ascending ? 0 : sorted.size() - 1;;
		Card firstCard = list.get(expectedFirst);
		assertEquals(ColorEnum.RED, firstCard.getSymbol().getColor());
		int expectedLast = ascending ? sorted.size() - 1 : 0;
		Card lastCard = list.get(expectedLast);
		assertEquals(ColorEnum.BLACK, lastCard.getSymbol().getColor());
	}
	
	private void testSortCardsByNumber(boolean ascending) {
		Collection<Card> cards = service.generateCardGame();
		Collection<Card> sorted = service.sortCards(cards, ComparatorTypeEnum.NUMBER.name(), ascending);
		
		List<Card> list = Lists.newArrayList(sorted);
		int expectedFirst = ascending ? 0 : sorted.size() - 1;;
		Card firstCard = list.get(expectedFirst);
		assertEquals(Integer.valueOf(1), firstCard.getNumber());
		int expectedLast = ascending ? sorted.size() - 1 : 0;
		Card lastCard = list.get(expectedLast);
		assertEquals(Integer.valueOf(10), lastCard.getNumber());
	}
}
