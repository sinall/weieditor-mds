package com.weieditor.mds;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.weieditor.mds.model.Article;
import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.Paragraph;
import com.weieditor.mds.model.Sentence;
import com.weieditor.mds.model.Speech;
import com.weieditor.mds.model.Word;

public class DocumentParserImplTest {

	@Test
	public void should_parse_article() {
		DocumentParserImpl builder = new DocumentParserImpl(new IctclasSegmenter());
		ArticleBuilder articleBuilder = new ArticleBuilder();
		Article article = articleBuilder
				.withTitle("国资委官员称从4月起文件批复就不再请示蒋洁敏")
				.withContent(
						"9月1日上午11点，新华网发布消息称，从中央纪委获悉，国资委主任、党委副书记蒋洁敏涉嫌严重违纪，目前正接受组织调查。当晚，国资委将其网站上所有涉及蒋的信息全部撤除。")
				.build();

		Document doc = builder.parse(article);

		List<Paragraph> paragraphs = doc.getParagraphs();
		assertThat(paragraphs.size(), equalTo(1));
		List<Sentence> sentences = paragraphs.get(0).getSentences();
		assertThat(
				sentences.get(0).getContent(),
				equalTo("9月1日上午11点，新华网发布消息称，从中央纪委获悉，国资委主任、党委副书记蒋洁敏涉嫌严重违纪，目前正接受组织调查。"));
		Sentence sentence = sentences.get(1);
		assertThat(sentence.getContent(),
				equalTo("当晚，国资委将其网站上所有涉及蒋的信息全部撤除。"));
		List<Word> words = sentence.getWords();
		assertThat(words, hasItem(new Word("网站", Speech.NOUN)));
	}

}
