package com.weieditor.mds;

import java.util.List;

import com.weieditor.mds.model.Word;

public interface Segmenter {

	public List<Word> segment(String text);

}
