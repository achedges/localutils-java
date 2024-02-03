package com.adamhedges.utilities.json;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class JsonObject extends JsonElement {
	private TreeMap<String, JsonElement> objectTreeMap = new TreeMap<>();

	@Override
	public String serialize(int indent) {
		StringBuilder ret = new StringBuilder();
		ret.append("{ ");

		if (indent > 0) ret.append("\n");

		String tab = indent > 0 ? new String(new char[indent]).replace('\0', '\t') : "";
		int sz = this.objectTreeMap.size();
		for (Map.Entry<String, JsonElement> entry : this.objectTreeMap.entrySet()) {
			ret.append(String.format("%s\"%s\": %s", tab, entry.getKey(), entry.getValue().serialize(indent)));
			if ((--sz) > 0) ret.append(", ");
			if (indent > 0) ret.append("\n");
		}

		ret.append(String.format("%s}", indent > 0 ? tab.substring(0, indent - 1) : " "));

		return ret.toString();
	}

	@Override
	public JsonObject getObjectElement() {
		return this;
	}

	public int getObjectSize() {
		return this.objectTreeMap.size();
	}

	public boolean contains(String key) {
		return this.objectTreeMap.containsKey(key);
	}

	public JsonElement get(String key) {
		return this.objectTreeMap.get(key);
	}

	public void set(String key, JsonElement value) {
		this.objectTreeMap.put(key, value);
	}

	public Set<String> getKeys() {
		return this.objectTreeMap.keySet();
	}
}
