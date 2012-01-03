package com.valuablecode.tool;

/**
 * Allows cards to be added to a Fact or Myth card layout.
 */
public interface FactOrMythLayout {

	void addCard(FactOrMythCard card);
	void complete();

}
