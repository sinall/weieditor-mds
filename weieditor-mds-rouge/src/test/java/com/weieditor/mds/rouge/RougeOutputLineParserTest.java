package com.weieditor.mds.rouge;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RougeOutputLineParserTest {

	@Test
	public void should_parse_line() {
		RougeN_OutputLineParser parser = new RougeN_OutputLineParser();
		String line = "26 ROUGE-1 Average_R: 0.33939 (95%-conf.int. 0.32215 - 0.35572)";

		RougeN_AverageOutput output = parser.parse(line);

		assertThat(output.getScore(), equalTo(0.33939));
	}

}
