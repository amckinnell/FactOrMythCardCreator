package com.valuablecode.tool;

public class FactOrMythCardDriver {

	public static void main(String[] args) {
		FactOrMythConfiguration configuration = new HardCodedFactOrMythConfiguration();
		
		FactOrMythCardProvider cardProvider = new FileBasedCardProvider(configuration);
		FactOrMythLayout target = new PdfFactOrMythLayout(configuration);
		
		FactOrMythCardCreator cardCreator = new FactOrMythCardCreator(cardProvider, target);
		
		cardCreator.createCards();
	}

}
