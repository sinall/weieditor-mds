package com.weieditor.mds;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;
import com.weieditor.mds.model.Paragraph;
import com.weieditor.mds.model.Sentence;
import com.weieditor.mds.model.Speech;
import com.weieditor.mds.model.Word;
import com.weieditor.mds.visitor.DocumentLengthVisitor;
import com.weieditor.mds.visitor.SentenceWeightVisitor;
import com.weieditor.mds.visitor.WordWeightVisitor;

public class SensitiveToContextSummarizer implements Summarizer {

	private static final double LOW_WEIGHT = 0.0001;
	private int lengthLimit = 200;
	private Map<Word, Double> wordWeightMapping;
	private Map<Sentence, Double> sentenceWeightMapping;
	private Map<Sentence, Boolean> sentencePickedMapping = new LinkedHashMap<Sentence, Boolean>();

	public SensitiveToContextSummarizer(int lengthLimit) {
		this.lengthLimit = lengthLimit;
	}

	@Override
	public Document summarize(MultiDocument multiDoc) {
		Document doc = new Document();
		Paragraph p = new Paragraph(doc, 1);
		doc.add(p);

		WordWeightVisitor wordVisitor = new WordWeightVisitor();
		multiDoc.accept(wordVisitor);
		wordWeightMapping = wordVisitor.getWordWeightMapping();
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

		for (Entry<Sentence, Boolean> entry : sentencePickedMapping.entrySet()) {
			if (entry.getValue()) {
				p.add(entry.getKey());
			}
		}

		return doc;
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
		return length < lengthLimit && unpickedSentenceExists;
	}

}
