package com.valuablecode.tool;

import java.util.List;

/**
 * Knows how to provide a list of Fact or Myth cards.
 */
public interface FactOrMythCardProvider {

    List<FactOrMythCard> getCards();

}
