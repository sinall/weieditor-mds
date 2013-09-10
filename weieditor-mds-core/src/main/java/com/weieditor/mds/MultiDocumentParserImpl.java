package com.weieditor.mds;

import java.util.List;

import com.weieditor.mds.model.Article;
import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiArticle;
import com.weieditor.mds.model.MultiDocument;

public class MultiDocumentParserImpl implements MultiDocumentParser {

	private DocumentParser parser;

	public MultiDocumentParserImpl(DocumentParser parser) {
		this.parser = parser;
	}

	@Override
	public MultiDocument parse(MultiArticle multiArticle) {
		MultiDocument multiDocument = new MultiDocument();
		List<Article> articles = multiArticle.getArticles();
		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			Document doc = parser.parse(article);
			doc.setDocId(i + 1);
			multiDocument.add(doc);
		}
		return multiDocument;
	}
}
