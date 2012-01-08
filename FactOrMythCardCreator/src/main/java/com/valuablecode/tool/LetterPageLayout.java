package com.valuablecode.tool;

import static com.itextpdf.text.Utilities.inchesToPoints;

import com.itextpdf.text.Rectangle;

/**
 * Defines the page layout for US Letter paper.
 */
public class LetterPageLayout extends BasePageLayout {

	// US Letter pages are 8.5 by 11 inches. This orientation is landscape.
	private final static Rectangle pageSize = new Rectangle(inchesToPoints(11.5f), inchesToPoints(8f));

	public int getCardsPerPage() {
		return 6;
	}

	public int getColumnsPerPage() {
		return 2;
	}

	public Rectangle getPageSize() {
		return pageSize;
	}

}
