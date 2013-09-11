package com.weieditor.mds;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.weieditor.mds.model.Article;
import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiArticle;
import com.weieditor.mds.model.MultiDocument;
import com.weieditor.mds.model.Paragraph;
import com.weieditor.mds.model.Sentence;
import com.weieditor.mds.model.Speech;
import com.weieditor.mds.model.Title;
import com.weieditor.mds.model.Word;
import com.weieditor.mds.visitor.DocumentLengthVisitor;
import com.weieditor.mds.visitor.SentenceWeightVisitor;
import com.weieditor.mds.visitor.TitleWeightVisitor;
import com.weieditor.mds.visitor.WordWeightVisitor;

public class SensitiveToContextSummarizer implements Summarizer {

	private static final double LOW_WEIGHT = 0.0001;
	private Map<Word, Double> wordWeightMapping;
	private Map<Sentence, Double> sentenceWeightMapping;
	private Map<Title, Double> titleWeightMapping;
	private Map<Sentence, Boolean> sentencePickedMapping = new LinkedHashMap<Sentence, Boolean>();
	private Configuration conf;
	private MultiDocumentParser multiDocParser;

	public SensitiveToContextSummarizer(MultiDocumentParser multiDocParser) {
		this.multiDocParser = multiDocParser;
	}

	@Override
	public void setConfiguration(Configuration conf) {
		this.conf = conf;
	}

	@Override
	public Article summarize(MultiArticle multiArticle) {
		Document doc = new Document();

		WordWeightVisitor wordVisitor = new WordWeightVisitor();
		MultiDocument multiDoc = multiDocParser.parse(multiArticle);
		multiDoc.accept(wordVisitor);
		wordWeightMapping = wordVisitor.getWordWeightMapping();

		Title title = buildTitle(multiDoc);
		doc.setTitle(title);

		SentenceWeightVisitor sentenceVistor = new SentenceWeightVisitor(
				wordWeightMapping);
		multiDoc.accept(sentenceVistor);
		sentenceWeightMapping = sentenceVistor.getSentenceWeightMapping();
		for (Entry<Sentence, Double> entry : sentenceWeightMapping.entrySet()) {
			sentencePickedMapping.put(entry.getKey(), false);
		}
		while (shouldContinue(doc)) {
			Sentence sentence = pickSentence();
			sentencePickedMapping.put(sentence, true);
			resetWordWeight(sentence);
			multiDoc.accept(sentenceVistor);
		}
		Paragraph p = new Paragraph(doc, 1);
		for (Entry<Sentence, Boolean> entry : sentencePickedMapping.entrySet()) {
			if (entry.getValue()) {
				p.add(entry.getKey());
			}
		}
		doc.add(p);

		ArticleTransformer articleTransformer = new ArticleTransformer();
		Article article = articleTransformer.transform(doc);

		return article;
	}

	private Title buildTitle(MultiDocument multiDoc) {
		TitleWeightVisitor titleVisitor = new TitleWeightVisitor(
				wordWeightMapping);
		multiDoc.accept(titleVisitor);
		titleWeightMapping = titleVisitor.getTitleWeightMapping();
		Title title = pickTitle();
		return title;
	}

	private void resetWordWeight(Sentence sentence) {
		List<Word> words = sentence.getWords();
		for (Word word : words) {
			if (word.getSpeech().equals(Speech.UNKNOWN)) {
				return;
			}
			Double weight = wordWeightMapping.get(word);
			if (weight != null && weight > 0) {
				wordWeightMapping.put(word, LOW_WEIGHT);
			}
		}
	}

	private Title pickTitle() {
		Set<Entry<Title, Double>> entrySet = titleWeightMapping.entrySet();
		Entry<Title, Double> pickedEntry = null;
		for (Entry<Title, Double> entry : entrySet) {
			if (pickedEntry == null) {
				pickedEntry = entry;
			} else if (entry.getValue() > pickedEntry.getValue()) {
				pickedEntry = entry;
			}
		}

		Title title = null;
		if (pickedEntry != null) {
			title = pickedEntry.getKey();
		}

		return title;
	}

	private Sentence pickSentence() {
		Set<Entry<Sentence, Double>> entrySet = sentenceWeightMapping
				.entrySet();
		Entry<Sentence, Double> pickedEntry = null;
		for (Entry<Sentence, Double> entry : entrySet) {
			if (sentencePickedMapping.get(entry.getKey())) {
				continue;
			}
			if (pickedEntry == null) {
				pickedEntry = entry;
			} else if (entry.getValue() > pickedEntry.getValue()) {
				pickedEntry = entry;
			}
		}

		Sentence sentence = null;
		if (pickedEntry != null) {
			sentence = pickedEntry.getKey();
		}

		return sentence;
	}

	private boolean shouldContinue(Document doc) {
		DocumentLengthVisitor docVisitor = new DocumentLengthVisitor();
		for (Entry<Sentence, Boolean> entry : sentencePickedMapping.entrySet()) {
			if (entry.getValue()) {
				entry.getKey().accept(docVisitor);
			}
		}
		int length = docVisitor.getLength();
		boolean unpickedSentenceExists = sentencePickedMapping.values()
				.contains(false);
		return length < conf.getLengthLimit() && unpickedSentenceExists;
	}

}
