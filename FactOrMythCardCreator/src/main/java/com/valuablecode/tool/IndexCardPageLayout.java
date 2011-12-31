package com.valuablecode.tool;

import com.itextpdf.text.Rectangle;

/**
 * Defines the page layout for index cards.
 */
public class IndexCardPageLayout implements PageLayout {

	private final int cardsPerPage = 1;
	private final int columnsPerPage = 1;

	//  Multiply by 72 to convert from inches to points.
	private static final int POINTS_PER_INCH = 72;

	// Index cards are 3 by 5 inches.
	private final Rectangle pageSize = new Rectangle(5 * POINTS_PER_INCH, 3 * POINTS_PER_INCH);


	public int getCardsPerPage() {
		return cardsPerPage;
	}

	public int getColumnsPerPage() {
		return columnsPerPage;
	}

	public Rectangle getPageSize() {
		return pageSize;
	}

}
