public class JsonFloat extends JsonElement {
	public double value;

	public JsonFloat(double value) {
		this.value = value;
	}

	@Override
	public String serialize(int indent) {
		return String.valueOf(this.value);
	}
}
