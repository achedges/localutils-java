package localutils.tests;

import localutils.tabulator.ColumnDefinition;
import localutils.tabulator.Tabulator;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestTabulator  {

	@Test
	public void testTableOutput() throws Exception {

		Tabulator tabulator = new Tabulator();
		tabulator.addColumn(new ColumnDefinition("Symbol")); // test with ColumnDefinition
		tabulator.addColumn("Date"); // test with name only
		tabulator.addColumn(new ColumnDefinition("Volume"));
		tabulator.addColumn(new ColumnDefinition("Avg Price"));
		tabulator.addColumn(new ColumnDefinition("Rate"));

		List<List<String>> records = new ArrayList<>();
		records.add(Arrays.asList("ASDF", "20200930", String.format("%,d", 1234567), String.format("$%,.2f", 1234.1234), String.format("%.2f%%", 33.19)));
		records.add(Arrays.asList("QWER", "20200930", String.format("%,d", 987654321), String.format("$%,.2f", 10.1423), String.format("%.2f%%", 78.34562)));
		records.add(Arrays.asList("ASDF", "20201001", String.format("%,d", 3142536), String.format("$%,.2f", 1211.89), String.format("%.2f%%", 100.0)));
		records.add(Arrays.asList("QWER", "20201001", String.format("%,d", 786466378), String.format("$%,.2f", 11.0), String.format("%.2f%%", 0.0)));

		String output = tabulator.toTable(records, 1);
		String expected = new String(Files.readAllBytes(Paths.get("table-results.txt")));

		Assert.assertEquals(expected, output);

	}

}
