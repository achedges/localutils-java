package localutils.json;

public abstract class JsonElement {

	// JsonElement getters
	public JsonInteger getIntElement() { return null; }
	public JsonFloat getFloatElement() { return null; }
	public JsonString getStringElement() { return null; }
	public JsonList getListElement() { return null; }
	public JsonObject getObjectElement() { return null; }
	public JsonBoolean getBoolElement() { return null; }

	// Value getters
	public Integer getInt() { return null; }
	public Double getDouble() { return null; }
	public String getString() { return null; }
	public Boolean getBool() { return null; }

	public abstract String serialize(int indent);
}
