package com.weieditor.mds;

import com.weieditor.mds.model.Article;
import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.Title;
import com.weieditor.mds.visitor.ArticleTransformerVisitor;

public class ArticleTransformer {

	private ArticleTransformerVisitor transformerVisitor = new ArticleTransformerVisitor();

	public Article transform(Document doc) {
		doc.accept(transformerVisitor);
		String content = transformerVisitor.getContent();
		Title title = doc.getTitle();
		String titleContent = "";
		if (title != null) {
			titleContent = title.getContent();
		}
		ArticleBuilder articleBuilder = new ArticleBuilder();
		Article article = articleBuilder.withTitle(titleContent)
				.withContent(content).build();

		return article;
	}

}
