package com.weieditor.mds;

import com.weieditor.mds.model.Article;
import com.weieditor.mds.model.MultiArticle;

public interface Summarizer {

	public void setConfiguration(Configuration conf);

	public Article summarize(MultiArticle multiArticle);

}
