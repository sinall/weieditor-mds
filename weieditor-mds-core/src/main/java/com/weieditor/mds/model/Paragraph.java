package com.weieditor.mds.model;

import java.util.ArrayList;
import java.util.List;

public class Paragraph {

	private String content;
	private List<Sentence> sentences = new ArrayList<Sentence>();

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}

	public void add(Sentence sentence) {
		sentences.add(sentence);
	}

}
