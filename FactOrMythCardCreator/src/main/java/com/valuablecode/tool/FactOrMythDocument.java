package com.valuablecode.tool;

/**
 * Allows cards to be added to a Fact or Myth card document.
 */
public interface FactOrMythDocument {

	void addCard(FactOrMythCard card);
	void close();

}
