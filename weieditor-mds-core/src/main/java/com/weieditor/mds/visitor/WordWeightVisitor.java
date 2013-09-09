package com.weieditor.mds.visitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;
import com.weieditor.mds.model.Paragraph;
import com.weieditor.mds.model.Sentence;
import com.weieditor.mds.model.Speech;
import com.weieditor.mds.model.Word;

public class WordWeightVisitor implements DocumentVisitor {

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

	public Map<Word, Double> getWordWeightMapping() {
		Map<Word, Double> wordWeightMapping = new HashMap<Word, Double>();
		Iterator<?> it = wordOccurrenceMapping.entrySet().iterator();
		while (it.hasNext()) {
			@SuppressWarnings("unchecked")
			Entry<Word, Integer> pairs = (Entry<Word, Integer>) it.next();
			Word word = pairs.getKey();
			Integer occurrences = pairs.getValue();
			wordWeightMapping.put(word, (double) occurrences / wordNumber);
		}
		return wordWeightMapping;
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

	private int getOccurrences(Word word) {
		Integer occurrences = wordOccurrenceMapping.get(word);
		if (occurrences == null) {
			occurrences = 0;
		}
		return occurrences;
	}

}
