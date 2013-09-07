package com.weieditor.mds;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.weieditor.mds.model.Speech;
import com.weieditor.mds.model.Word;

public class IctclasSegResultParser {

	private static IctclasSpeechConvertor convertor = new IctclasSpeechConvertor();

	public List<Word> parse(String text) {
		List<Word> words = new ArrayList<Word>();
		Pattern pattern = Pattern.compile("[^/]+/[a-z] ");
		Matcher matcher = pattern.matcher(text);
		String group = "";
		while (matcher.find()) {
			group = matcher.group();
			String[] parts = group.trim().split("/");
			Speech speech = convertor.convert(parts[1]);
			Word word = new Word(parts[0], speech);
			words.add(word);
		}
		return words;
	}
}
