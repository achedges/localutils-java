public class JsonString extends JsonElement {
	public String value;

	public JsonString(String value) {
		this.value = value;
	}

	@Override
	public String serialize(int indent) {
		return String.format("\"%s\"", this.value.replace("\"", "\\\""));
	}
}
