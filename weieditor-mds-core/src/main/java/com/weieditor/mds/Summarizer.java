package com.weieditor.mds;

import java.util.List;

import com.weieditor.mds.model.Document;

public interface Summarizer {

	public Document summarize(List<Document> docs);

}
