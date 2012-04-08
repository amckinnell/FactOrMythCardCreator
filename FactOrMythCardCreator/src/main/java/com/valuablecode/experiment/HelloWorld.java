package com.valuablecode.experiment;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class HelloWorld {

    public static final String RESULT = "/Users/alistair/Desktop/HelloWorld.pdf";

    public static void main(String[] args) throws DocumentException, IOException {
    	new HelloWorld().createPdf(RESULT);
    }

    public void createPdf(String filename) throws DocumentException, IOException {
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(filename));

        document.open();

        FontFactory.register("/Library/Fonts/Noteworthy.ttc");

        Font cardFont = FontFactory.getFont("noteworthy-bold", FontFactory.defaultEncoding, false);
        cardFont.setSize(14f);
        cardFont.setStyle(Font.NORMAL);

        Paragraph p = new Paragraph("Hello World!", cardFont);
        p.setAlignment(Element.ALIGN_CENTER);

        document.add(p);

        document.close();
    }

}

