package com.weieditor.mds;

import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.MultiDocument;

public interface Summarizer {

	public Document summarize(MultiDocument multiDoc);

}
