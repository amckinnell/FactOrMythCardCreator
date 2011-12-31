package com.valuablecode.tool;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * Allows cards to be added to a PDF document suitable for printing to regular pages. 
 * Note: several cards will appear on each page.
 */
public class PagedLayout implements FactOrMythDocument {
	
	// We're going to layout this many cards on each page.
	private static final int CARDS_PER_PAGE = 6;

	// We're going to layout the cards in this many columns on each page.
	private static final int COLUMNS_PER_PAGE = 2;

	// We're going to layout this many cards in each column.
	private static final int CARDS_PER_COLUMN = CARDS_PER_PAGE / COLUMNS_PER_PAGE;

	// Use US Letter at the page size.
	private static final Rectangle PAGED_SIZE = PageSize.LETTER;
	
	// Hard coded path to the result PDF.
	private static final String RESULT_PDF = "/Users/alistair/Desktop/Paged.pdf";

	private Document document;
	private final CardFormat cardFormat;

	// The count of cards that we've added to the current page.
	private int cardCount;
	
	// Used to control the card layout on the current page.
	private PdfPTable layoutTable;

	public PagedLayout() {
		this.cardFormat = new HardCodedCardFormat();
		
		initializeSinglePageLayout();
	}
	
	public void close() {
		if (null == document) {
			throw new RuntimeException("Can't close document without first adding a card.");
		}
		
		handleIncompletePage();
		
		document.close();
	}

	private void handleIncompletePage() {
		if (0 == cardCount) return;
		
		handleIncompleteColumn();
		
		emitSinglePage();
	}

	private void handleIncompleteColumn() {
		if (0 == cardCount % COLUMNS_PER_PAGE) return;
		
		layoutTable.addCell(createEmptyCard());
	}

	private PdfPCell createEmptyCard() {
		return createCardLayout(new FactOrMythCard(""));
	}

	public void addCard(FactOrMythCard card) {
		guaranteeThatDocumentIsInitialized();
		
		addCardLayoutToDocument(createCardLayout(card));
	}

	private void addCardLayoutToDocument(PdfPCell layout) {
		layoutTable.addCell(layout);
		cardCount += 1;
		
		if (CARDS_PER_PAGE == cardCount) {
			emitSinglePage();
			initializeSinglePageLayout();
		}
	}

	private void initializeSinglePageLayout() {
		layoutTable = new PdfPTable(COLUMNS_PER_PAGE);
		layoutTable.setWidthPercentage(100f);
		
		cardCount = 0;
	}

	private void emitSinglePage() {
		try {
			document.add(layoutTable);
			document.newPage();
		} catch (DocumentException e) {
			throw new RuntimeException("Can't layout page", e);
		}
	}

	private PdfPCell createCardLayout(FactOrMythCard card) {
		PdfPCell result = new PdfPCell(new Phrase(card.getCardText(), cardFormat.getFont()));
		
		result.setBorder(Rectangle.NO_BORDER);
		result.setHorizontalAlignment(Element.ALIGN_CENTER);
		result.setVerticalAlignment(Element.ALIGN_MIDDLE);
		result.setFixedHeight(document.getPageSize().getHeight() / CARDS_PER_COLUMN);

		return result;
	}

	private void guaranteeThatDocumentIsInitialized() {
		if (null == document) {
			document = initializeDocument();
			
			document.open();
		}
	}

	private Document initializeDocument() {
		Document document = new Document(PAGED_SIZE, 0f, 0f, 0f, 0f);

		try {
			PdfWriter.getInstance(document, new FileOutputStream(RESULT_PDF));
		} catch (Exception e) {
			throw new RuntimeException("Unable to initialize document", e);
		}
		
		return document;
	}

}
