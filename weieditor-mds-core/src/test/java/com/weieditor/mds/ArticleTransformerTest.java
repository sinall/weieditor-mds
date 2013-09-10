package com.weieditor.mds;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.weieditor.mds.model.Article;
import com.weieditor.mds.model.Document;

public class ArticleTransformerTest {

	@Test
	public void should_transform_document_to_article() {
		ArticleTransformer articleTransformer = new ArticleTransformer();
		ArticleBuilder articleBuilder = new ArticleBuilder();
		Article sourceArticle = articleBuilder
				.withContent(
						"9月1日上午11点，新华网发布消息称，从中央纪委获悉，国资委主任、党委副书记蒋洁敏涉嫌严重违纪，目前正接受组织调查。当晚，国资委将其网站上所有涉及蒋的信息全部撤除。")
				.build();
		DocumentParser parser = new DocumentParserImpl(new IctclasSegmenter());
		Document doc = parser.parse(sourceArticle);

		Article article = articleTransformer.transform(doc);

		assertTrue(StringUtils.isNotBlank(article.getContent()));
		assertThat(
				article.getContent(),
				equalTo("9月1日上午11点，新华网发布消息称，从中央纪委获悉，国资委主任、党委副书记蒋洁敏涉嫌严重违纪，目前正接受组织调查。当晚，国资委将其网站上所有涉及蒋的信息全部撤除。"));
	}
}
