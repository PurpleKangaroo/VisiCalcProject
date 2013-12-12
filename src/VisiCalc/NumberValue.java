package VisiCalc;

public class NumberValue extends Value{
	private float value;
	
	public NumberValue(String valueString) {
		super(valueString);
		setValue();
	}
	
	private void setValue()
	{
		value = Float.parseFloat(getInputStr());
	}
	
	public float getFloatValue()
	{
		return value;
	}

}
