package com.valuablecode.tool;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class PdfFactOrMythLayoutTest {
	
	final PageLayout pageLayout = mock(PageLayout.class);
	final FactOrMythDocument document = mock(FactOrMythDocument.class);
	
	final FactOrMythLayout sut = new PdfFactOrMythLayout(pageLayout, document);

	@Test
	public void adds_each_card_to_the_document() {
		FactOrMythCard card = new FactOrMythCard("dont_care");

		sut.addCard(card);

		verify(document).addCard(card);
	}
	
	@Test
	public void emits_page_when_page_is_complete() {
		FactOrMythCard card = new FactOrMythCard("dont_care");
		
		when(pageLayout.getCardsPerPage()).thenReturn(1);

		sut.addCard(card);

		verify(document).addCard(card);
		verify(document).emitPage();
	}
	
	@Test
	public void adds_empty_card_when_page_is_incomplete() {
		FactOrMythCard card = new FactOrMythCard("dont_care");
		
		when(pageLayout.getCardsPerPage()).thenReturn(2);
		when(pageLayout.getColumnsPerPage()).thenReturn(2);

		sut.addCard(card);
		sut.complete();

		verify(document).addCard(card);
		verify(document).emitPage();
	}
	
}
