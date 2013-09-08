package com.weieditor.mds;

import com.weieditor.mds.model.Article;
import com.weieditor.mds.model.Document;

public interface DocumentParser {

	public abstract Document parse(Article article);

}
