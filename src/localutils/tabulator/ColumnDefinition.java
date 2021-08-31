package localutils.tabulator;

public class ColumnDefinition {

	public String name;
	public int padLength;
	public int fieldLength;

	public ColumnDefinition(String name) {
		this.name = name;
		this.padLength = 4;
		this.fieldLength = name.length();
	}

	public ColumnDefinition(String name, int padLength) {
		this.name = name;
		this.padLength = padLength;
		this.fieldLength = name.length();
	}

	public ColumnDefinition(String name, int padLength, int fieldLength) {
		this.name = name;
		this.padLength = padLength;
		this.fieldLength = fieldLength;
	}

	public String getJustifiedValue(String value) {
		String fmt = "%-" + (padLength + fieldLength) + 's';
		return String.format(fmt, value);
	}

}
