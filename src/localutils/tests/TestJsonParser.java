package localutils.tests;

import localutils.json.*;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestJsonParser {
	@Test
	public void testObjectParser() throws Exception {
		String testContents = new String(Files.readAllBytes(Paths.get("parse-obj.json")));
		JsonParser parser = new JsonParser(testContents);
		JsonElement result = parser.parse();

		Assert.assertTrue("Result should be a JsonObject", result instanceof JsonObject);

		JsonObject object = (JsonObject)result;

		Assert.assertEquals("Incorrect main object size", 1, object.getObjectSize()); // should contain a single overall object
		Assert.assertTrue("'object' identifier not found", object.contains("object"));

		Assert.assertTrue("Incorrect main object value type", object.get("object") instanceof JsonObject);

		JsonObject innerobj = (JsonObject)object.get("object");

		Set<String> keySet = new HashSet<>(Arrays.asList("string", "int", "float", "bool", "null", "list"));
		Assert.assertEquals("Unexpected keyset", keySet, innerobj.getKeys());

		Assert.assertEquals("Incorrect value for key 'string'", "\"stringvalue\"", innerobj.get("string").serialize(0));
		Assert.assertEquals("Incorrect value for key 'int'", "1", innerobj.get("int").serialize(0));
		Assert.assertEquals("Incorrect value for key 'float'", "1.123", innerobj.get("float").serialize(0));
		Assert.assertEquals("Incorrect value for key 'bool'", "true", innerobj.get("bool").serialize(0));
		Assert.assertEquals("Incorrect value for key 'null'", "null", innerobj.get("null").serialize(0));

		Assert.assertTrue("Incorrect list object value type", innerobj.get("list") instanceof JsonList);

		JsonList innerlst = (JsonList)innerobj.get("list");
		Assert.assertEquals("Incorrect size of list", 3, innerlst.getListSize());
		Assert.assertEquals("Incorrect list element 1", "\"one\"", innerlst.get(0).serialize(0));
		Assert.assertEquals("Incorrect list element 2", "\"two\"", innerlst.get(1).serialize(0));

		Assert.assertEquals("Incorrect list element 3", "\"\\\"three\\\"\"", innerlst.get(2).serialize(0));
	}

	@Test
	public void testListParser() throws Exception {
		String testContents = new String(Files.readAllBytes(Paths.get("parse-list.json")));
		JsonParser parser = new JsonParser(testContents);
		JsonElement result = parser.parse();

		Assert.assertTrue("Result object should be an array", result instanceof JsonList);

		JsonList list = (JsonList)result;
		Assert.assertEquals("Result list size is incorrect", 2, list.getListSize());

		Set<String> keySet = new HashSet<>(Arrays.asList("key", "value"));
		for (int i = 0; i < list.getListSize(); i++) {
			JsonObject obj = (JsonObject)list.get(i);
			Assert.assertEquals("Unexpected key set, item " + i, keySet, obj.getKeys());
			Assert.assertEquals("Unexpected value", "\"some-key-" + i + "\"", obj.get("key").serialize(0));
			Assert.assertEquals("Unexpected value", "\"some-value-" + i + "\"", obj.get("value").serialize(0));
		}
	}

	@Test
	public void testSerializer() {
		JsonObject obj = new JsonObject();
		obj.set("string", new JsonString("string-value"));
		obj.set("integer", new JsonInteger(123));
		obj.set("float", new JsonFloat(4.56));
		obj.set("bool", new JsonBoolean(true));
		obj.set("null", new JsonNull());

		JsonList lst = new JsonList();
		lst.add(new JsonString("list-value"));
		lst.add(new JsonInteger(879));
		lst.add(new JsonFloat(0.12));
		lst.add(new JsonBoolean(false));
		lst.add(new JsonNull());

		obj.set("list", lst);

		String expected = "{ \n" +
				"\t\"bool\": true, \n" +
				"\t\"float\": 4.56, \n" +
				"\t\"integer\": 123, \n" +
				"\t\"list\": [ \n" +
				"\t\t\"list-value\", \n" +
				"\t\t879, \n" +
				"\t\t0.12, \n" +
				"\t\tfalse, \n" +
				"\t\tnull\n" +
				"\t], \n" +
				"\t\"null\": null, \n" +
				"\t\"string\": \"string-value\"\n" +
				"}";

		String result = obj.serialize(1);
		Assert.assertEquals("Unexpected JSON object serialization", expected, result);

		expected = "{ \"bool\": true, \"float\": 4.56, \"integer\": 123, \"list\": [ \"list-value\", 879, 0.12, false, null ], \"null\": null, \"string\": \"string-value\" }";
		result = obj.serialize(0);
		Assert.assertEquals("Unexpected JSON object serialization", expected, result);
	}
}
