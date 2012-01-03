package com.valuablecode.tool;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Immutable value object that knows all there is to know about a particular Fact or Myth card.
 */
public class FactOrMythCard {
	
	public static final FactOrMythCard aBlankCard = new FactOrMythCard("");
	
	private final String cardText;
	
	public String getCardText() {
		return cardText;
	}

	public FactOrMythCard(String cardText) {
		this.cardText = cardText;
	}
	
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append(cardText)
			.toString();
	}

}
