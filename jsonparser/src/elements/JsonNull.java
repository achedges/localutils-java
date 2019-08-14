public class JsonNull extends JsonElement {
	@Override
	public String serialize(int indent) {
		return "null";
	}
}
