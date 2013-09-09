package com.weieditor.mds;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.weieditor.mds.model.Article;
import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;
import com.weieditor.mds.model.Speech;
import com.weieditor.mds.model.Word;
import com.weieditor.mds.visitor.WordStatisticsVisitor;

public class WordStatisticsVisitorTest {

	@Test
	public void should_count_word_occurences() {
		WordStatisticsVisitor visitor = new WordStatisticsVisitor();
		ArticleBuilder articleBuilder = new ArticleBuilder();
		Article article = articleBuilder.withContent(
				"李克强:新型城镇化要真正惠及农民\n要让新型城镇化路子走好走顺").build();
		Document doc = new DocumentParserImpl(new IctclasSegmenter())
				.parse(article);
		MultiDocument multiDoc = new MultiDocument();
		multiDoc.add(doc);

		multiDoc.accept(visitor);

		assertThat(visitor.getOccurrences(new Word("农民", Speech.NOUN)),
				equalTo(1));
		assertThat(visitor.getOccurrences(new Word("城镇", Speech.NOUN)),
				equalTo(2));
		double occurrenceProbability1 = visitor
				.getWordWeight(new Word("农民", Speech.NOUN));
		double occurrenceProbability2 = visitor
				.getWordWeight(new Word("城镇", Speech.NOUN));
		assertThat(occurrenceProbability2 / occurrenceProbability1,
				equalTo(2.0));
	}

}
