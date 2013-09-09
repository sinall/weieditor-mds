package com.weieditor.mds;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.weieditor.mds.model.Article;
import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;

public class SpectralClusteringSummarizerTest {

	@Test
	public void should_summarize_single_doc() {
		SpectralClusteringSummarizer summarizer = new SpectralClusteringSummarizer(
				10);
		MultiDocument multiDoc = new MultiDocument();
		ArticleBuilder articleBuilder = new ArticleBuilder();
		Article article = articleBuilder.withContent(
				"李克强:新型城镇化要真正惠及农民\n要让新型城镇化路子走好走顺\n城镇化好，城镇化好，城镇化好").build();
		Document rawDoc = new DocumentParserImpl(new IctclasSegmenter())
				.parse(article);
		multiDoc.add(rawDoc);
		Document doc = summarizer.summarize(multiDoc);
		assertNotNull(doc);
		assertThat(doc.getParagraphs().get(0).getSentences().get(0)
				.getContent(), equalTo("城镇化好，城镇化好，城镇化好。"));
	}

}
