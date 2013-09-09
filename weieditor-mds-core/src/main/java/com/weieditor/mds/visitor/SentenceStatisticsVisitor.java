package com.weieditor.mds.visitor;

import java.util.HashMap;
import java.util.Map;

import com.weieditor.mds.WordStatistics;
import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;
import com.weieditor.mds.model.Paragraph;
import com.weieditor.mds.model.Sentence;
import com.weieditor.mds.model.Word;

public class SentenceStatisticsVisitor implements DocumentVisitor {

	private WordStatistics wordStatistics;
	private Map<Sentence, Double> sentenceWeightMapping = new HashMap<Sentence, Double>();

	public SentenceStatisticsVisitor(WordStatistics wordStatistics) {
		super();
		this.wordStatistics = wordStatistics;
	}

	public double getSentenceWeight(Sentence sentence) {
		Double weight = sentenceWeightMapping.get(sentence);
		return weight;
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
			double weight = wordStatistics.getWordWeight(word);
			if (weight > 0) {
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
