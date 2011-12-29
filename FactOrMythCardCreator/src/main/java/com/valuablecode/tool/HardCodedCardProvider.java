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
		createCard("Fact or Myth Card One");
		createCard("Fact or Myth Card Two\n(With A Second Line)");
		createCard("Fact or Myth Card Two\n(With A Second Line\nand Third Line)");
	}

	private void createCard(String cardText) {
		cards.add(new FactOrMythCard(cardText));
	}

	public List<FactOrMythCard> getCards() {
		return Collections.unmodifiableList(cards);
	}
	
}
