package com.weieditor.mds.visitor;

import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;
import com.weieditor.mds.model.Paragraph;
import com.weieditor.mds.model.Sentence;
import com.weieditor.mds.model.Word;

public class DocumentLengthVisitor implements DocumentVisitor {

	private int length;

	public int getLength() {
		return length;
	}

	public void visit(MultiDocument multiDocument) {
	}

	public void visit(Document document) {
	}

	public void visit(Paragraph paragraph) {
	}

	public void visit(Sentence sentence) {
		int sentenceLength = sentence.getContent().length();
		length += sentenceLength;
	}

	public void visit(Word word) {
	}

}
