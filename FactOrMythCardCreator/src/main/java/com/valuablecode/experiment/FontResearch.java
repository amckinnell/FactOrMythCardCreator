package com.valuablecode.experiment;

import java.io.IOException;
import java.util.Set;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;


public class FontResearch {

    public static void main(String[] args) throws DocumentException, IOException {
    	new FontResearch().showFontInformation();
    }
 
    public void showFontInformation() throws DocumentException, IOException {
        FontFactory.register("/Library/Fonts/Noteworthy.ttc");
        
		Set<String> registeredFonts = FontFactory.getRegisteredFonts();
        
        for (String registeredFont : registeredFonts) {
			System.out.println(registeredFont);
		}
        
        Font cardFont = FontFactory.getFont("noteworthy-bold");
        cardFont.setSize(14f);
        cardFont.setStyle(Font.NORMAL);
        
        System.out.println("\n\nCardFont Information");
        System.out.println("Size:\t" + cardFont.getSize());
        System.out.println("Style:\t" + cardFont.getStyle());
    }

}
