package com.valuablecode.tool;

/**
 * Allows cards to be added to a Fact or Myth card layout.
 * 
 * Note: the client code must indicate when they are finished adding cards by invoking the complete() method.
 */
public interface FactOrMythLayout {

	void addCard(FactOrMythCard card);
	void complete();

}
