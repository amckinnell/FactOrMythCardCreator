package com.valuablecode.tool;

import com.valuablecode.tool.itext.PdfFactOrMythDocument;


/**
 * Allows cards to be added to a PDF layout suitable for printing.
 */
public class PdfLayoutService implements FactOrMythLayoutService {

    private final PageLayout pageLayout;
    private final FactOrMythDocument document;

    // The count of cards that we've added to the current page.
    private int cardCount = 0;


    public PdfLayoutService(FactOrMythConfiguration configuration) {
        this(configuration.getPageLayout(), new PdfFactOrMythDocument(configuration));
    }

    public PdfLayoutService(PageLayout pageLayout, FactOrMythDocument pdfDocument) {
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

        for (int i = 0; i < getMissingCoumnCount(); i++) {
            document.addCard(FactOrMythCard.aBlankCard);
        }
    }

    private int getMissingCoumnCount() {
        return pageLayout.getColumnsPerPage() - (cardCount % pageLayout.getColumnsPerPage());
    }

    private boolean isCompleteColumn() {
        return 0 == cardCount % pageLayout.getColumnsPerPage();
    }

}

