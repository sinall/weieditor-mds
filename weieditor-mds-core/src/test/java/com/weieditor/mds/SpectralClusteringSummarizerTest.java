package com.weieditor.mds;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;

public class SpectralClusteringSummarizerTest {

	@Test
	public void should_summarize_docs() {
		SpectralClusteringSummarizer summarizer = new SpectralClusteringSummarizer();
		MultiDocument multiDoc = new MultiDocument();
		Document doc = summarizer.summarize(multiDoc);
		assertNotNull(doc);
	}
}
