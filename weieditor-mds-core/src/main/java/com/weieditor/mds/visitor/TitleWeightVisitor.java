package com.weieditor.mds.visitor;

import java.util.LinkedHashMap;
import java.util.Map;

import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;
import com.weieditor.mds.model.Paragraph;
import com.weieditor.mds.model.Sentence;
import com.weieditor.mds.model.Title;
import com.weieditor.mds.model.Word;

public class TitleWeightVisitor implements DocumentVisitor {

	private Map<Word, Double> wordWeightMapping;
	private Map<Title, Double> titleWeightMapping = new LinkedHashMap<Title, Double>();

	public TitleWeightVisitor(Map<Word, Double> wordWeightMapping) {
		this.wordWeightMapping = wordWeightMapping;
	}

	public Map<Title, Double> getTitleWeightMapping() {
		return titleWeightMapping;
	}

	public void visit(MultiDocument multiDocument) {
	}

	public void visit(Document document) {
	}

	public void visit(Title title) {
		double totalWeight = 0;
		int candidateWordNum = 0;
		for (Word word : title.getWords()) {
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
		titleWeightMapping.put(title, weight);
	}

	public void visit(Paragraph paragraph) {
	}

	public void visit(Sentence sentence) {
	}

	public void visit(Word word) {
	}

}
