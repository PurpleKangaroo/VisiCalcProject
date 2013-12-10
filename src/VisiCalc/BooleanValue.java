package VisiCalc;

public class BooleanValue extends Value {
	
	private boolean value;
	
	public BooleanValue(String ValueString)
	{
		super(ValueString);
		setValue();
	}
	
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
