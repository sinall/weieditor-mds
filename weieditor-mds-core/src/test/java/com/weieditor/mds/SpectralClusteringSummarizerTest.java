package com.weieditor.mds;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.weieditor.mds.model.Article;
import com.weieditor.mds.model.MultiArticle;

public class SpectralClusteringSummarizerTest {

	@Test
	public void should_summarize_single_doc() {
		SpectralClusteringSummarizer summarizer = new SpectralClusteringSummarizer();
		MultiArticle multiArticle = new MultiArticle();
		Article article = summarizer.summarize(multiArticle);
		assertNotNull(article);
	}

}
