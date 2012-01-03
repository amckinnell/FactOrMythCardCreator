package com.valuablecode.tool;

public class FactOrMythCardDriver {

	public static void main(String[] args) {
		FactOrMythConfiguration configuration = new HardCodedFactOrMythConfiguration();
		
		FactOrMythCardProvider cardProvider = new FileBasedCardProvider(configuration);
		FactOrMythLayoutService layoutService = new PdfLayoutService(configuration);
		
		FactOrMythCardCreator cardCreator = new FactOrMythCardCreator(cardProvider, layoutService);
		
		cardCreator.createCards();
	}

}
