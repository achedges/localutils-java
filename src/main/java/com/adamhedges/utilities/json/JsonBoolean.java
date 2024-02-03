package com.adamhedges.utilities.json;

public class JsonBoolean extends JsonElement {
	public boolean value;

	public JsonBoolean(boolean value) {
		this.value = value;
	}

	@Override
	public String serialize(int indent) {
		return this.value ? "true" : "false";
	}

	@Override
	public JsonBoolean getBoolElement() {
		return this;
	}

	@Override
	public Boolean getBool() {
		return this.value;
	}
}
