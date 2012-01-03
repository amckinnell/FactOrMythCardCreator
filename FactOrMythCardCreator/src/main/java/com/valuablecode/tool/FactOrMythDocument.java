package com.valuablecode.tool;

public interface FactOrMythDocument {

	void addCard(FactOrMythCard card);
	void emitPage();
	void close();

}
