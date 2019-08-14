public class JsonToken {
	public String token;
	public String text;

	public JsonToken(String token, String text) {
		this.token = token;
		this.text = text;
	}

	@Override
	public String toString() {
		return (this.token.equals(this.text)) ? this.token : String.format("%s: %s", this.token, this.text);
	}
}
