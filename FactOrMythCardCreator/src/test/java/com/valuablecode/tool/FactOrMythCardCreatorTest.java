package com.valuablecode.tool;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.matchers.JUnitMatchers.containsString;
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
	fail_when_there_are_no_fact_or_myth_cards() {
		when(cardProvider.getCards()).thenReturn(Collections.<FactOrMythCard>emptyList());
		
		try {
			sut.createCards();
			fail("Expected to fail when there are no cards to create");
		} catch (RuntimeException expected) {
			assertThat(expected.getMessage(), containsString("No cards to create"));
		}
	}

	@Test public void
	add_single_fact_or_myth_card_to_the_document() {
		FactOrMythCard card = aCard("");
		
		when(cardProvider.getCards()).thenReturn(singletonList(card));

		sut.createCards();
		
		verify(document).addCard(card);
	}

	@Test public void
	add_multiple_fact_or_myth_card_to_the_document() {
		FactOrMythCard card1 = aCard("1");
		FactOrMythCard card2 = aCard("2");
		FactOrMythCard card3 = aCard("3");
		
		when(cardProvider.getCards()).thenReturn(asList(card1, card2, card3));

		sut.createCards();
		
		// TODO [FactOrMythCards] AM Dec 28, 2011: How do we assert that we call in the correct order?
		verify(document).addCard(card1);
		verify(document).addCard(card2);
		verify(document).addCard(card3);
	}
	
	private static FactOrMythCard aCard(String cardText) {
		return new FactOrMythCard(cardText);
	}

}
