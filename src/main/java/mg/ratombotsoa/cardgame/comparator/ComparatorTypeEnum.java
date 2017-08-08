package mg.ratombotsoa.cardgame.comparator;

import java.util.Comparator;

import mg.ratombotsoa.cardgame.model.Card;

public enum ComparatorTypeEnum {
	COLOR {
		@Override
		public Comparator<Card> getComparator() {
			return new ColorComparator();
		}
	},
	SYMBOL {
		@Override
		public Comparator<Card> getComparator() {
			return new SymbolComparator();
		}
	},
	NUMBER {
		@Override
		public Comparator<Card> getComparator() {
			return new NumberComparator();
		}
	};
	
	public abstract Comparator<Card> getComparator();
}
