package com.weieditor.mds;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.weieditor.mds.model.Document;

public class SpectralClusteringSummarizerTest {

	@Test
	public void should_summarize_docs() {
		SpectralClusteringSummarizer summarizer = new SpectralClusteringSummarizer();
		List<Document> docs = new ArrayList<Document>();
		Document doc = summarizer.summarize(docs);
		assertNotNull(doc);
	}
}
