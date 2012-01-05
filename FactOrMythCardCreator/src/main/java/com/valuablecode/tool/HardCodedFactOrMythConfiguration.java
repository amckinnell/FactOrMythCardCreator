package com.valuablecode.tool;

import com.valuablecode.tool.itext.HardCodedCardFormat;

/**
 * Hard-coded configuration related to creating Fact Or Myth cards.
 */
public class HardCodedFactOrMythConfiguration implements FactOrMythConfiguration {

	// Hard coded path to the file containing the Fact or Myth phrases.
	private static final String CARD_SOURCE = "/Users/alistair/Desktop/FactOrMyths.txt";
	
	// Hard coded path to the output PDF.
	private static final String OUTPUT_PDF = "/Users/alistair/Desktop/FactOrMyth.pdf";


	public String getCardSourceFileName() {
		return CARD_SOURCE;
	}

	public String getOutputFileName() {
		return OUTPUT_PDF;
	}
	
	public PageLayout getPageLayout() {
		return new LetterPageLayout();
	}

	public CardFormat getCardFormat() {
		return new HardCodedCardFormat();
	}

}
