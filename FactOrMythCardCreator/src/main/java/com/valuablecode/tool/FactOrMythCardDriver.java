package com.valuablecode.tool;

public class FactOrMythCardDriver {

	public static void main(String[] args) {
		FactOrMythCardProvider cardProvider = new FileBasedCardProvider();
		FactOrMythDocument document = new FactOrMythPdfDocument(new IndexCardPageLayout());
		
		FactOrMythCardCreator cardCreator = new FactOrMythCardCreator(cardProvider, document);
		
		cardCreator.createCards();
	}

}
