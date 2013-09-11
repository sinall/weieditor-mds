package com.weieditor.mds.visitor;

import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;
import com.weieditor.mds.model.Paragraph;
import com.weieditor.mds.model.Sentence;
import com.weieditor.mds.model.Title;
import com.weieditor.mds.model.Word;

public class ArticleTransformerVisitor implements DocumentVisitor {

	private StringBuilder stringBuilder = new StringBuilder();

	public String getContent() {
		String content = stringBuilder.toString();
		return content;
	}

	public void visit(MultiDocument multiDocument) {
	}

	public void visit(Document document) {
	}

	public void visit(Title title) {
	}

	public void visit(Paragraph paragraph) {
	}

	public void visit(Sentence sentence) {
		stringBuilder.append(sentence.getContent());
	}

	public void visit(Word word) {
	}

}