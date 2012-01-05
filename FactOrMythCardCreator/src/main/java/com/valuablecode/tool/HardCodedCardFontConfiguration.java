package com.valuablecode.tool;


/**
 * Hard codes a particular configuration.
 */
public class HardCodedCardFontConfiguration implements CardFontConfiguration {

	// Hard coded fully qualified path to the card font.
	private static final String CARD_FONT_PATH = "/Library/Fonts/Noteworthy.ttc";
	
	// Hard coded card font name.
	private static final String CARD_FONT_NAME = "noteworthy-bold";

	
	public String getCardFontPath() {
		return CARD_FONT_PATH;
	}

	public String getCardFontName() {
		return CARD_FONT_NAME;
	}

	public float getFontSize() {
		return 24f;
	}
	
}
