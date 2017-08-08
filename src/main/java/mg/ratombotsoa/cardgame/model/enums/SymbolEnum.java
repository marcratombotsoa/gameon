package mg.ratombotsoa.cardgame.model.enums;

public enum SymbolEnum {
	CLUBS(ColorEnum.BLACK), 
	DIAMONDS(ColorEnum.RED), 
	HEARTS(ColorEnum.RED), 
	SPADES(ColorEnum.BLACK);
	
	private ColorEnum color;

	private SymbolEnum(ColorEnum color) {
		this.color = color;
	}

	public ColorEnum getColor() {
		return color;
	}
}
