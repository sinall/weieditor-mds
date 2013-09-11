package com.weieditor.mds;

import com.weieditor.mds.model.Article;

public class ArticleBuilder {

	private String title = "";
	private String content = "";

	public Article build() {
		Article article = new Article();
		article.setTitle(title);
		article.setContent(content);
		return article;
	}

	public ArticleBuilder withTitle(String title) {
		this.title = title;
		return this;
	}

	public ArticleBuilder withContent(String content) {
		this.content = content;
		return this;
	}

}
