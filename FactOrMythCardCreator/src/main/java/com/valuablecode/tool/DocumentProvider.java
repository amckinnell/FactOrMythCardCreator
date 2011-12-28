package com.valuablecode.tool;

import com.itextpdf.text.Document;

/**
 * Knows how to provide a fully configured document.
 */
public interface DocumentProvider {

	Document getDocument();

}
