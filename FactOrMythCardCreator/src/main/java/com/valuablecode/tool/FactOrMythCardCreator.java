package com.valuablecode.tool;


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
		for (FactOrMythCard card : cardProvider.getCards()) {
			document.addCard(card);
		}
	}

}
