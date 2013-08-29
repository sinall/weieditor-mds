package com.weieditor.mds.rouge;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

public class RougeParserTest {

	@Test
	public void should_parse_output() throws IOException {
		RougeOutputParser parser = new RougeOutputParser();
		RougeOutput rougeOutput = parser.parse(ClassLoader.getSystemResourceAsStream("DUC2002-ROUGE.in.26.spl.lst.out"));

		RougeN_Output rouge1Output = rougeOutput.getRouge1_Output();
		RougeN_AverageOutput rouge1AverageOutput = rouge1Output.getAverageR_Output();
		assertThat(rouge1AverageOutput.getScore(), equalTo(0.33939));
	}

}
