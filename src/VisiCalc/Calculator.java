package VisiCalc;

public class Calculator {
	private String expression;
	private float value;
	
	public Calculator(String expression)
	{
		this.expression = expression;
		Calculate();
	}

	private void Calculate()
	{
		format();
		while (!isFinished())
		{
			float numA;
			float numB;
			float answer;
			char operator;
			if (operator.equals('+'))
			{
				numA + numB = answer;
			}
			else if (operator.equals("-"))
			{
				numA - numB = answer;
			}
			else if (operator.equals("*"))
			{
				numA * numB = answer;
			}
			else if (operator.equals("/"))
			{
				numA / numB = answer;		
			}
		}
	}
	
	private boolean isFinished()
	{
		if (expression.contains(" "))
		{
			return false; 
		}
		else
		{
			return true;
		}
	}
	
	private void format()
	{
		//TODO
		//Make this so that it will format the String expression
		//EX: "1+2 + -3 *9-2 *6^4/2*-9" -------> "1 + 2 + -3 * 9 - 2 * 6^4 / 2 * -9"
	}
	
	public float getValue()
	{
		return value;
	}
	
	
}
