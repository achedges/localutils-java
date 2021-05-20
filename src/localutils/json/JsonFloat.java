package localutils.json;

public class JsonFloat extends JsonElement {
	public double value;

	public JsonFloat(double value) {
		this.value = value;
	}

	@Override
	public String serialize(int indent) {
		return String.valueOf(this.value);
	}

	@Override
	public JsonFloat getFloatValue() {
		return this;
	}

	@Override
	public JsonInteger getIntValue() {
		return new JsonInteger((int)value);
	}
}
