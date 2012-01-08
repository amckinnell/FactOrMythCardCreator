package com.valuablecode.tool;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

public interface CardFormat {

	Font getFont();
	int getBorder();
	BaseColor getBorderColor();

}
