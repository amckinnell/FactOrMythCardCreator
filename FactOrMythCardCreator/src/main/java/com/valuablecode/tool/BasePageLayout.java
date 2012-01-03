package com.valuablecode.tool;


public abstract class BasePageLayout implements PageLayout {

	public float getCardHeight() {
		int cardsPerColumn = getCardsPerPage() / getColumnsPerPage();
		
		return getPageSize().getHeight() / cardsPerColumn;
	}

}
