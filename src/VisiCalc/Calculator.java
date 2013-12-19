package VisiCalc;

public class Calculator {
	private Operator[] operators;
	
	public Calculator()
	{
		
	}
	
	public float calculate(String expression) throws ParenthesisException
	{
		expression = format(expression);
		for (int i = 0; i < 10; i++)
		{	expression.replaceAll(i + "(", i + "*(");
			expression.replaceAll(")" + i, ")*" + i);
		}
		while (!finished())
		operators = Operator.findOperations(expression);
		getParenthesis(expression);
		
		
	}
	
	private String getParenthesis(String expression) throws ParenthesisException
	{
		//TODO use recursion to keep finding parenthesis inside of parenthesis.
		int openCounter = 0;
		int closeCounter = 0;
		int level = 0;
		int index = 0;
		for (int i = 0; i < expression.length(); i++)
		{
			if (expression.charAt(i) == '(')
			{
				//Create new parenthesis
				openCounter++;
			}
			if(expression.charAt(i) == ')')
			{
				//Close the correct parenthesis
				closeCounter++;
			}
			if ((openCounter - closeCounter)> level)
			{
				level = openCounter - closeCounter;
				index = i;
			}
		}
		
		int endIndex = 0;
		
		for (int i = index; i < expression.length(); i++)//*
		{
			if (expression.charAt(i) == ')')
			{
				endIndex = i;
			}
		}
		Calculator parenthisisCalc = new Calculator();
		float a = parenthisisCalc.calculate(expression.substring(index + 1, endIndex));
		expression.replace(expression.substring(index+1, endIndex), a + "");
		
		/*if (openCounter != closeCounter)
		{
			ParenthesisException parenthesis = new ParenthesisException();
			throw parenthesis;
		}*/
	}
	
	private String format(String expression)
	{
		String[] operatorWS = {" *", "* ", "+ ", " +", " -", "- ", "/ ", " /", "^ ", " ^", "( ", ") ", " (", " )"};
		boolean formated = false;
		while(!formated)
		{
			formated = true;
			for (int i = 0; i < operatorWS.length; i++)
			{
				if( operatorWS[i]
			}
		}
	}
	
	/**
	 * A method that can tell if an expression is finished being calculated.
	 * @param expression - the expression that is being calculated
	 * @return
	 */
	private boolean finsihed(String expression)
	{
		//TODO: DEVON
		//Take negatives into account ex: expression = -4
		// in this case the expression is calculatted yet still contains a minus.
		//but if the expression is 6 - 2, then the expression is not complete
	}
		
		
		
}
