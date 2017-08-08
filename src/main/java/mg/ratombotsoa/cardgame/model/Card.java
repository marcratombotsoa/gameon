package mg.ratombotsoa.cardgame.model;

import mg.ratombotsoa.cardgame.model.enums.SymbolEnum;

/**
 * Entity that represents one card with its symbol and number
 * 
 * @author ratombotsoam
 *
 */
public class Card {

	private SymbolEnum symbol;
	private Integer number;
	
	public Card() {
		super();
	}
	
	public Card(SymbolEnum symbol, Integer number) {
		super();
		this.symbol = symbol;
		this.number = number;
	}

	public SymbolEnum getSymbol() {
		return symbol;
	}
	public void setSymbol(SymbolEnum symbol) {
		this.symbol = symbol;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "(" + number + ", " + symbol.getColor().name() + ", " + symbol.name() + ")";
	}
	
}
