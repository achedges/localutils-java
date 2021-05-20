package localutils.json;

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
	public JsonBoolean getBoolValue() {
		return this;
	}
}
