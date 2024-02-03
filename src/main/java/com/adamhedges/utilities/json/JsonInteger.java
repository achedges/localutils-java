package com.adamhedges.utilities.json;

public class JsonInteger extends JsonElement {
	public int value;

	public JsonInteger(int value) {
		this.value = value;
	}

	@Override
	public String serialize(int indent) {
		return String.valueOf(this.value);
	}

	@Override
	public JsonInteger getIntElement() {
		return this;
	}

	@Override
	public JsonFloat getFloatElement() {
		return new JsonFloat(value);
	}

	@Override
	public Integer getInt() {
		return this.value;
	}

	@Override
	public Double getDouble() {
		return (double)this.value;
	}
}
