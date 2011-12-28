package com.valuablecode.tool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Knows how to provide a hard coded list of Fact or Myth cards.
 */
public class HardCodedCardProvider implements FactOrMythCardProvider {
	
	private List<FactOrMythCard> cards = new ArrayList<FactOrMythCard>();
	
	public HardCodedCardProvider() {
		createCard("Fact or Myth Card 1");
		createCard("Fact or Myth Card 2");
	}

	private void createCard(String cardText) {
		cards.add(new FactOrMythCard(cardText));
	}

	public List<FactOrMythCard> getCards() {
		return Collections.unmodifiableList(cards);
	}
	
}
