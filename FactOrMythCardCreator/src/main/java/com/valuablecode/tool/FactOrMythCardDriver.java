package com.valuablecode.tool;

public class FactOrMythCardDriver {

	public static void main(String[] args) {
		FactOrMythCardProvider cardProvider = new FileBasedCardProvider();
		FactOrMythLayout document = new PdfFactOrMythLayout(new LetterPageLayout());
		
		FactOrMythCardCreator cardCreator = new FactOrMythCardCreator(cardProvider, document);
		
		cardCreator.createCards();
	}

}
