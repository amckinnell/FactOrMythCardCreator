package com.valuablecode.tool;

import com.itextpdf.text.Rectangle;

/**
 * Defines the page layout for US Letter paper.
 */
public class LetterPageLayout implements PageLayout {

	private final int cardsPerPage = 6;
	private final int columnsPerPage = 2;
	private final int cardsPerColumn = cardsPerPage / columnsPerPage;

	//  Multiply by 72 to convert from inches to points.
	private static final int POINTS_PER_INCH = 72;

	// US Letter pages are 8.5 by 11 inches.
	private final Rectangle pageSize = new Rectangle(11.5f * POINTS_PER_INCH, 8 * POINTS_PER_INCH);


	public int getCardsPerColumn() {
		return cardsPerColumn;
	}

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
