package com.valuablecode.tool;

import static com.itextpdf.text.Utilities.inchesToPoints;

import com.itextpdf.text.Rectangle;

/**
 * Defines the page layout for index cards.
 */
public class IndexCardPageLayout extends BasePageLayout {

	// Index cards are 3 by 5 inches.
	private final static Rectangle pageSize = new Rectangle(inchesToPoints(5), inchesToPoints(3));


	public int getCardsPerPage() {
		return 1;
	}

	public int getColumnsPerPage() {
		return 1;
	}

	public Rectangle getPageSize() {
		return pageSize;
	}

}
