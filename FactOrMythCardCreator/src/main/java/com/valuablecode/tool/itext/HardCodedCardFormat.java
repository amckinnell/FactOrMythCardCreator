package com.valuablecode.tool.itext;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.valuablecode.tool.CardFormat;

/**
 * Knows the font to use for displaying each Fact or Myth phrase.
 */
public class HardCodedCardFormat implements CardFormat {
	
	// The safest choice is to embed the font in the PDF document.
	private static final boolean EMBED_FONT_IN_DOCUMENT = true;

	// Hard coded fully qualified path to the card font.
	private static final String CARD_FONT_PATH = "/Library/Fonts/Noteworthy.ttc";
	
	private final Font cardFont;
	
	
	public HardCodedCardFormat() {
		this.cardFont = initializeCardFont();
	}

	public Font getFont() {
		return cardFont;
	}

	private Font initializeCardFont() {
        Font result = lookupFont("noteworthy-bold");
        
        result.setSize(24f);
        result.setStyle(Font.NORMAL);
        
		return result;
	}

	private Font lookupFont(String fontName) {
        FontFactory.register(CARD_FONT_PATH);
        
		return FontFactory.getFont(fontName, FontFactory.defaultEncoding, EMBED_FONT_IN_DOCUMENT);
	}

}
