package VisiCalc;

import java.util.HashMap;
import java.util.Map;
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
		expression.replaceAll("x", "*");
		format();
		
		while(expression.contains("("))
			{
				parenthesis();
			}
		evaluate();
	}
	
	/**
	 * Formats the expression.
	 * <li> Simplifies negatives
	 * <li> Formats whitespace
	 */
	private void format()
	{
		while(expression.substring(0, 1).equals(" "))
		{
			expression = expression.substring(1);
		}
		while(expression.substring(expression.length() - 1).equals(" "))
		{
			expression.substring(0, expression.length() - 1);
		}
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
		String[] negative = {"\\* ", "\\^ ", "/ ", "\\( ", "\\) "};
		String[] operatorStrs = {"-", "\\+", "\\*", "\\^", "/"};
		String[] operatorWSpace = {" - ", " \\+ ", " \\* ", " \\^ ", " / "};
				
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
				expression = expression.replaceAll((negative[i] + " - " + n), (negative[i] + " -" + n));
			}
		}
		expression = expression + " ";
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
		Stack<Float> numberStack = new Stack<Float>();
		Stack<String> operatorStack = new Stack<String>();
		boolean done = false;
		Map<String, Integer> operators = new HashMap<String, Integer>();
		
		operators.put(" ^ ", 3);
		operators.put(" * ", 2);
		operators.put(" / ", 2);
		operators.put(" + ", 1);
		operators.put(" - ", 1);

		float eger = Float.parseFloat(expression.substring(0,expression.indexOf(" ")));
		numberStack.push(eger);
		expression = expression.substring(expression.indexOf(" "));
		while (!done)
		{	
			try
			{
				String operator = expression.substring(0,3);
				operatorStack.push(operator);
				expression = expression.substring(3);
			
				eger = Float.parseFloat(expression.substring(0,expression.indexOf(" ")));
				numberStack.push(eger);
				expression = expression.substring(expression.indexOf(" "));
			}
			catch(RuntimeException e)
			{
				
			}
			
			try
			{
				if (operators.get(operatorStack.peek()) < operators.get(expression.substring(0,3)))
				{
					//NOTHING HAPPENS.
				}
				
				else
				{
					float second = numberStack.pop();
					float first = numberStack.pop();
					String operation = operatorStack.pop();
					
					if (operation.equals(" ^ "))
					{
						numberStack.push((float) Math.pow(first, second));
					}
					if (operation.equals(" * "))
					{
						numberStack.push(first * second);
					}
					if (operation.equals(" / "))
					{
						numberStack.push(first / second);
					}
					if (operation.equals(" + "))
					{
						numberStack.push(first + second);
					}
					if (operation.equals(" - "))
					{
						numberStack.push(first - second);
					}
				}
			}
			
			catch(RuntimeException f)
			{
				float second = numberStack.pop();
				float first = numberStack.pop();
				String operation = operatorStack.pop();
				
				if (operation.equals(" ^ "))
				{
					numberStack.push((float) Math.pow(first, second));
				}
				if (operation.equals(" * "))
				{
					numberStack.push(first * second);
				}
				if (operation.equals(" / "))
				{
					numberStack.push(first / second);
				}
				if (operation.equals(" + "))
				{
					numberStack.push(first + second);
				}
				if (operation.equals(" - "))
				{
					numberStack.push(first - second);
				}	
			}
			if (expression.length() == 1 && operatorStack.empty() == true)
			{
				done = true;
			}
		}
		value = numberStack.pop();
	}
}
