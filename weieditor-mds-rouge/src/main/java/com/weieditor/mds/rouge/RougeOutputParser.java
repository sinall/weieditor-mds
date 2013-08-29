package com.weieditor.mds.rouge;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class RougeOutputParser {

	private RougeN_OutputLineParser rougeLineParser;

	public RougeOutputParser() {
		rougeLineParser = new RougeN_OutputLineParser();
	}

	public RougeOutput parse(InputStream inputStream) throws IOException {
		RougeOutput rougeOutput = new RougeOutput();
		List<String> lines = IOUtils.readLines(inputStream);
		RougeN_AverageOutput avgR = null;
		RougeN_AverageOutput avgP = null;
		RougeN_AverageOutput avgF = null;
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if (line.startsWith("--")) {
				RougeN_Output rougeN_Output = new RougeN_Output();
				line = lines.get(++i);
				avgR = rougeLineParser.parse(line);
				rougeN_Output.setAverageR_Output(avgR);
				line = lines.get(++i);
				avgP = rougeLineParser.parse(line);
				rougeN_Output.setAverageP_Output(avgP);
				line = lines.get(++i);
				avgF = rougeLineParser.parse(line);
				rougeN_Output.setAverageF_Output(avgF);
				switch (avgR.getRougeType()) {
				case ROUGE_1:
					rougeOutput.setRouge1_Output(rougeN_Output);
				case ROUGE_2:
					rougeOutput.setRouge2_Output(rougeN_Output);
				case ROUGE_3:
					rougeOutput.setRouge3_Output(rougeN_Output);
				case ROUGE_4:
					rougeOutput.setRouge4_Output(rougeN_Output);
				case ROUGE_L:
					rougeOutput.setRougeL_Output(rougeN_Output);

				}
			}
		}
		return rougeOutput;
	}

}
