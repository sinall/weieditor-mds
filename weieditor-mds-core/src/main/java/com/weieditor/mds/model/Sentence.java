package com.weieditor.mds.model;

import java.util.ArrayList;
import java.util.List;

import com.weieditor.mds.visitor.DocumentVisitor;

public class Sentence {

	private int paragraphId;
	private int id;
	private String content = "";
	private List<Word> words = new ArrayList<Word>();

	public int getParagraphId() {
		return paragraphId;
	}

	public void setParagraphId(int paragraphId) {
		this.paragraphId = paragraphId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + paragraphId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sentence other = (Sentence) obj;
		if (id != other.id)
			return false;
		if (paragraphId != other.paragraphId)
			return false;
		return true;
	}

}
