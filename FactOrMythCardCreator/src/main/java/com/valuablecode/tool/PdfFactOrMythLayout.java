package com.valuablecode.tool;


/**
 * Allows cards to be added to a PDF layout suitable for printing. 
 */
public class PdfFactOrMythLayout implements FactOrMythLayout {
	
	private final PageLayout pageLayout;
	private final FactOrMythDocument document;

	// The count of cards that we've added to the current page.
	private int cardCount = 0;
	

	public PdfFactOrMythLayout(PageLayout pageLayout) {
		this(pageLayout, new PdfFactOrMythDocument(pageLayout, new HardCodedCardFormat()));
	}
	
	public PdfFactOrMythLayout(PageLayout pageLayout, FactOrMythDocument pdfDocument) {
		this.pageLayout = pageLayout;
		this.document = pdfDocument;
	}

	public void addCard(FactOrMythCard card) {
		document.addCard(card);
		cardCount += 1;
		
		if (isCompletePage()) {
			document.emitPage();
			cardCount = 0;
		}
	}

	private boolean isCompletePage() {
		return pageLayout.getCardsPerPage() == cardCount;
	}

	public void complete() {
		handleIncompletePage();
		
		document.close();
	}

	private void handleIncompletePage() {
		if (isEmptyPage()) return;
		
		handleIncompleteColumn();
		
		document.emitPage();
	}

	private boolean isEmptyPage() {
		return 0 == cardCount;
	}

	private void handleIncompleteColumn() {
		if (isCompleteColumn()) return;
		
		document.addCard(new FactOrMythCard(""));
	}

	private boolean isCompleteColumn() {
		return 0 == cardCount % pageLayout.getColumnsPerPage();
	}

}
