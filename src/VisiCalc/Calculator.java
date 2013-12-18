package VisiCalc;

public class Calculator {
	private Operator[] operators;
	
	public Calculator()
	{
		
	}
	
	public float calculate(String expression) throws ParenthesisException
	{
		for (int i = 0; i < 10; i++)
		{	expression.replaceAll(i + "(", i + "*(");
			expression.replaceAll(")" + i, ")*" + i);
		}
		operators = Operator.findOperations(expression);
		getParenthesis(expression);
		
		
	}
	
	private String getParenthesis(String expression) throws ParenthesisException
	{
		//TODO use recursion to keep finding parenthesis inside of parenthesis.
		class Parenthesis
		{
			private int locationStart;
			private int locationEnd;
			public Parenthesis(int locationStart)
			{
				this.locationStart = locationStart;
			}
			
			public void setEnd()
			{
				
			}
		}
		
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
		
		
		
}
