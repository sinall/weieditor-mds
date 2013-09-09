package com.weieditor.mds;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import com.weieditor.mds.model.Article;
import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;
import com.weieditor.mds.model.Paragraph;
import com.weieditor.mds.model.Sentence;
import com.weieditor.mds.visitor.SentenceWeightVisitor;
import com.weieditor.mds.visitor.WordWeightVisitor;

public class SentenceWeightVisitorTest {

	@Test
	public void should_calculate_sentence_weight() {
		ArticleBuilder articleBuilder = new ArticleBuilder();
		Article article = articleBuilder.withContent(
				"李克强:新型城镇化要真正惠及农民\n要让新型城镇化路子走好走顺").build();
		Document doc = new DocumentParserImpl(new IctclasSegmenter())
				.parse(article);
		MultiDocument multiDoc = new MultiDocument();
		multiDoc.add(doc);

		WordWeightVisitor wordVisitor = new WordWeightVisitor();
		multiDoc.accept(wordVisitor);
		SentenceWeightVisitor sentenceVisitor = new SentenceWeightVisitor(
				wordVisitor.getWordWeightMapping());
		multiDoc.accept(sentenceVisitor);

		Map<Sentence, Double> sentenceWeightMapping = sentenceVisitor
				.getSentenceWeightMapping();
		Sentence sentence1 = new Sentence(new Paragraph(doc, 1), 1);
		double weight1 = sentenceWeightMapping.get(sentence1);
		assertTrue(weight1 > 0.0);
		assertTrue(weight1 < 1.0);

		Sentence sentence2 = new Sentence(new Paragraph(doc, 2), 1);
		double weight2 = sentenceWeightMapping.get(sentence2);
		assertTrue(weight2 > 0.0);
		assertTrue(weight2 < 1.0);
	}

}
