package com.valuablecode.tool;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class IndexCardPageLayoutTest {

    @Test public void
    card_height_same_as_page_height() {
        IndexCardPageLayout sut = new IndexCardPageLayout();

        assertThat(sut.getCardHeight(), equalTo(sut.getPageSize().getHeight()));
    }

}
