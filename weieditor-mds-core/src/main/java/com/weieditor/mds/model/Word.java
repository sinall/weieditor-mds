package com.weieditor.mds.model;

import com.weieditor.mds.visitor.DocumentVisitor;

public class Word {

	private String value = "";
	private Speech speech = Speech.UNKNOWN;

	public Word(String value, Speech speech) {
		this.value = value;
		this.speech = speech;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Speech getSpeech() {
		return speech;
	}

	public void setSpeech(Speech speech) {
		this.speech = speech;
	}

	public void accept(DocumentVisitor docVisitor) {
		docVisitor.visit(this);
	}

	@Override
	public String toString() {
		return "Word [value=" + value + ", speech=" + speech + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((speech == null) ? 0 : speech.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (speech != other.speech)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
