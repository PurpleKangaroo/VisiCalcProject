package VisiCalc;

public class BooleanValue extends Value {
	
	private boolean value;
	
	/**
	 * A value type object representing a Boolean value type within a cell.
	 * @param valueString - the user input typed into the cell that has been interpreted as a Boolean value.
	 */
	public BooleanValue(String valueString)
	{
		super(valueString);
		setValue();
	}
	
	/**
	 * Sets the value of a boolean cell type.
	 */
	private void setValue()
	{
		String a = getInputStr();
		if (a.equalsIgnoreCase("true"))
		{
			value = true;
		}
		else if (a.equalsIgnoreCase("false"))
		{
			value = false;
		}
	}
	
}
