package com.weieditor.mds;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.weieditor.mds.model.Article;
import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;
import com.weieditor.mds.model.Paragraph;
import com.weieditor.mds.model.Sentence;

public class SensitiveToContextSummarizerTest {

	@Test
	public void should_summarize_single_doc() {
		SensitiveToContextSummarizer summarizer = new SensitiveToContextSummarizer(
				10);
		MultiDocument multiDoc = new MultiDocument();
		addDoc(multiDoc, 1, "李克强:新型城镇化要真正惠及农民\n要让新型城镇化路子走好走顺\n城镇化好，城镇化好，城镇化好");
		Document doc = summarizer.summarize(multiDoc);

		List<Sentence> sentences = doc.getParagraphs().get(0).getSentences();
		assertThat(sentences.size(), equalTo(1));
		assertThat(sentences.get(0).getContent(), equalTo("城镇化好，城镇化好，城镇化好。"));
	}

	@Test
	public void should_summarize_multi_docs() {
		SensitiveToContextSummarizer summarizer = new SensitiveToContextSummarizer(
				10);
		MultiDocument multiDoc = new MultiDocument();
		addDoc(multiDoc, 1, "李克强:新型城镇化要真正惠及农民\n要让新型城镇化路子走好走顺");
		addDoc(multiDoc, 2, "城镇化好，城镇化好，城镇化好");
		Document doc = summarizer.summarize(multiDoc);

		List<Sentence> sentences = doc.getParagraphs().get(0).getSentences();
		assertThat(sentences.size(), equalTo(1));
		assertThat(doc.getParagraphs().get(0).getSentences().get(0)
				.getContent(), equalTo("城镇化好，城镇化好，城镇化好。"));
	}

	@Test
	public void should_summarize_multi_docs_2() {
		SensitiveToContextSummarizer summarizer = new SensitiveToContextSummarizer(
				20);
		MultiDocument multiDoc = new MultiDocument();
		addDoc(multiDoc, 1, "李克强:新型城镇化要真正惠及农民\n要让新型城镇化路子走好走顺");
		addDoc(multiDoc, 2, "城镇化好，城镇化好，城镇化好");
		Document doc = summarizer.summarize(multiDoc);

		Paragraph paragraph = doc.getParagraphs().get(0);
		List<Sentence> sentences = paragraph.getSentences();
		assertThat(sentences.size(), equalTo(2));
		assertThat(paragraph.getSentences().get(1).getContent(),
				equalTo("城镇化好，城镇化好，城镇化好。"));
	}

	private void addDoc(MultiDocument multiDoc, int docId, String content) {
		ArticleBuilder articleBuilder = new ArticleBuilder();
		Article article = articleBuilder.withContent(content).build();
		Document doc = new DocumentParserImpl(new IctclasSegmenter())
				.parse(article);
		doc.setDocId(docId);
		multiDoc.add(doc);
	}

}
