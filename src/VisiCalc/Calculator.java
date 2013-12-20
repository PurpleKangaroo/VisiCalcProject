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
			Operator[] operatorsUsed = Operator.findOperations(expression);
			
			for (int i = 0; i < operatorsUsed.length; i++)
			{
				Operator operator = operatorsUsed[i];
				if (operator.equals("+"))
				{
					String numA = expression.split("+")[0];;
				}
				else if (operator.equals("-"))
				{
				}
				else if (operator.equals("*"))
				{
					
				}
				else if (operator.equals("/"))
				{
							
				}
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
		String[] whitespace1 = {" *", "* ", " /", "/ ", "+ ", " +", "- ", " -", "( ", " (", " )", ") ", "^ ", " ^"};
		String[] whitespace2 = {"*", "*", "/", "/", "+", "+", "-", "-", "(", "(", ")", ")", "^", "^"};
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
		
		//Reinsert whitespace
		String[] negative = {"* ", "^ ", "/ ", "( ", ") "};
		String[] operatorStrs = {"-", "+", "*", "^", "/"};
		String[] operatorWSpace = {" - ", " + ", " * ", " ^ ", " / "};
				
		for (int i = 0; i< operatorStrs.length; i++)
		{
			expression.replaceAll(operatorStrs[i], operatorWSpace[i]);
		}
		
		for (int i = 0; i < negative.length; i++)
		{
			//Negatives
		}
	}
	
	public float getValue()
	{
		return value;
	}
	
	
}
