package com.valuablecode.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Knows how to provide a list of Fact or Myth cards by reading from a file.
 */
public class FileBasedCardProvider implements FactOrMythCardProvider {
	
	// Hard coded path to the source of the Fact or Myth phrases.
	private static final String CARD_SOURCE = "/Users/alistair/Desktop/FactOrMyths.txt";
	
	private List<FactOrMythCard> cards = new ArrayList<FactOrMythCard>();
	
	public FileBasedCardProvider() {
		this(initializeCardSource());
	}

	private static BufferedReader initializeCardSource() {
		try {
			return new BufferedReader(new FileReader(new File(CARD_SOURCE)));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Can't find Fact or Myth file: " + CARD_SOURCE, e);
		}
	}

	public FileBasedCardProvider(BufferedReader cardSource) {
		try {
			initializeCards(cardSource);
		} catch (IOException e) {
			throw new RuntimeException("Can't read Fact or Myth phrases from: " + CARD_SOURCE, e);
		}
	}

	private void initializeCards(BufferedReader cardSource) throws IOException {
		String factOrMythPhrase = cardSource.readLine();
		
		while (null != factOrMythPhrase) {
			cards.add(createFactOrMythCard(factOrMythPhrase));
			
			factOrMythPhrase = cardSource.readLine();
		}
	}

	private FactOrMythCard createFactOrMythCard(String factOrMythPhrase) {
		return new FactOrMythCard(factOrMythPhrase.replaceAll("\\|", "\n"));
	}

	public List<FactOrMythCard> getCards() {
		return Collections.unmodifiableList(cards);
	}
	
}
