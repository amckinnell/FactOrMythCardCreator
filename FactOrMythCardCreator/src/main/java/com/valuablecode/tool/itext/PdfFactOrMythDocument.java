package com.valuablecode.tool.itext;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.valuablecode.tool.CardFormat;
import com.valuablecode.tool.FactOrMythCard;
import com.valuablecode.tool.FactOrMythDocument;
import com.valuablecode.tool.PageLayout;

public class PdfFactOrMythDocument implements FactOrMythDocument {
	
	// Hard coded path to the output PDF.
	private static final String OUTPUT_PDF = "/Users/alistair/Desktop/FactOrMyth.pdf";

	private final CardFormat cardFormat;
	private final PageLayout pageLayout;

	// The underlying PDF document.
	private Document document;

	// Used to control the card layout on the current page.
	private PdfPTable pageTable;


	public PdfFactOrMythDocument(PageLayout pageLayout, CardFormat cardFormat) {
		this.pageLayout = pageLayout;
		this.cardFormat = cardFormat;
		
		initializePage();
	}
	
	public void close() {
		document.close();
	}

	public void emitPage() {
		try {
			document.add(pageTable);
			document.newPage();
		} catch (DocumentException e) {
			throw new RuntimeException("Can't layout page", e);
		}
		
		initializePage();
	}

	private void initializePage() {
		pageTable = new PdfPTable(pageLayout.getColumnsPerPage());
		pageTable.setWidthPercentage(100f);
	}

	public void addCard(FactOrMythCard card) {
		guaranteeDocumentIsInitialized();

		pageTable.addCell(createCardLayoutFor(card));
	}

	private void guaranteeDocumentIsInitialized() {
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

	private PdfPCell createCardLayoutFor(FactOrMythCard card) {
		PdfPCell result = new PdfPCell(new Phrase(card.getCardText(), cardFormat.getFont()));
		
		result.setBorder(Rectangle.NO_BORDER);
		result.setHorizontalAlignment(Element.ALIGN_CENTER);
		result.setVerticalAlignment(Element.ALIGN_MIDDLE);
		result.setFixedHeight(pageLayout.getCardHeight());

		return result;
	}
	
}
