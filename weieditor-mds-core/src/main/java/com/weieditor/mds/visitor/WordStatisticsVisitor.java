package com.weieditor.mds.visitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;
import com.weieditor.mds.model.Paragraph;
import com.weieditor.mds.model.Sentence;
import com.weieditor.mds.model.Speech;
import com.weieditor.mds.model.Word;

public class WordStatisticsVisitor implements DocumentVisitor {

	private static Set<Speech> candidateSpeechs;
	private int wordNumber;
	private Map<Word, Integer> wordOccurrenceMapping = new HashMap<Word, Integer>();

	static {
		candidateSpeechs = new HashSet<Speech>();
		candidateSpeechs.add(Speech.ADJECTIVE);
		candidateSpeechs.add(Speech.NOUN);
		candidateSpeechs.add(Speech.NUM);
		candidateSpeechs.add(Speech.VERB);
	}

	public double getOccurrenceProbability(Word word) {
		int occurrences = getOccurrences(word);
		return (double) occurrences / wordNumber;
	}

	public int getOccurrences(Word word) {
		Integer occurrences = wordOccurrenceMapping.get(word);
		if (occurrences == null) {
			occurrences = 0;
		}
		return occurrences;
	}

	public void visit(MultiDocument multiDocument) {
	}

	public void visit(Document document) {
	}

	public void visit(Paragraph paragraph) {
	}

	public void visit(Sentence sentence) {
	}

	public void visit(Word word) {
		if (!candidateSpeechs.contains(word.getSpeech())) {
			return;
		}

		wordNumber++;
		int occurrences = getOccurrences(word);
		wordOccurrenceMapping.put(word, ++occurrences);
	}

}
