package com.weieditor.mds;

import java.util.HashMap;
import java.util.Map;

import com.weieditor.mds.model.Speech;

public class IctclasSpeechConvertor {

	private static Map<String, Speech> speechMapping;

	static {
		speechMapping = new HashMap<String, Speech>();
		speechMapping.put("a", Speech.ADJECTIVE);
		speechMapping.put("d", Speech.ADVERB);
		speechMapping.put("m", Speech.NUM);
		speechMapping.put("n", Speech.NOUN);
		speechMapping.put("r", Speech.PRONOUN);
		speechMapping.put("v", Speech.VERB);
	}

	public Speech convert(String ictclasSpeech) {
		Speech speech = speechMapping.get(ictclasSpeech);
		if (speech == null) {
			speech = Speech.UNKNOWN;
		}
		return speech;
	}
}
