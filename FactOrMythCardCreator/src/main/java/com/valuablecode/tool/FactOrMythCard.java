package com.valuablecode.tool;

/**
 * Immutable value object that knows all there is to know about a particular Fact or Myth card.
 */
public class FactOrMythCard {
	
	public static final FactOrMythCard aBlankCard = new FactOrMythCard("");
	
	private final String cardText;
	
	public String getCardText() {
		return cardText;
	}

	public FactOrMythCard(String cardText) {
		this.cardText = cardText;
	}

}
