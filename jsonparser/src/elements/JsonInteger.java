public class JsonInteger extends JsonElement {
	public int value;

	public JsonInteger(int value) {
		this.value = value;
	}

	@Override
	public String serialize(int indent) {
		return String.valueOf(this.value);
	}
}
