package com.weieditor.mds.rouge;

public class RougeN_OutputLineParser {

	public RougeN_AverageOutput parse(String line) {
		RougeN_AverageOutput output = new RougeN_AverageOutput();
		String parts[] = line.split(" ");
		String rougeTypeText = parts[1];
		RougeType rougeType = RougeType.fromString(rougeTypeText);
		output.setRougeType(rougeType);
		String scoreText = parts[3];
		double score = Double.parseDouble(scoreText);
		output.setScore(score);
		return output;
	}

}
