package com.valuablecode.tool;

import static com.itextpdf.text.Utilities.inchesToPoints;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Rectangle;

/**
 * Defines the custom card page layout for US Letter paper.
 *
 * Note: the idea is that this class can be modified to support special cases.
 */
public class CustomPageLayout extends BasePageLayout {

    // US Letter pages are 8.5 by 11 inches. This orientation is landscape.
    private final static Rectangle pageSize = new Rectangle(inchesToPoints(11.5f), inchesToPoints(8f));

    private static final BaseColor DIM_GRAY = new BaseColor(105, 105, 105);


    public int getCardsPerPage() {
	    return 18;
	}

    public int getColumnsPerPage() {
        return 3;
    }

    public Rectangle getPageSize() {
        return pageSize;
    }

    public int getBorder() {
        return Rectangle.BOX;
    }

    public BaseColor getBorderColor() {
        return DIM_GRAY;
    }

}

