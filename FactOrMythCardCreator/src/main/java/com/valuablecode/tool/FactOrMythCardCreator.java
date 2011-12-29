package com.valuablecode.tool;

import java.util.List;


/**
 * Knows how to create a PDF containing Fact or Myth cards.
 */
public class FactOrMythCardCreator {
	
	private final FactOrMythCardProvider cardProvider;
	private final FactOrMythDocument document;

	public FactOrMythCardCreator(FactOrMythCardProvider cardProvider, FactOrMythDocument document) {
		this.cardProvider = cardProvider;
		this.document = document;
	}

	public void createCards() {
		for (FactOrMythCard card : cardsToCreate()) {
			document.addCard(card);
		}
		
		document.close();
	}

	private List<FactOrMythCard> cardsToCreate() {
		List<FactOrMythCard> result = cardProvider.getCards();

		if (result.isEmpty()) {
			throw new RuntimeException("No cards to create");
		}
		
		return result;
	}

}

