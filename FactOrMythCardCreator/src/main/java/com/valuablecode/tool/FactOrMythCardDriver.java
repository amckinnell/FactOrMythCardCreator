package com.valuablecode.tool;

public class FactOrMythCardDriver {

	public static void main(String[] args) {
		FactOrMythCardProvider cardProvider = new HardCodedCardProvider();
		FactOrMythDocument document = new IndexCardLayout();
		
		FactOrMythCardCreator cardCreator = new FactOrMythCardCreator(cardProvider, document);
		
		cardCreator.createCards();
	}

}
