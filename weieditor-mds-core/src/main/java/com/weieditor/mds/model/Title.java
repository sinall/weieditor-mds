package com.weieditor.mds.model;

import java.util.List;

import com.weieditor.mds.visitor.DocumentVisitor;

public class Title {

	private Document doc;
	private String content = "";
	private List<Word> words;

	public Title(Document doc) {
		this.doc = doc;
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
		result = prime * result + ((doc == null) ? 0 : doc.hashCode());
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
		Title other = (Title) obj;
		if (doc == null) {
			if (other.doc != null)
				return false;
		} else if (!doc.equals(other.doc))
			return false;
		return true;
	}

}
