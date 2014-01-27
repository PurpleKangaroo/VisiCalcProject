package VisiCalc;

public class NumberValue extends Value{
	private float value;
	
	/**
	 * A value type object representing a date value type within a cell.
	 * @param valueString - the user input typed into the cell that has been interpreted as a number value.
	 */
	public NumberValue(String valueString) {
		super(valueString);
		setValue();
		setDisplayStr(value + "");
		System.out.println(value);
	}
	
	/**
	 * Sets the value as a float from the user input.
	 */
	private void setValue()
	{
		value = Float.parseFloat(getInputStr());
	}
	
	/**
	 * Returns the value of the cell.
	 * @return value - the number stored in the cell.
	 */
	public float getFloatValue()
	{
		return value;
	}

}
