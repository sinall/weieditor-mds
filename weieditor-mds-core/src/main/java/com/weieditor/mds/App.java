package com.weieditor.mds;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import com.weieditor.mds.model.Article;
import com.weieditor.mds.model.MultiArticle;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		SensitiveToContextSummarizer summarizer = new SensitiveToContextSummarizer(
				new MultiDocumentParserImpl(new DocumentParserImpl(
						new IctclasSegmenter())));
		Configuration conf = new Configuration();
		summarizer.setConfiguration(conf);

		MultiArticle multiArticle = new MultiArticle();
		File docsDir = new File("src/test/resources/docs/docs1");
		File[] docFiles = docsDir.listFiles();
		for (File docFile : docFiles) {
			ArticleBuilder articleBuilder = new ArticleBuilder();
			String content = IOUtils.toString(new FileInputStream(docFile),
					Charsets.UTF_8);
			String fileName = docFile.getName();
			String title = fileName.replaceAll("\\.txt$", "");
			Article article = articleBuilder.withTitle(title)
					.withContent(content).build();
			multiArticle.add(article);
		}

		Article article = summarizer.summarize(multiArticle);
		System.out.println(article.toString());
	}
}
