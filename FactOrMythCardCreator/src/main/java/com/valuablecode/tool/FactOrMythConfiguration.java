package com.valuablecode.tool;

/**
 * Knows all the configuration related to creating Fact Or Myth cards.
 */
public interface FactOrMythConfiguration {

	String getCardSourceFileName();
	String getOutputFileName();
	PageLayout getPageLayout();
	CardFormat getCardFormat();

}
