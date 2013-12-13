package VisiCalc;

public class Calculator {
	private Operator[] operators;
	
	public Calculator()
	{
		
	}
	
	public float calculate(String expression)
	{
		operators = Operator.findOperations(expression);
		getParenthesisSets();
		
	}
	
	private String getParenthesisSets(String expression) throws ParenthesisException
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
		for (int i = 0; i < expression.length(); i++)
		{
			if (expression.charAt(i) == '(')
			{
				//Create new parenthesis
			}
			if(expression.charAt(i) == ')')
			{
				//Close the correct parenthesis
			}
		}
		if (openCounter != closeCounter)
		{
			ParenthesisException parenthesis = new ParenthesisException();
			throw parenthesis;
		}
	}

}
