package localutils.json;

import java.util.*;

public class JsonParser {

	private String input;
	private int n, i;
	private ArrayList<JsonToken> stream = new ArrayList<>();

	public JsonParser(String jsonString) {
		input = jsonString.trim();
		n = input.length();
		i = 0;
	}

	public JsonElement parse() throws Exception {
		tokenizeInput();
		i = 0;

		if (stream.get(0).token.equals("{"))
			return parseObject();

		else if (stream.get(0).token.equals("["))
			return parseList();

		else
			throw new Exception("JSON documents must start with '{' or '['");
	}

	private void tokenizeInput() {
		HashSet<Character> _WS = new HashSet<>(Arrays.asList(' ', '\t', '\n', '\r'));
		HashSet<Character> _TK = new HashSet<>(Arrays.asList('{', '}', '[', ']', ',', ':'));
		HashSet<Character> _LT = new HashSet<>(Arrays.asList(',', ']', '}'));

		while (input.charAt(i) != '{' && input.charAt(i) != '[')
			i++;

		while (i < n) {
			if (_WS.contains(input.charAt(i))) {
				i++;
				continue;
			}

			else if (_TK.contains(input.charAt(i)))
				stream.add(new JsonToken(String.valueOf(input.charAt(i)), String.valueOf(input.charAt(i))));

			else if (input.charAt(i) == '"') {
				StringBuilder id = new StringBuilder();
				i++;
				while (i < n) {
					if (input.charAt(i) == '"' && input.charAt(i - 1) != '\\')
						break;

					else if (input.charAt(i) == '"' && input.charAt(i - 1) == '\\')
						id.setCharAt(id.length() - 1, '"');

					else
						id.append(input.charAt(i));

					i++;
				}

				stream.add(new JsonToken("ID", id.toString()));
			}

			else {
				StringBuilder lit = new StringBuilder();
				while (i < n) {
					if (_LT.contains(input.charAt(i))) {
						i--; // back-up so we can capture the ending token in the stream
						break;
					}
					else if (!_WS.contains(input.charAt(i))) {
						lit.append(input.charAt(i));
					}

					i++;
				}

				stream.add(new JsonToken("LIT", lit.toString()));
			}

			i++;
		}
	}

	private JsonToken nextToken() {
		i++;
		if (i < stream.size())
			return stream.get(i);
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
		JsonToken token = nextToken();
		while (!token.token.equals("]")) {
			switch (token.token) {
				case "ID":
					list.add(new JsonString(token.text));
					break;
				case "LIT":
					list.add(JsonParser.parseLiteral(token.text));
					break;
				case "{":
					list.add(parseObject());
					break;
				case "[":
					list.add(parseList());
					break;
			}

			token = nextToken();
			if (token.token.equals("EOF"))
				break;
		}

		return list;
	}

	private JsonObject parseObject() throws Exception {
		JsonObject obj = new JsonObject();
		JsonToken key = nextToken();

		while (!key.token.equals("}")) {
			if (key.token.equals(","))
				key = nextToken();

			JsonToken separator = nextToken();
			JsonToken value = nextToken();

			if (!key.token.equals("ID"))
				throw new Exception(String.format("Invalid token detected for object key: %s", key.token));
			if (!separator.token.equals(":"))
				throw new Exception(String.format("Invalid token detected for key-value separator: %s", key.token));

			switch (value.token) {
				case "{":
					obj.set(key.text, parseObject());
					break;
				case "[":
					obj.set(key.text, parseList());
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

			key = nextToken();
		}

		return obj;
	}

}
