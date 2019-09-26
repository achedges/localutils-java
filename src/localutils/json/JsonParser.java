package localutils.json;

import java.util.*;

public class JsonParser {

	private String input;
	private int n, i;
	private ArrayList<JsonToken> stream = new ArrayList<>();

	public JsonParser(String jsonString) {
		this.input = jsonString.trim();
		this.n = this.input.length();
		this.i = 0;
	}

	public JsonObject parse() throws Exception {
		this.tokenizeInput();
		this.i = 0;

		if (!this.stream.get(0).token.equals("{"))
			throw new Exception("JSON documents must start with '{'");

		return this.parseObject();
	}

	private void tokenizeInput() {
		HashSet<Character> _WS = new HashSet<>(Arrays.asList(' ', '\t', '\n', '\r'));
		HashSet<Character> _TK = new HashSet<>(Arrays.asList('{', '}', '[', ']', ',', ':'));
		HashSet<Character> _LT = new HashSet<>(Arrays.asList(',', ']', '}'));

		while (this.input.charAt(this.i) != '{')
			this.i++;

		while (this.i < this.n) {
			if (_WS.contains(this.input.charAt(this.i))) {
				this.i++;
				continue;
			}

			else if (_TK.contains(this.input.charAt(this.i)))
				this.stream.add(new JsonToken(String.valueOf(this.input.charAt(this.i)), String.valueOf(this.input.charAt(this.i))));

			else if (this.input.charAt(this.i) == '"') {
				StringBuilder id = new StringBuilder();
				this.i++;
				while (this.i < this.n) {
					if (this.input.charAt(this.i) == '"' && this.input.charAt(this.i - 1) != '\\')
						break;

					else if (this.input.charAt(this.i) == '"' && this.input.charAt(this.i - 1) == '\\')
						id.setCharAt(id.length() - 1, '"');

					else
						id.append(this.input.charAt(this.i));

					this.i++;
				}

				this.stream.add(new JsonToken("ID", id.toString()));
			}

			else {
				StringBuilder lit = new StringBuilder();
				while (this.i < this.n) {
					if (_LT.contains(this.input.charAt(this.i))) {
						this.i--; // back-up so we can capture the ending token in the stream
						break;
					}
					else if (!_WS.contains(this.input.charAt(this.i))) {
						lit.append(this.input.charAt(this.i));
					}

					this.i++;
				}

				this.stream.add(new JsonToken("LIT", lit.toString()));
			}

			this.i++;
		}
	}

	private JsonToken nextToken() {
		this.i++;
		if (this.i < this.stream.size())
			return this.stream.get(this.i);
		else
			return new JsonToken("EOF", "EOF");
	}

	private static JsonElement parseLiteral(String text) {
		if (text.equals("true"))
			return new JsonBoolean(true);
		else if (text.equals("false"))
			return new JsonBoolean(false);
		else if (text.equals("null"))
			return new JsonNull();
		else if (text.contains("."))
			return new JsonFloat(Double.parseDouble(text));
		else
			return new JsonInteger(Integer.parseInt(text));
	}

	private JsonList parseList() throws Exception {
		JsonList list = new JsonList();
		JsonToken token = this.nextToken();
		while (!token.token.equals("]")) {
			switch (token.token) {
				case "ID":
					list.add(new JsonString(token.text));
					break;
				case "LIT":
					list.add(JsonParser.parseLiteral(token.text));
					break;
				case "{":
					list.add(this.parseObject());
					break;
				case "[":
					list.add(this.parseList());
					break;
			}

			token = this.nextToken();
			if (token.token.equals("EOF"))
				break;
		}

		return list;
	}

	private JsonObject parseObject() throws Exception {
		JsonObject obj = new JsonObject();
		JsonToken key = this.nextToken();

		while (!key.token.equals("}")) {
			if (key.token.equals(","))
				key = this.nextToken();

			JsonToken separator = this.nextToken();
			JsonToken value = this.nextToken();

			if (!key.token.equals("ID"))
				throw new Exception(String.format("Invalid token detected for object key: %s", key.token));
			if (!separator.token.equals(":"))
				throw new Exception(String.format("Invalid token detected for key-value separator: %s", key.token));

			switch (value.token) {
				case "{":
					obj.set(key.text, this.parseObject());
					break;
				case "[":
					obj.set(key.text, this.parseList());
					break;
				case "ID":
					obj.set(key.text, new JsonString(value.text));
					break;
				case "LIT":
					obj.set(key.text, JsonParser.parseLiteral(value.text));
					break;
				default:
					throw new Exception(String.format("Unknown value token: %s", value.token));
			}

			key = this.nextToken();
		}

		return obj;
	}

}
