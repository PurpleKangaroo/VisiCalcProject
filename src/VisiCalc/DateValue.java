package VisiCalc;

import java.util.GregorianCalendar;

public class DateValue extends Value{
	private GregorianCalendar value;
	
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

	public GregorianCalendar getDateValue()
	{
		return value;
	}
	
}
