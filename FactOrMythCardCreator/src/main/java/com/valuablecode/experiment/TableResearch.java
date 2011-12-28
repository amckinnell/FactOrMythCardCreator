package com.valuablecode.experiment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class TableResearch {

	public static final String RESULT = "/Users/alistair/Desktop/TableResearch.pdf";

    public static void main(String[] args) throws DocumentException, IOException {
    	new TableResearch().createPdf(RESULT);
    }
 
	public void createPdf(String filename) throws DocumentException, IOException {
		Rectangle pagesize = new Rectangle(5 * 72, 3 * 72);	// Index card size
		Document document = new Document(pagesize, 0f, 0f, 0f, 0f);

		PdfWriter.getInstance(document, new FileOutputStream(filename));
		
		PdfPCell c = new PdfPCell(new Phrase("Fact or Myth Statement\n" + new Date()));
		c.setBorder(Rectangle.NO_BORDER);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		c.setFixedHeight(document.getPageSize().getHeight());
		
		PdfPTable t = new PdfPTable(1);
		t.addCell(c);
		
		document.open();
		document.add(t);
		document.newPage();
		document.add(t);
		document.close();
	}

}
