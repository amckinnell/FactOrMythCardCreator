package com.valuablecode.tool;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class PdfFactOrMythLayoutTest {
	
	final FactOrMythCard card_1 = new FactOrMythCard("card.1");
	final FactOrMythCard card_2 = new FactOrMythCard("card.2");

	final PageLayout pageLayout = mock(PageLayout.class);
	final FactOrMythDocument document = mock(FactOrMythDocument.class);
	
	final FactOrMythLayout sut = new PdfFactOrMythLayout(pageLayout, document);

	@Test
	public void adds_each_card_to_the_document() {
		sut.addCard(card_1);

		verify(document).addCard(card_1);
	}
	
	@Test
	public void emits_page_when_page_is_complete() {
		when(pageLayout.getCardsPerPage()).thenReturn(1);

		sut.addCard(card_1);

		verify(document).emitPage();
	}
	
	@Test
	public void adds_a_blank_card_when_column_is_incomplete() {
		when(pageLayout.getCardsPerPage()).thenReturn(2);
		when(pageLayout.getColumnsPerPage()).thenReturn(3);

		sut.addCard(card_1);
		sut.complete();

		verify(document).addCard(FactOrMythCard.aBlankCard);
		verify(document).emitPage();
	}
	
	@Test
	public void does_not_add_empty_card_when_colum_is_complete() {
		when(pageLayout.getCardsPerPage()).thenReturn(6);
		when(pageLayout.getColumnsPerPage()).thenReturn(2);

		sut.addCard(card_1);
		sut.addCard(card_2);
		sut.complete();

		verify(document, never()).addCard(FactOrMythCard.aBlankCard);
	}
	
	@Test
	public void does_nothing_when_page_is_complete() {
		when(pageLayout.getCardsPerPage()).thenReturn(2);
		when(pageLayout.getColumnsPerPage()).thenReturn(2);

		sut.addCard(card_1);
		sut.addCard(card_2);
		sut.complete();
	}
	
}
