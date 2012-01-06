package com.valuablecode.tool;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.commons.configuration.Configuration;
import org.junit.Test;

import com.valuablecode.tool.itext.ConfigurableCardFormat;

public class PropertyFileConfigurationTest {

	Configuration configuration = mock(Configuration.class);
	
	final PropertyFileConfiguration sut = new PropertyFileConfiguration(configuration);

	@Test public void
	configures_the_card_source_file_name() {
		when(configuration.getString("cardSourceFileName")).thenReturn("CARD SOURCE FILE NAME VALUE");
		
		assertThat(sut.getCardSourceFileName(), equalTo("CARD SOURCE FILE NAME VALUE"));
	}
	
	@Test public void
	configures_the_output_file_name() {
		when(configuration.getString("outputFileName")).thenReturn("OUTPUT FILE NAME VALUE");
		
		assertThat(sut.getOutputFileName(), equalTo("OUTPUT FILE NAME VALUE"));
	}
	
	@Test public void
	configures_the_page_layout_for_letter_specification() {
		when(configuration.getString("pageLayout")).thenReturn("Letter");
		
		assertThat(sut.getPageLayout(), instanceOf(LetterPageLayout.class));
	}
	
	@Test public void
	configures_the_page_layout_for_index_card_specification() {
		when(configuration.getString("pageLayout")).thenReturn("IndexCard");
		
		assertThat(sut.getPageLayout(), instanceOf(IndexCardPageLayout.class));
	}
	
	@Test public void
	configures_the_page_layout_for_index_card_specification_with_spaces() {
		when(configuration.getString("pageLayout")).thenReturn(" IndexCard  ");
		
		assertThat(sut.getPageLayout(), instanceOf(IndexCardPageLayout.class));
	}
	
	@Test public void
	configures_the_card_format() {
		assertThat(sut.getCardFormat(), instanceOf(ConfigurableCardFormat.class));
	}
	
	@Test public void
	configures_the_card_font_path() {
		when(configuration.getString("font.fileName")).thenReturn("CARD FONT PATH VALUE");
		
		assertThat(sut.getCardFontPath(), equalTo("CARD FONT PATH VALUE"));
	}
	
	@Test public void
	configures_the_card_font_name() {
		when(configuration.getString("font.name")).thenReturn("CARD FONT NAME VALUE");
		
		assertThat(sut.getCardFontName(), equalTo("CARD FONT NAME VALUE"));
	}
	
	@Test public void
	configures_the_font_size() {
		when(configuration.getFloat("font.size")).thenReturn(14f);
		
		assertThat(sut.getCardFontSize(), equalTo(14f));
	}
	
	@Test public void
	fails_for_an_invalid_page_layout_specification() {
		 try {
			when(configuration.getString("pageLayout")).thenReturn("_INVALID_PAGE_LAYOUT_SPECIFICATION_");
			sut.getPageLayout();
			fail();
		} catch (RuntimeException expected) {
			assertThat(expected.getMessage(), containsString("_INVALID_PAGE_LAYOUT_SPECIFICATION_"));
		}
	}
	
	@Test public void
	fails_for_an_invalid_file_name() {
		 try {
			new PropertyFileConfiguration("_INVALID_FILE_NAME_"); 
			fail();
		} catch (RuntimeException expected) {
			assertThat(expected.getMessage(), containsString("_INVALID_FILE_NAME_"));
		}
	}
	
}
