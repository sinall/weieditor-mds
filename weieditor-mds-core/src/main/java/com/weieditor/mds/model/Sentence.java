package com.weieditor.mds.model;

import java.util.ArrayList;
import java.util.List;

import com.weieditor.mds.visitor.DocumentVisitor;

public class Sentence {

	private Paragraph paragraph;
	private int sentenceId;
	private String content = "";
	private List<Word> words = new ArrayList<Word>();

	public Sentence(Paragraph p, int sentenceId) {
		this.paragraph = p;
		this.sentenceId = sentenceId;
	}

	public Paragraph getParagraph() {
		return paragraph;
	}

	public void setParagraph(Paragraph paragraph) {
		this.paragraph = paragraph;
	}

	public int getSentenceId() {
		return sentenceId;
	}

	public void setSentenceId(int id) {
		this.sentenceId = id;
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
	public String toString() {
		return "Sentence [sentenceId=" + sentenceId + ", content=" + content
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((paragraph == null) ? 0 : paragraph.hashCode());
		result = prime * result + sentenceId;
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
		if (paragraph == null) {
			if (other.paragraph != null)
				return false;
		} else if (!paragraph.equals(other.paragraph))
			return false;
		if (sentenceId != other.sentenceId)
			return false;
		return true;
	}

}
