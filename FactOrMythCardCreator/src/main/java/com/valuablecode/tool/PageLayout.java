package com.valuablecode.tool;

import com.itextpdf.text.Rectangle;

public interface PageLayout {
	
	// We're going to layout this many cards in each column.
	int getCardsPerColumn();
	
	// We're going to layout this many cards on each page.
	int getCardsPerPage();

	// We're going to layout the cards in this many columns on each page.
	int getColumnsPerPage();
	
	// We're going to layout cards using this page size.
	Rectangle getPageSize();

}
