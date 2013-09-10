package com.weieditor.mds;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.weieditor.mds.model.Article;
import com.weieditor.mds.model.MultiArticle;

public class SensitiveToContextSummarizerTest {

	private SensitiveToContextSummarizer summarizer;
	private Configuration conf;

	@Before
	public void setUp() {
		summarizer = new SensitiveToContextSummarizer(
				new MultiDocumentParserImpl(new DocumentParserImpl(
						new IctclasSegmenter())));
		conf = new Configuration();
		summarizer.setConfiguration(conf);
	}

	@Test
	public void should_summarize_single_doc() {
		conf.setLengthLimit(10);
		MultiArticle multiArticle = new MultiArticle();
		addArticle(multiArticle, 1,
				"李克强:新型城镇化要真正惠及农民\n要让新型城镇化路子走好走顺\n城镇化好，城镇化好，城镇化好");
		Article article = summarizer.summarize(multiArticle);

		assertThat(article.getContent(), equalTo("城镇化好，城镇化好，城镇化好。"));
	}

	@Test
	public void should_summarize_multi_docs() {
		conf.setLengthLimit(10);
		MultiArticle multiArticle = new MultiArticle();
		addArticle(multiArticle, 1, "李克强:新型城镇化要真正惠及农民\n要让新型城镇化路子走好走顺");
		addArticle(multiArticle, 2, "城镇化好，城镇化好，城镇化好");
		Article article = summarizer.summarize(multiArticle);

		assertThat(article.getContent(), equalTo("城镇化好，城镇化好，城镇化好。"));
	}

	@Test
	public void should_summarize_multi_docs_2() {
		conf.setLengthLimit(20);
		MultiArticle multiArticle = new MultiArticle();
		addArticle(multiArticle, 1, "李克强:新型城镇化要真正惠及农民\n要让新型城镇化路子走好走顺");
		addArticle(multiArticle, 2, "城镇化好，城镇化好，城镇化好");
		Article article = summarizer.summarize(multiArticle);

		assertThat(article.getContent(),
				equalTo("李克强:新型城镇化要真正惠及农民。城镇化好，城镇化好，城镇化好。"));
	}

	private void addArticle(MultiArticle multiArticle, int docId, String content) {
		ArticleBuilder articleBuilder = new ArticleBuilder();
		Article article = articleBuilder.withContent(content).build();
		multiArticle.add(article);
	}

}
