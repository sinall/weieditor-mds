package com.weieditor.mds.visitor;

import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;
import com.weieditor.mds.model.Paragraph;
import com.weieditor.mds.model.Sentence;
import com.weieditor.mds.model.Word;

public interface DocumentVisitor {

	public void visit(MultiDocument multiDocument);

	public void visit(Document document);

	public void visit(Paragraph paragraph);

	public void visit(Sentence sentence);

	public void visit(Word word);

}
