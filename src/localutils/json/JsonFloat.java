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
	public JsonFloat getFloatElement() {
		return this;
	}

	@Override
	public JsonInteger getIntElement() {
		return new JsonInteger((int)value);
	}

	@Override
	public Double getDouble() {
		return this.value;
	}

	@Override
	public Integer getInt() {
		return (int)this.value;
	}
}
