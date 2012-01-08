package com.valuablecode.tool;

import static com.itextpdf.text.Utilities.inchesToPoints;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Rectangle;

/**
 * Defines the page layout for US Letter paper.
 */
public class LetterPageLayout extends BasePageLayout {

	// US Letter pages are 8.5 by 11 inches. This orientation is landscape.
	private final static Rectangle pageSize = new Rectangle(inchesToPoints(11.5f), inchesToPoints(8f));

	private static final BaseColor LIGHT_GRAY = new BaseColor(211, 211, 211);

	
	public int getCardsPerPage() {
		return 6;
	}

	public int getColumnsPerPage() {
		return 2;
	}

	public Rectangle getPageSize() {
		return pageSize;
	}

	public int getBorder() {
		return Rectangle.NO_BORDER;
	}

	public BaseColor getBorderColor() {
		return LIGHT_GRAY;
	}

}
