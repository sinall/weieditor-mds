package com.weieditor.mds.model;

import java.util.ArrayList;
import java.util.List;

import com.weieditor.mds.visitor.DocumentVisitor;

public class Sentence {

	private String content = "";
	private List<Word> words = new ArrayList<Word>();

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Word> getWords() {
		return words;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}

	public void accept(DocumentVisitor docVisitor) {
		docVisitor.visit(this);
		for (Word word : words) {
			word.accept(docVisitor);
		}
	}

}
