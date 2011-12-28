package com.valuablecode.tool;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class FactOrMythCardCreatorTest {
	
	final DocumentProvider documentProvider = mock(DocumentProvider.class); 
	
	final FactOrMythCardCreator sut = new FactOrMythCardCreator(documentProvider);
	
	
	@Test public void
	orchestrateCollaborators() {
		sut.create();
		
		when(documentProvider.getDocument()).thenReturn(null);
	}

}
