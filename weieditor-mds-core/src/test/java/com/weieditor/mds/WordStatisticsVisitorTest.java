package com.weieditor.mds;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import com.weieditor.mds.model.Article;
import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;
import com.weieditor.mds.model.Speech;
import com.weieditor.mds.model.Word;
import com.weieditor.mds.visitor.WordWeightVisitor;

public class WordStatisticsVisitorTest {

	@Test
	public void should_calculate_word_weight() {
		WordWeightVisitor visitor = new WordWeightVisitor();
		ArticleBuilder articleBuilder = new ArticleBuilder();
		Article article = articleBuilder.withContent(
				"李克强:新型城镇化要真正惠及农民\n要让新型城镇化路子走好走顺").build();
		Document doc = new DocumentParserImpl(new IctclasSegmenter())
				.parse(article);
		MultiDocument multiDoc = new MultiDocument();
		multiDoc.add(doc);

		multiDoc.accept(visitor);

		Map<Word, Double> wordWeightMapping = visitor.getWordWeightMapping();
		double occurrenceProbability1 = wordWeightMapping.get(new Word("农民",
				Speech.NOUN));
		double occurrenceProbability2 = wordWeightMapping.get(new Word("城镇",
				Speech.NOUN));
		assertTrue(occurrenceProbability2 > 0);
		assertTrue(occurrenceProbability2 < 1);
		assertThat(occurrenceProbability2 / occurrenceProbability1,
				equalTo(2.0));
	}

}
