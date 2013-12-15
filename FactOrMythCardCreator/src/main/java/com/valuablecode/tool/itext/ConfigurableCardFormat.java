package com.valuablecode.tool.itext;

import static com.itextpdf.text.FontFactory.defaultEncoding;

import com.itextpdf.text.BaseColor;
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

    public BaseColor getBackgroundColor() {
        // new BaseColor(102, 255, 102); Light Green
        // new BaseColor(255, 102, 102); Light Red
        // new BaseColor(255, 255, 102); Light Yellow
        // new BaseColor(255, 255, 255); White

        return new BaseColor(255, 255, 255);
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

