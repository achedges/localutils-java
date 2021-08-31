package localutils.tabulator;

import java.util.ArrayList;
import java.util.List;

public class Tabulator {

	private ArrayList<ColumnDefinition> columnDefinitions;

	public Tabulator() {
		columnDefinitions = new ArrayList<>();
	}

	public void addColumn(String name) {
		columnDefinitions.add(new ColumnDefinition(name));
	}

	public void addColumn(ColumnDefinition columnDef) {
		columnDefinitions.add(columnDef);
	}

	public String getTableHeader() {

		StringBuilder header = new StringBuilder("\n");
		int headerwidth = 0;

		for (ColumnDefinition def : columnDefinitions)
		{
			String s = def.getJustifiedValue(def.name);
			header.append(s);
			headerwidth += s.length();
		}

		header.append("\n");

		for (int i = 0; i < headerwidth; i++)
			header.append('-');

		return header.toString();

	}

	public String toTable(List<List<String>> records) {
		return toTable(records, -1);
	}

	public String toTable(List<List<String>> records, int segmentColumnIndex) {

		for (List<String> record : records) {
			for (int c = 0; c < columnDefinitions.size(); c++) {
				if (record.get(c).length() > columnDefinitions.get(c).fieldLength) {
					columnDefinitions.get(c).fieldLength = record.get(c).length();
				}
			}
		}

		StringBuilder output = new StringBuilder(getTableHeader() + "\n");
		if (records.size() == 0) {
			output.append("No records found\n");
			return output.toString();
		}

		// validate the segmentColumnIndex if provided
		if (segmentColumnIndex >= columnDefinitions.size())
			segmentColumnIndex = -1;

		String segmentValue = segmentColumnIndex < 0 ? "" : records.get(0).get(segmentColumnIndex);

		for (List<String> record : records) {
			if (segmentColumnIndex >= 0 && !record.get(segmentColumnIndex).equals(segmentValue)) {
				output.append(getTableHeader()).append("\n");
				segmentValue = record.get(segmentColumnIndex);
			}

			output.append(toRow(record)).append("\n");
		}

		return output.toString();

	}

	public String toRow(List<String> record) {

		StringBuilder row = new StringBuilder();
		for (int i = 0; i < columnDefinitions.size(); i++) {
			row.append(columnDefinitions.get(i).getJustifiedValue(record.get(i)));
		}
		return row.toString();

	}

}
