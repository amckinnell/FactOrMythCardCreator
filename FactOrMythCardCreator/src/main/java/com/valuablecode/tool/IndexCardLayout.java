package com.valuablecode.tool;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * Allows cards to be added to a PDF document suitable for printing to index cards.
 */
public class IndexCardLayout implements FactOrMythDocument {
	
	//  Multiply by 72 to convert from inches to points.
	private static final int POINTS_PER_INCH = 72;

	// Index cards are 3 by 5 inches.
	private static final Rectangle INDEX_CARD_SIZE = new Rectangle(5 * POINTS_PER_INCH, 3 * POINTS_PER_INCH);
	
	// Hard coded path to the result PDF.
	private static final String RESULT_PDF = "/Users/alistair/Desktop/IndexCards.pdf";

	private Document document;
	private final CardFormat cardFormat;


	public IndexCardLayout() {
		this.cardFormat = new HardCodedCardFormat();
	}
	
	public void close() {
		if (null == document) {
			throw new RuntimeException("Can't close document without first adding a card.");
		}

		document.close();
	}

	public void addCard(FactOrMythCard card) {
		guaranteeThatDocumentIsInitialized();
		
		addCardLayoutToDocument(createCardLayout(card));
	}

	private void addCardLayoutToDocument(Element layout) {
		try {
			document.add(layout);
			document.newPage();
		} catch (DocumentException e) {
			throw new RuntimeException("Can't layout card", e);
		}
	}

	private Element createCardLayout(FactOrMythCard card) {
		PdfPTable result = new PdfPTable(1);
		
		result.addCell(createLayoutCell(card));
	
		return result;
	}

	private PdfPCell createLayoutCell(FactOrMythCard card) {
		PdfPCell result = new PdfPCell(new Phrase(card.getCardText(), cardFormat.getFont()));
		
		result.setBorder(Rectangle.NO_BORDER);
		result.setHorizontalAlignment(Element.ALIGN_CENTER);
		result.setVerticalAlignment(Element.ALIGN_MIDDLE);
		result.setFixedHeight(document.getPageSize().getHeight());

		return result;
	}

	private void guaranteeThatDocumentIsInitialized() {
		if (null == document) {
			document = initializeDocument();
			
			document.open();
		}
	}

	private Document initializeDocument() {
		Document document = new Document(INDEX_CARD_SIZE, 0f, 0f, 0f, 0f);

		try {
			PdfWriter.getInstance(document, new FileOutputStream(RESULT_PDF));
		} catch (Exception e) {
			throw new RuntimeException("Unable to initialize document", e);
		}
		
		return document;
	}

}

