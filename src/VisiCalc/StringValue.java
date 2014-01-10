package VisiCalc;

public class StringValue extends Value{
	private String value;
	
	public StringValue(String valueString) {
		super(valueString);
		setValue();
		//displayValue();
	}

	/**
	 * Sets the value of a String cell type.
	 */
	private void setValue()
	{
		value = getInputStr().substring(1, getInputStr().length() -1);
		setDisplayStr(value + "");
	}
	
	/**
	private String displayValue()
	{
		String noQuotesValue = value;
		char a;
		a = value.charAt(0);
		noQuotesValue = a + "";
		return noQuotesValue;
	}
	**/
	
	public String getStringValue()
	{
		return value;
	}
	
}
