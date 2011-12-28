package com.valuablecode.tool;

public class FactOrMythCardDriver {

	public static void main(String[] args) {
		FactOrMythCardCreator cardCreator = new FactOrMythCardCreator(new HardCodedCardProvider(), 
				new PdfFactOrMythDocument());
		
		cardCreator.createCards();
	}

}
