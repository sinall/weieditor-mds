package com.weieditor.mds;

import com.weieditor.mds.model.Speech;

public class IctclasSpeechConvertor {

	public Speech convert(String ictclasSpeech) {
		Speech speech = Speech.UNKNOWN;
		if (ictclasSpeech.equals("r")) {
			speech = Speech.PRONOUN;
		} else if (ictclasSpeech.equals("v")) {
			speech = Speech.VERB;
		} else if (ictclasSpeech.equals("n")) {
			speech = Speech.NOUN;
		}
		return speech;
	}
}
