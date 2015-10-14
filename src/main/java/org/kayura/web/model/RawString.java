package org.kayura.web.model;

public class RawString {

	private String value;

	public RawString() {

	}

	public RawString(String value) {
		this.setValue(value);
	}

	@Override
	public String toString() {
		return getValue();
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
