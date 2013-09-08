package com.weieditor.mds;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;

public class SensitiveToContextSummarizerTest {

	@Test
	public void should_summarize_docs() {
		SensitiveToContextSummarizer summarizer = new SensitiveToContextSummarizer();
		MultiDocument multiDoc = new MultiDocument();
		Document doc = summarizer.summarize(multiDoc);
		assertNotNull(doc);
	}
}
