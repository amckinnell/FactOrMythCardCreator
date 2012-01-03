package com.valuablecode.tool;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

public class FileBasedCardProviderTest {

	@Test public void
	creates_single_card() {
		FileBasedCardProvider sut = createFileBasedCardProvider("First Card");
		List<FactOrMythCard> cards = sut.getCards();
		
		assertThat(cards.size(), equalTo(1));
		assertThat(cards.get(0).getCardText(), equalTo("First Card"));
	}

	@Test public void
	creates_multiple_cards() {
		FileBasedCardProvider sut = createFileBasedCardProvider("First Card\nSecond Card");
		List<FactOrMythCard> cards = sut.getCards();
		
		assertThat(cards.size(), equalTo(2));
		assertThat(cards.get(0).getCardText(), equalTo("First Card"));
		assertThat(cards.get(1).getCardText(), equalTo("Second Card"));
	}

	@Test public void
	creates_multiline_cards() {
		FileBasedCardProvider sut = createFileBasedCardProvider("First Card|Extra Line|Extra Extra Line\nSecond Card");
		List<FactOrMythCard> cards = sut.getCards();
		
		assertThat(cards.size(), equalTo(2));
		assertThat(cards.get(0).getCardText(), equalTo("First Card\nExtra Line\nExtra Extra Line"));
		assertThat(cards.get(1).getCardText(), equalTo("Second Card"));
	}

	private FileBasedCardProvider createFileBasedCardProvider(String cardSource) {
		BufferedReader cardSourceReader = new BufferedReader(new StringReader(cardSource));	
		
		return new FileBasedCardProvider("Card Source File Name", cardSourceReader);
	}
}
