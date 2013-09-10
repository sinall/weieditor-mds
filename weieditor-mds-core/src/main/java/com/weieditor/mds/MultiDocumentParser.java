package com.weieditor.mds;

import com.weieditor.mds.model.MultiArticle;
import com.weieditor.mds.model.MultiDocument;

public interface MultiDocumentParser {

	MultiDocument parse(MultiArticle multiArticle);

}
