package com.valuablecode.tool;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

/**
 * Knows the font to use for displaying each Fact or Myth phrase.
 */
public class HardCodedCardFormat implements CardFormat {
	
	// The safest choice is to embed the font in the document.
	private static final boolean EMBED_FONT_IN_DOCUMENT = true;

	// Hard coded fully qualified path to the card font.
	private static final String CARD_FONT_PATH = "/Library/Fonts/Noteworthy.ttc";
	
	private final Font cardFont;
	
	public HardCodedCardFormat() {
		cardFont = initializeCardFont();
	}

	public Font getFont() {
		return cardFont;
	}

	private Font initializeCardFont() {
        Font result = lookupFont("noteworthy-bold");
        
        result.setSize(14f);
        result.setStyle(Font.NORMAL);
        
		return result;
	}

	private Font lookupFont(String fontName) {
        FontFactory.register(CARD_FONT_PATH);
        
		return FontFactory.getFont(fontName, FontFactory.defaultEncoding, EMBED_FONT_IN_DOCUMENT);
	}

}
