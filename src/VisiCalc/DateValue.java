package VisiCalc;

import java.util.GregorianCalendar;

public class DateValue extends Value{
	private GregorianCalendar value;
	
	public DateValue(String ValueString) 
	{
		super(ValueString);
		setValue();
	}
	
	private void setValue()
	{
		String dateStr = getInputStr();
		value = new GregorianCalendar(Integer.parseInt(dateStr.substring(6,11)),
									Integer.parseInt(dateStr.substring(0,2)),
									Integer.parseInt(dateStr.substring(3, 5)));
	}

}
