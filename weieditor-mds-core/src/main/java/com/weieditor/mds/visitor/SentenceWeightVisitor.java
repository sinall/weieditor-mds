package com.weieditor.mds.visitor;

import java.util.LinkedHashMap;
import java.util.Map;

import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;
import com.weieditor.mds.model.Paragraph;
import com.weieditor.mds.model.Sentence;
import com.weieditor.mds.model.Word;

public class SentenceWeightVisitor implements DocumentVisitor {

	private Map<Word, Double> wordWeightMapping;
	private Map<Sentence, Double> sentenceWeightMapping = new LinkedHashMap<Sentence, Double>();

	public SentenceWeightVisitor(Map<Word, Double> wordWeightMapping) {
		this.wordWeightMapping = wordWeightMapping;
	}

	public Map<Sentence, Double> getSentenceWeightMapping() {
		return sentenceWeightMapping;
	}

	public void visit(MultiDocument multiDocument) {
	}

	public void visit(Document document) {
	}

	public void visit(Paragraph paragraph) {
	}

	public void visit(Sentence sentence) {
		double totalWeight = 0;
		int candidateWordNum = 0;
		for (Word word : sentence.getWords()) {
			Double weight = wordWeightMapping.get(word);
			if (weight != null && weight > 0) {
				totalWeight += weight;
				candidateWordNum++;
			}
		}

		double weight = 0;
		if (candidateWordNum != 0) {
			weight = totalWeight / candidateWordNum;
		}
		sentenceWeightMapping.put(sentence, weight);
	}

	public void visit(Word word) {
	}

}
