package VisiCalc;

public class StringValue extends Value
{
	private String value;
	
	/**
	 * A value type object representing a string value type within a cell.
	 * @param valueString - the user input typed into the cell that has been interpreted as a string value.
	 */
	public StringValue(String valueString) {
		super(valueString);
		setValue();
		displayValue();
	}

	/**
	 * Sets the value of a String cell type.
	 */
	private void setValue()
	{
		value = getInputStr().substring(1, getInputStr().length() -1);
		setDisplayStr(value + "");
	}
	
	private String displayValue()
	{
		String noQuotesValue = value.replace("\"", "");
		return noQuotesValue;
	}
	
	/**
	 * Returns the value of a string cell type.
	 * @return value - the value of the cell.
	 */
	public String getStringValue()
	{
		return value;
	}
	
}
