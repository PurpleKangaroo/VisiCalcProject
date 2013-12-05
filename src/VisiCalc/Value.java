package VisiCalc;

public class Value {
	private String valueStr;
	private static Value[][] cellValues;//Holds all values so that cells that contain another cells value in a formula can get the cell value
	
	public Value(String ValueString)
	{
		valueStr = ValueString;
	}
	
	
	public String getValueStr()
	{
		return valueStr;
	}
	
}
