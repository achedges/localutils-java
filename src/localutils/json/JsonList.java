package localutils.json;

import java.util.ArrayList;
import java.util.List;

public class JsonList extends JsonElement {
	private List<JsonElement> list = new ArrayList<>();

	@Override
	public String serialize(int indent) {
		StringBuilder ret = new StringBuilder();
		ret.append("[ ");

		if (indent > 0) ret.append("\n");

		String tab = indent > 0 ? new String(new char[indent+1]).replace('\0', '\t') : "";
		int sz = this.list.size();
		for (JsonElement element : this.list) {
			ret.append(String.format("%s%s", tab, element.serialize(indent)));
			if ((--sz) > 0) ret.append(", ");
			if (indent > 0) ret.append("\n");
		}

		ret.append(String.format("%s]", indent > 0 ? tab.substring(0, indent) : " "));

		return ret.toString();
	}

	public int getListSize() {
		return this.list.size();
	}

	public JsonElement get(int index) {
		return this.list.get(index);
	}

	public void add(JsonElement element) {
		this.list.add(element);
	}
}
