package com.valuablecode.tool;

public class FactOrMythCardDriver {

	// Hard coded path to the source of the Fact or Myth phrases.
	private static final String CARD_SOURCE = "/Users/alistair/Desktop/FactOrMyths.txt";
	
	
	public static void main(String[] args) {
		FactOrMythCardProvider cardProvider = new FileBasedCardProvider(CARD_SOURCE);
		FactOrMythLayout document = new PdfFactOrMythLayout(new LetterPageLayout());
		
		FactOrMythCardCreator cardCreator = new FactOrMythCardCreator(cardProvider, document);
		
		cardCreator.createCards();
	}

}
