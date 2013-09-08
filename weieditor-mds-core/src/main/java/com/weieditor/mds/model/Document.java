package com.weieditor.mds.model;

import java.util.ArrayList;
import java.util.List;

import com.weieditor.mds.visitor.DocumentVisitor;

public class Document {

	private List<Paragraph> paragraphs = new ArrayList<Paragraph>();

	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
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

}
