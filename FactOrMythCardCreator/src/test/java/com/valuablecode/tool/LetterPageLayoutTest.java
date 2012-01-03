package com.valuablecode.tool;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class LetterPageLayoutTest {

	@Test public void
	card_height_same_as_page_height_divided_by_cards_in_column() {
		LetterPageLayout sut = new LetterPageLayout();
		
		assertThat(sut.getCardHeight(), equalTo(sut.getPageSize().getHeight() / 3));
	}
	
}
