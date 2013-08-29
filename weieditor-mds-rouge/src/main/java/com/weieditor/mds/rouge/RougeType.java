package com.weieditor.mds.rouge;

public enum RougeType {
	ROUGE_1("ROUGE-1"), ROUGE_2("ROUGE-2"), ROUGE_3("ROUGE-3"), ROUGE_4(
			"ROUGE-4"), ROUGE_L("ROUGE-L");

	private String text;

	private RougeType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static RougeType fromString(String text) {
		if (text != null) {
			for (RougeType rougeType : RougeType.values()) {
				if (rougeType.getText().equalsIgnoreCase(text)) {
					return rougeType;
				}
			}
		}
		return null;
	}
}
