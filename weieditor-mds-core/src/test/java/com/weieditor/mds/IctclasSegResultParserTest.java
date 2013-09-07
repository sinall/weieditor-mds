package com.weieditor.mds;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.weieditor.mds.model.Speech;
import com.weieditor.mds.model.Word;

public class IctclasSegResultParserTest {

	@Test
	public void should_parse_segment_result() {
		IctclasSegResultParser parser = new IctclasSegResultParser();

		List<Word> words = parser.parse("我/r 使用/v 计算机/n ");

		assertThat(words.size(), equalTo(3));
		assertThat(words.get(0), equalTo(new Word("我", Speech.PRONOUN)));
		assertThat(words.get(1), equalTo(new Word("使用", Speech.VERB)));
		assertThat(words.get(2), equalTo(new Word("计算机", Speech.NOUN)));
	}

}
