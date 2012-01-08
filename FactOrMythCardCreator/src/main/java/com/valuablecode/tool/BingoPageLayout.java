package com.valuablecode.tool;

import static com.itextpdf.text.Utilities.inchesToPoints;

import com.itextpdf.text.Rectangle;

/**
 * Defines the bingo card page layout for US Letter paper.
 */
public class BingoPageLayout extends BasePageLayout {

	// US Letter pages are 8.5 by 11 inches. This orientation is landscape.
	private final static Rectangle pageSize = new Rectangle(inchesToPoints(11.5f), inchesToPoints(8f));

	public int getCardsPerPage() {
		return 12;
	}

	public int getColumnsPerPage() {
		return 3;
	}

	public Rectangle getPageSize() {
		return pageSize;
	}

}
