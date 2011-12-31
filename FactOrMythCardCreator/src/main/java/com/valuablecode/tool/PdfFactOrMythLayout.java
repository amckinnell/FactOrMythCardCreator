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
 * Allows cards to be added to a PDF layout suitable for printing. 
 */
public class PdfFactOrMythLayout implements FactOrMythLayout {
	
	// Hard coded path to the output PDF.
	private static final String OUTPUT_PDF = "/Users/alistair/Desktop/FactOrMyth.pdf";

	// Collaborators
	private final CardFormat cardFormat;
	private final PageLayout pageLayout;

	// Wrap the layout.
	private Document document;

	// Used to control the card layout on the current page.
	private PdfPTable layoutTable;

	// The count of cards that we've added to the current page.
	private int cardCount;
	

	public PdfFactOrMythLayout(PageLayout pageLayout) {
		this.pageLayout = pageLayout;
		this.cardFormat = new HardCodedCardFormat();
		
		initializeSinglePageLayout();
	}
	
	public void close() {
		if (null == document) {
			throw new RuntimeException("Can't close layout without first adding a card.");
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
		if (0 == cardCount % pageLayout.getColumnsPerPage()) return;
		
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
		
		if (pageLayout.getCardsPerPage() == cardCount) {
			emitSinglePage();
			initializeSinglePageLayout();
		}
	}

	private void initializeSinglePageLayout() {
		layoutTable = new PdfPTable(pageLayout.getColumnsPerPage());
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
		result.setFixedHeight(document.getPageSize().getHeight() / pageLayout.getCardsPerColumn());

		return result;
	}

	private void guaranteeThatDocumentIsInitialized() {
		if (null == document) {
			document = initializeDocument();
			
			document.open();
		}
	}

	private Document initializeDocument() {
		Document document = new Document(pageLayout.getPageSize(), 0f, 0f, 0f, 0f);

		try {
			PdfWriter.getInstance(document, new FileOutputStream(OUTPUT_PDF));
		} catch (Exception e) {
			throw new RuntimeException("Unable to initialize layout", e);
		}
		
		return document;
	}

}
