package localutils.json;

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
	public JsonInteger getIntValue() {
		return this;
	}

	@Override
	public JsonFloat getFloatValue() {
		return new JsonFloat(value);
	}
}
