package com.weieditor.mds;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.weieditor.mds.model.Document;

public class SensitiveToContextSummarizerTest {

	@Test
	public void should_summarize_docs() {
		SensitiveToContextSummarizer summarizer = new SensitiveToContextSummarizer();
		List<Document> docs = new ArrayList<Document>();
		Document doc = summarizer.summarize(docs);
		assertNotNull(doc);
	}
}
