package com.weieditor.mds;

import java.util.List;

import org.ictclas4j.bean.SegResult;
import org.ictclas4j.segment.SegTag;

import com.weieditor.mds.model.Word;

public class IctclasSegmenter implements Segmenter {

	private static final int SEG_PATH_COUNT = 1;
	private IctclasSegResultParser parser = new IctclasSegResultParser();

	@Override
	public List<Word> segment(String text) {
		SegTag segTag = new SegTag(SEG_PATH_COUNT);
		SegResult segResult = segTag.split(text);
		String segmentedText = segResult.getFinalResult();
		List<Word> words = parser.parse(segmentedText);

		return words;
	}

}
