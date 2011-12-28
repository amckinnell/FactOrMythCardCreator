package com.valuablecode.tool;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Test;

public class FactOrMythCardCreatorTest {
	
	final FactOrMythCardProvider cardProvider = mock(FactOrMythCardProvider.class);
	final FactOrMythDocument document = mock(FactOrMythDocument.class); 
	
	final FactOrMythCardCreator sut = new FactOrMythCardCreator(cardProvider, document);
	
	
	@Test public void
	nothing_to_do_when_there_are_no_fact_or_myth_cards() {
		when(cardProvider.getCards()).thenReturn(Collections.<FactOrMythCard>emptyList());

		sut.createCards();
	}

	@Test public void
	add_single_fact_or_myth_card_to_the_document() {
		FactOrMythCard card = new FactOrMythCard("");
		
		when(cardProvider.getCards()).thenReturn(singletonList(card));

		sut.createCards();
		
		verify(document).addCard(card);
	}

	@Test public void
	add_multiple_fact_or_myth_card_to_the_document() {
		FactOrMythCard card1 = new FactOrMythCard("1");
		FactOrMythCard card2 = new FactOrMythCard("2");
		FactOrMythCard card3 = new FactOrMythCard("3");
		
		when(cardProvider.getCards()).thenReturn(asList(card1, card2, card3));

		sut.createCards();
		
		// TODO [FactOrMythCards] AM Dec 28, 2011: How do we assert that we call in the correct order?
		verify(document).addCard(card1);
		verify(document).addCard(card2);
		verify(document).addCard(card3);
	}

}
