package VisiCalc;

public class BooleanValue extends Value {
	
	private boolean value;
	
	/**
	 * A type object representing a Boolean value type within a cell.
	 */
	public BooleanValue(String ValueString)
	{
		super(ValueString);
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
