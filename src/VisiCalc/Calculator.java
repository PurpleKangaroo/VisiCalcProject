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
		String[] whitespace1 = {" *", "* ", " /", "/ ", "+ ", " +", "- ", " -", "( ", " (", " )", ") "};
		String[] whitespace2 = {"*", "*", "/", "/", "+", "+", "-", "-", "(", "(", ")", ")"};
		for(int i = 0; i < whitespace1.length; i++)
		{
			while(expression.contains(whitespace1[i]))
			{
				expression = expression.replaceAll(whitespace1[i], whitespace2[i]);
			}	
		}
		String[] operatorEq = {"--", "-+", "+-"};
		String[] operatorEqReplace = {"+", "-", "-"};
		
		boolean done = false;
		boolean[] finished = {false, false, false};
		
		for (int i = 0 ; i < operatorEq.length && !done; i++)
		{
			
			finished[i%3] = true;
			
			while(expression.contains(operatorEq[i%3]))
			{
				expression = expression.replace(operatorEq[i%3], operatorEqReplace[i%3]);
				finished[i%3] = false;
			}
			
			done = true;
			for (int n = 0; n < 3; n++)
			{
				if(finished[n] == false)
				{
					done = false;
				}
			}
			
		}
	}
	
	public float getValue()
	{
		return value;
	}
	
	
}
