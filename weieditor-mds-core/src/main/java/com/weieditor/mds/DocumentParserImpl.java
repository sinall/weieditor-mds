package com.weieditor.mds;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.weieditor.mds.model.Article;
import com.weieditor.mds.model.Document;
import com.weieditor.mds.model.Paragraph;
import com.weieditor.mds.model.Sentence;
import com.weieditor.mds.model.Word;

public class DocumentParserImpl implements DocumentParser {
	
	private final String TERMINATOR = "。";
	private Segmenter segmenter;

	public DocumentParserImpl(Segmenter segmenter) {
		this.segmenter = segmenter;
	}

	@Override
	public Document parse(Article article) {
		Document doc = new Document();

		String content = article.getContent();
		Scanner scanner = new Scanner(content);
		while (scanner.hasNextLine()) {
			String pContent = scanner.nextLine();
			if (StringUtils.isNotBlank(pContent)) {
				Paragraph p = new Paragraph();
				p.setContent(pContent.trim());
				String[] sentenceContents = pContent.split(TERMINATOR);
				for (String sentenceContent : sentenceContents) {
					sentenceContent += TERMINATOR;
					Sentence sentence = new Sentence();
					sentence.setContent(sentenceContent);
					List<Word> words = segmenter.segment(sentenceContent);
					sentence.setWords(words);

					p.add(sentence);
				}
				doc.add(p);
			}
		}
		return doc;
	}

}
