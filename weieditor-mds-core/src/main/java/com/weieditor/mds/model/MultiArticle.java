package com.weieditor.mds.model;

import java.util.ArrayList;
import java.util.List;


public class MultiArticle {

	private List<Article> articles = new ArrayList<Article>();

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public void add(Article article) {
		articles.add(article);
	}

}
