package com.weieditor.mds.model;

import java.util.ArrayList;
import java.util.List;

import com.weieditor.mds.visitor.DocumentVisitor;

public class Document {

	private int docId;
	private List<Paragraph> paragraphs = new ArrayList<Paragraph>();

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}

	public Paragraph get(int id) {
		return paragraphs.get(id);
	}

	public void add(Paragraph p) {
		paragraphs.add(p);
	}

	public void accept(DocumentVisitor docVisitor) {
		docVisitor.visit(this);
		for (Paragraph p : paragraphs) {
			p.accept(docVisitor);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + docId;
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
		Document other = (Document) obj;
		if (docId != other.docId)
			return false;
		return true;
	}

}
