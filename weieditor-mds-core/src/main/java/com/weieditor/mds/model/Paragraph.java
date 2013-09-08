package com.weieditor.mds.model;

import java.util.ArrayList;
import java.util.List;

import com.weieditor.mds.visitor.DocumentVisitor;

public class Paragraph {

	private int docId;
	private int id;
	private String content = "";
	private List<Sentence> sentences = new ArrayList<Sentence>();

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
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

	public List<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}

	public Sentence get(int id) {
		return sentences.get(id);
	}

	public void add(Sentence sentence) {
		sentences.add(sentence);
	}

	public void accept(DocumentVisitor docVisitor) {
		docVisitor.visit(this);
		for (Sentence sentence : sentences) {
			sentence.accept(docVisitor);
		}

	}

}
