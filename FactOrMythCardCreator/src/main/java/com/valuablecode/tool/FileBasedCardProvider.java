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
	
	private static final String FACT_OR_MYTH_TITLE = "Fact or Myth?";
	
	private List<FactOrMythCard> cards = new ArrayList<FactOrMythCard>();
	

	public FileBasedCardProvider(FactOrMythConfiguration configuration) {
		this(configuration.getCardSourceFileName());
	}

	public FileBasedCardProvider(String cardSourceFileName) {
		this(cardSourceFileName, initializeCardSource(cardSourceFileName));
	}

	private static BufferedReader initializeCardSource(String cardSourceFileName) {
		try {
			return new BufferedReader(new FileReader(new File(cardSourceFileName)));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Can't find Fact or Myth file: " + cardSourceFileName, e);
		}
	}

	public FileBasedCardProvider(String cardSourceFileName, BufferedReader cardSource) {
		try {
			initializeCards(cardSource);
		} catch (IOException e) {
			throw new RuntimeException("Can't read Fact or Myth phrases from: " + cardSourceFileName, e);
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
