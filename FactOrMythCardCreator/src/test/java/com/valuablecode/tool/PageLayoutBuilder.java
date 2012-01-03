package com.valuablecode.tool;

import com.itextpdf.text.Rectangle;

public class PageLayoutBuilder implements TestDataBuilder<PageLayout> {
	
	private final int cardsPerPage;
	private final int columnsPerPage;
	

	public static PageLayoutBuilder aPageLayout() {
		return new PageLayoutBuilder(0, 0);
	}
	
	public PageLayoutBuilder(int cardsPerPage, int columnsPerPage) {
		this.cardsPerPage = cardsPerPage;
		this.columnsPerPage = columnsPerPage;
	}

	public PageLayout build() {
		return new BasePageLayout() {
			
			public Rectangle getPageSize() {
				throw new UnsupportedOperationException();
			}
			
			public int getColumnsPerPage() {
				return columnsPerPage;
			}
			
			public int getCardsPerPage() {
				return cardsPerPage;
			}
		};
	}
	
	public PageLayoutBuilder withCardsPerPage(int cardsPerPage) {
		return new PageLayoutBuilder(cardsPerPage, columnsPerPage);
	}

	public PageLayoutBuilder withColumnsPerPage(int columnsPerPage) {
		return new PageLayoutBuilder(cardsPerPage, columnsPerPage);
	}

}
