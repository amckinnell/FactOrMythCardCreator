package com.valuablecode.tool;

import java.util.List;


/**
 * Knows how to create a PDF containing Fact or Myth cards.
 */
public class FactOrMythCardCreator {

    private final FactOrMythCardProvider cardProvider;
    private final FactOrMythLayoutService layoutService;

    public FactOrMythCardCreator(FactOrMythCardProvider cardProvider, FactOrMythLayoutService layoutService) {
        this.cardProvider = cardProvider;
        this.layoutService = layoutService;
    }

    public void createCards() {
        for (FactOrMythCard card : cardsToCreate()) {
            layoutService.addCard(card);
        }

        layoutService.complete();
    }

    private List<FactOrMythCard> cardsToCreate() {
        List<FactOrMythCard> result = cardProvider.getCards();

        if (result.isEmpty()) {
            throw new RuntimeException("No cards to create");
        }

        return result;
    }

}

