package com.valuablecode.tool;

public class FactOrMythCardDriver {

	private static final String CONFIGURATION_FILE_NAME = "/Users/alistair/Desktop/FactOrMyths.properties";

	public static void main(String[] args) {
		FactOrMythConfiguration configuration = new PropertyFileFactOrMythConfiguration(CONFIGURATION_FILE_NAME);
		
		FactOrMythCardProvider cardProvider = new FileBasedCardProvider(configuration);
		FactOrMythLayoutService layoutService = new PdfLayoutService(configuration);
		
		FactOrMythCardCreator cardCreator = new FactOrMythCardCreator(cardProvider, layoutService);
		
		cardCreator.createCards();
	}

}
