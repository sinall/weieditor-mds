package com.weieditor.mds.model;

import java.util.ArrayList;
import java.util.List;

import com.weieditor.mds.visitor.DocumentVisitor;

public class MultiDocument {

	private List<Document> documents = new ArrayList<Document>();

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public void add(Document doc) {
		documents.add(doc);
	}

	public void accept(DocumentVisitor docVisitor) {
		docVisitor.visit(this);
		for (Document doc : documents) {
			doc.accept(docVisitor);
		}
	}

}
