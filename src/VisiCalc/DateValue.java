package VisiCalc;

import java.util.GregorianCalendar;

public class DateValue extends Value{
	private GregorianCalendar value;
	
	/**
	 * A value type object representing a date value type within a cell.
	 * @param valueString - the user input typed into the cell that has been interpreted as a date value.
	 */
	public DateValue(String valueString) 
	{
		super(valueString);
		setValue();
		setDisplayStr(value + "");
	}
	
	/**
	 * Sets the value of a date cell type.
	 */
	private void setValue()
	{
		String dateStr = getInputStr();
		value = new GregorianCalendar(Integer.parseInt(dateStr.substring(6,11)),
									Integer.parseInt(dateStr.substring(0,2)),
									Integer.parseInt(dateStr.substring(3, 5)));
	}

	/**
	 * Returns the date value of the cell.
	 * @return value - the value (returned as a GregorianCalendar date).
	 */
	public GregorianCalendar getDateValue()
	{
		return value;
	}
	
}
