package localutils.json;

public abstract class JsonElement {

	public JsonInteger getIntValue() {
		return null;
	}

	public JsonFloat getFloatValue() {
		return null;
	}

	public JsonString getStringValue() {
		return null;
	}

	public JsonList getListValue() {
		return null;
	}

	public JsonObject getObjectValue() {
		return null;
	}

	public JsonBoolean getBoolValue() {
		return null;
	}

	public abstract String serialize(int indent);
}
