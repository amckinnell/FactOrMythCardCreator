package com.valuablecode.tool;

import static com.itextpdf.text.Utilities.inchesToPoints;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Rectangle;

/**
 * Defines the page layout for index cards.
 */
public class IndexCardPageLayout extends BasePageLayout {

	// Index cards are 3 by 5 inches.
	private final static Rectangle pageSize = new Rectangle(inchesToPoints(5), inchesToPoints(3));

	private static final BaseColor GRAY = new BaseColor(190, 190, 190);

	
	public int getCardsPerPage() {
		return 1;
	}

	public int getColumnsPerPage() {
		return 1;
	}

	public Rectangle getPageSize() {
		return pageSize;
	}

	public int getBorder() {
		return Rectangle.NO_BORDER;
	}

	public BaseColor getBorderColor() {
		return GRAY;
	}


}
