package com.valuablecode.tool;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class FactOrMythCardCreatorTest {
	
	final FactOrMythCard card_1 = new FactOrMythCard("card.1");
	final FactOrMythCard card_2 = new FactOrMythCard("card.2");
	final FactOrMythCard card_3 = new FactOrMythCard("card.3");
	
	final FactOrMythCardProvider cardProvider = mock(FactOrMythCardProvider.class);
	final FactOrMythLayout layout = mock(FactOrMythLayout.class);
	
	final FactOrMythCardCreator sut = new FactOrMythCardCreator(cardProvider, layout);
	
	final InOrder inOrder = Mockito.inOrder(layout);

	
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
		when(cardProvider.getCards()).thenReturn(singletonList(card_1));

		sut.createCards();
		
		inOrder.verify(layout).addCard(card_1);
		inOrder.verify(layout).complete();
	}

	@Test public void
	add_multiple_fact_or_myth_card_to_the_document() {
		when(cardProvider.getCards()).thenReturn(asList(card_1, card_2, card_3));

		sut.createCards();
		
		inOrder.verify(layout).addCard(card_1);
		inOrder.verify(layout).addCard(card_2);
		inOrder.verify(layout).addCard(card_3);

		inOrder.verify(layout).complete();
	}

}
