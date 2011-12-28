package com.valuablecode.tool;


/**
 * Knows how to create a PDF containing Fact or Myth cards.
 */
public class FactOrMythCardCreator {
	
	private final DocumentProvider documentProvider;

	public FactOrMythCardCreator() {
		this(new PdfDocumentProvider());
	}
	
	public FactOrMythCardCreator(DocumentProvider documentProvider) {
		this.documentProvider = documentProvider;
	}

	public static void main(String[] args) {
		FactOrMythCardCreator cardCreator = new FactOrMythCardCreator();
		
		cardCreator.create();
	}

	public void create() {
		documentProvider.getDocument();
	}

}
