package com.valuablecode.tool;

import static com.valuablecode.tool.PageLayoutBuilder.aPageLayout;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class PdfFactOrMythLayoutTest {
	
	final FactOrMythCard card_1 = new FactOrMythCard("card.1");
	final FactOrMythCard card_2 = new FactOrMythCard("card.2");

	final FactOrMythDocument document = mock(FactOrMythDocument.class);

	PageLayoutBuilder pageLayout;

	FactOrMythLayout sut;

	@Test
	public void adds_each_card_to_the_document() {
		pageLayout = aPageLayout().withCardsPerPage(1).withColumnsPerPage(1);
		sut = createFactOrMythLayout(pageLayout);

		sut.addCard(card_1);

		verify(document).addCard(card_1);
	}
	
	@Test
	public void emits_page_when_page_is_complete() {
		pageLayout = aPageLayout().withCardsPerPage(1).withColumnsPerPage(1);
		sut = createFactOrMythLayout(pageLayout);

		sut.addCard(card_1);

		verify(document).emitPage();
	}
	
	@Test
	public void adds_a_blank_card_when_column_is_incomplete() {
		pageLayout = aPageLayout().withCardsPerPage(6).withColumnsPerPage(3);
		sut = createFactOrMythLayout(pageLayout);

		sut.addCard(card_1);
		sut.complete();

		verify(document).addCard(FactOrMythCard.aBlankCard);
		verify(document).emitPage();
	}
	
	@Test
	public void does_not_add_empty_card_when_colum_is_complete() {
		pageLayout = aPageLayout().withCardsPerPage(6).withColumnsPerPage(2);
		sut = createFactOrMythLayout(pageLayout);

		sut.addCard(card_1);
		sut.addCard(card_2);
		sut.complete();

		verify(document, never()).addCard(FactOrMythCard.aBlankCard);
	}

	@Test
	public void does_nothing_when_page_is_complete() {
		pageLayout = aPageLayout().withCardsPerPage(2).withColumnsPerPage(2);
		sut = createFactOrMythLayout(pageLayout);

		sut.addCard(card_1);
		sut.addCard(card_2);
		sut.complete();

		verify(document, never()).addCard(FactOrMythCard.aBlankCard);
		verify(document, times(1)).emitPage();
	}
	
	private PdfFactOrMythLayout createFactOrMythLayout(PageLayoutBuilder pageLayout) {
		return new PdfFactOrMythLayout(pageLayout.build(), document);
	}
	
}
