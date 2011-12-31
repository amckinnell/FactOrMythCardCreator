package com.valuablecode.experiment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class TableResearch {

	public static final String INDEX_CARD_PDF = "/Users/alistair/Desktop/IndexCardTableResearch.pdf";
	public static final String PAGED_PDF = "/Users/alistair/Desktop/PagedTableResearch.pdf";

    public static void main(String[] args) throws DocumentException, IOException {
    	TableResearch tableResearch = new TableResearch();
    	
		tableResearch.createIndexCardPdf(INDEX_CARD_PDF);
		tableResearch.createPagedPdf(PAGED_PDF);
    }
 
	public void createIndexCardPdf(String filename) throws DocumentException, IOException {
		Rectangle pageSize = new Rectangle(5 * 72, 3 * 72);
		Document document = new Document(pageSize, 0f, 0f, 0f, 0f);

		PdfWriter.getInstance(document, new FileOutputStream(filename));
		
		PdfPCell c = new PdfPCell(new Phrase("Fact or Myth Statement\n" + new Date()));
		c.setBorder(Rectangle.NO_BORDER);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		c.setFixedHeight(document.getPageSize().getHeight());
		
		PdfPTable t = new PdfPTable(1);
		t.setWidthPercentage(100f);

		t.addCell(c);
		
		document.open();
		document.add(t);
		document.newPage();
		document.add(t);
		document.close();
	}

	private void createPagedPdf(String filename) throws DocumentException, IOException {
		Document document = new Document(PageSize.LETTER, 0f, 0f, 0f, 0f);

		PdfWriter.getInstance(document, new FileOutputStream(filename));
		PdfPCell c = new PdfPCell(new Phrase("Fact or Myth Statement\n" + new Date()));
		c.setBorder(Rectangle.NO_BORDER);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		c.setFixedHeight(document.getPageSize().getHeight() / 3);	// Six cells per page.
		
		PdfPTable t = new PdfPTable(2);
		t.setWidthPercentage(100f);
		
		t.addCell(c);
		t.addCell(c);
		t.addCell(c);
		t.addCell(c);
		t.addCell(c);
		t.completeRow();
		
		document.open();
		document.add(t);
		document.close();
	}

}
