package com.valuablecode.tool;


/**
 * Allows cards to be added to a PDF layout suitable for printing. 
 */
public class PdfFactOrMythLayout implements FactOrMythLayout {
	
	private final PageLayout pageLayout;
	private final FactOrMythDocument pdfDocument;

	// The count of cards that we've added to the current page.
	private int cardCount = 0;
	

	public PdfFactOrMythLayout(PageLayout pageLayout) {
		this.pageLayout = pageLayout;
		this.pdfDocument = new PdfFactOrMythDocument(pageLayout, new HardCodedCardFormat());
	}
	
	public void addCard(FactOrMythCard card) {
		pdfDocument.addCard(card);
		cardCount += 1;
		
		if (isCompletePage()) {
			pdfDocument.emitPage();
			cardCount = 0;
		}
	}

	private boolean isCompletePage() {
		return pageLayout.getCardsPerPage() == cardCount;
	}

	public void complete() {
		handleIncompletePage();
		
		pdfDocument.close();
	}

	private void handleIncompletePage() {
		if (isEmptyPage()) return;
		
		handleIncompleteColumn();
		
		pdfDocument.emitPage();
	}

	private boolean isEmptyPage() {
		return 0 == cardCount;
	}

	private void handleIncompleteColumn() {
		if (isCompleteColumn()) return;
		
		pdfDocument.addCard(new FactOrMythCard(""));
	}

	private boolean isCompleteColumn() {
		return 0 == cardCount % pageLayout.getColumnsPerPage();
	}

}
