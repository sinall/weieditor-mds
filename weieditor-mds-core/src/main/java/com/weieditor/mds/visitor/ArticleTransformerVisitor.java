package com.weieditor.mds.visitor;

import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;
import com.weieditor.mds.model.Paragraph;
import com.weieditor.mds.model.Sentence;
import com.weieditor.mds.model.Word;

public class ArticleTransformerVisitor implements DocumentVisitor {

	private StringBuilder stringBuilder = new StringBuilder();

	public String getContent() {
		String content = stringBuilder.toString();
		return content;
	}

	@Override
	public void visit(MultiDocument multiDocument) {
	}

	@Override
	public void visit(Document document) {
	}

	@Override
	public void visit(Paragraph paragraph) {
	}

	@Override
	public void visit(Sentence sentence) {
		stringBuilder.append(sentence.getContent());
	}

	@Override
	public void visit(Word word) {
	}

}