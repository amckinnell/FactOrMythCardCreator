package com.valuablecode.tool.itext;

import static com.itextpdf.text.FontFactory.defaultEncoding;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.valuablecode.tool.CardFontConfiguration;
import com.valuablecode.tool.CardFormat;

/**
 * Knows the font to use for displaying each Fact or Myth phrase.
 */
public class ConfigurableCardFormat implements CardFormat {

    // The safest choice is to embed the font in the PDF document.
    private static final boolean EMBED_FONT_IN_DOCUMENT = true;

    private final CardFontConfiguration configuration;

    private Font cardFont;


    public ConfigurableCardFormat(CardFontConfiguration configuration) {
        this.configuration = configuration;
    }

    public Font getFont() {
        if (null == cardFont) {
            cardFont = initializeCardFont();
        }

        return cardFont;
    }

    private Font initializeCardFont() {
        Font result = createFont();

        result.setSize(configuration.getCardFontSize());
        result.setStyle(Font.NORMAL);

        return result;
    }

    private Font createFont() {
        FontFactory.register(configuration.getCardFontPath());

        return FontFactory.getFont(configuration.getCardFontName(), defaultEncoding, EMBED_FONT_IN_DOCUMENT);
    }

}

