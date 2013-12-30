package VisiCalc;

import java.util.Stack;

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
			while(expression.contains("("))
			{
				parenthesis();
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
	
	/**
	 * Formats the expression.
	 * <li> Simplifies negatives
	 * <li> Formats whitespace
	 */
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
			expression = expression.replaceAll(operatorStrs[i], operatorWSpace[i]);
		}
		
		if(expression.substring(0, 3).equals(" - "))
		{
			expression = expression.replaceFirst(" - ", "-");
		}
		
		for (int i = 0; i < negative.length; i++)
		{
			for (int n = 0; n < 10; n++)
			{
				expression = expression.replaceAll(negative[i] + " - " + n, negative[i] + " -" + n);
			}
		}
	}
	
	public float getValue()
	{
		return value;
	}
	
	private void parenthesis()
	{
		String parenthesisExpr = expression.substring(expression.indexOf("("), findEndParenthisis() +1);
		Calculator parenthesisCalc = new Calculator(parenthesisExpr.substring(1, parenthesisExpr.length() - 1));
		parenthesisCalc.Calculate();
		expression.replaceFirst(parenthesisExpr, parenthesisCalc.getValue() + "");
	}
	
	private int findEndParenthisis()
	{
		boolean found = false;
		int endParenthesis = 0;
		int open = 0;
		int closed = 0;
		for(int i = expression.indexOf("("); i < expression.length() && !found; i++)
		{
			if (expression.charAt(i) == '(')
			{
				open++;
			}
			else if(expression.charAt(i) == ')')
			{
				closed++;
			}
			if (open == closed)
			{
				endParenthesis = i;
			}
		}
		
		return endParenthesis;
	}
	
	private void evaluate()
	{
		Stack<Integer> numberStack = new Stack<Integer>();
		Stack<String> operatorStack = new Stack<String>();
		boolean done = false;
		
		int eger = Integer.parseInt(expression.substring(0,expression.indexOf(" ")));
		numberStack.push(eger);
		expression = expression.substring(expression.indexOf(" "));
		while (!done)
		{		
			String operator = expression.substring(0,3);
			operatorStack.push(operator);
			expression = expression.substring(3);
			
			eger = Integer.parseInt(expression.substring(0,expression.indexOf(" ")));
			numberStack.push(eger);
			expression = expression.substring(expression.indexOf(" "));
			
			if (expression.length() == 0)
			{
				done = true;
			}
		}
	}
}
