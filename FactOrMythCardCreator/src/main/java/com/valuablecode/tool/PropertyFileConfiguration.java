package com.valuablecode.tool;

import static com.valuablecode.tool.LetterPageLayout.aLetterPageLayout;
import static com.valuablecode.tool.LetterPageLayout.aLetterPageLayoutWithBorders;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.valuablecode.tool.itext.ConfigurableCardFormat;

/**
 * Property file based configuration related to creating Fact Or Myth cards.
 */
public class PropertyFileConfiguration implements FactOrMythConfiguration, CardFontConfiguration {

    // Key definitions for the configuration property file.
    private static final String CARD_SOURCE_FILE_NAME_PROPERTY_KEY = "cardSourceFileName";
    private static final String OUTPUT_FILE_NAME_PROPERTY_KEY = "outputFileName";
    private static final String PAGE_LAYOUT_PROPERTY_KEY = "pageLayout";

    private static final String FONT_PATH_PROPERTY_KEY = "font.fileName";
    private static final String FONT_NAME_PROPERTY_KEY = "font.name";
    private static final String FONT_SIZE_PROPERTY_KEY = "font.size";

    // Normalised values for the page layout property. Values read from the property file are normalised by
    // trimming whitespace, replacing whitespace with an underscore, and converting the value to all upper case.
    private enum SupportedPageLayouts { BINGO, INDEX_CARD, LETTER, LETTER_WITH_BORDERS }

    private final Configuration configuration;
    private final String propertyFileName;

    public PropertyFileConfiguration(String propertyFileName) {
        this(propertyFileName, initializeConfiguration(propertyFileName));
    }

    private static Configuration initializeConfiguration(String propertyFileName) {
        try {
            return new PropertiesConfiguration(propertyFileName);
        } catch (ConfigurationException e) {
            throw new RuntimeException("Unable to initialize property configuration from: " + propertyFileName, e);
        }
    }

    public PropertyFileConfiguration(String propertyFileName, Configuration configuration) {
        this.propertyFileName = propertyFileName;
        this.configuration = configuration;
    }

    public String getCardSourceFileName() {
        return configuration.getString(CARD_SOURCE_FILE_NAME_PROPERTY_KEY);
    }

    public String getOutputFileName() {
        return configuration.getString(OUTPUT_FILE_NAME_PROPERTY_KEY);
    }

    public PageLayout getPageLayout() {
        switch (getSupportedPageLayout()) {
            case BINGO:
                return new BingoPageLayout();

            case INDEX_CARD:
                return new IndexCardPageLayout();

            case LETTER:
                return aLetterPageLayout();

            case LETTER_WITH_BORDERS:
                return aLetterPageLayoutWithBorders();

            default:
                throw new RuntimeException("Unknown page layout: " + getSupportedPageLayout());
        }
    }

    private SupportedPageLayouts getSupportedPageLayout() {
        String rawPageLayoutSpecification = configuration.getString(PAGE_LAYOUT_PROPERTY_KEY);

        try {
            String pageLayoutSpecification = normalizeRawValue(rawPageLayoutSpecification);

            return SupportedPageLayouts.valueOf(pageLayoutSpecification);
        } catch (Exception e) {
            throw new RuntimeException("Unknown page layout specification: " + rawPageLayoutSpecification);
        }
    }

    private String normalizeRawValue(String rawPageLayoutSpecification) {
        return rawPageLayoutSpecification.trim().replaceAll(" +", "_").toUpperCase();
    }

    public CardFormat getCardFormat() {
        return new ConfigurableCardFormat(this);
    }

    public String getCardFontPath() {
        return configuration.getString(FONT_PATH_PROPERTY_KEY);
    }

    public String getCardFontName() {
        return configuration.getString(FONT_NAME_PROPERTY_KEY);
    }

    public float getCardFontSize() {
        return configuration.getFloat(FONT_SIZE_PROPERTY_KEY);
    }

}

