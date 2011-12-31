package com.valuablecode.tool;

import java.util.List;


/**
 * Knows how to create a PDF containing Fact or Myth cards.
 */
public class FactOrMythCardCreator {
	
	private final FactOrMythCardProvider cardProvider;
	private final FactOrMythLayout layout;

	public FactOrMythCardCreator(FactOrMythCardProvider cardProvider, FactOrMythLayout layout) {
		this.cardProvider = cardProvider;
		this.layout = layout;
	}

	public void createCards() {
		for (FactOrMythCard card : cardsToCreate()) {
			layout.addCard(card);
		}
		
		layout.close();
	}

	private List<FactOrMythCard> cardsToCreate() {
		List<FactOrMythCard> result = cardProvider.getCards();

		if (result.isEmpty()) {
			throw new RuntimeException("No cards to create");
		}
		
		return result;
	}

}

