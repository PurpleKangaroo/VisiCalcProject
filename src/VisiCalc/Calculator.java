package VisiCalc;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Calculator {
	private String expression;
	private Object value;
	private static final String[] characters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
	
	/**
	 * Creates a Calculator object that takes input in the form of a math expression and calculates the result. Needs a spreadsheet.
	 * @param expression - the expression to be calculated.
	 * @param spread - the spreadsheet cell names are obtained from.
	 */
	public Calculator(String expression, Spreadsheet spread)
	{
		this.expression = expression;
		replaceCellName(spread);
		Calculate();
	}

	/**
	 * Creates a Calculator object that takes input in the form of a math expression and calculates the result. Does not need a spreadsheet.
	 * @param expression - the expression to be calculated.
	 */
	public Calculator(String expression)
	{
		this.expression = expression;
		Calculate();
	}
	
	/**
	 * Calculates the expression, first by formatting it correctly (no x for multiplication and spaces evenly distributed)
	 * After this, it does the evaluation.
	 */
	private void Calculate()
	{
		expression.replaceAll("x", "*");
		format();
		while(expression.contains("("))
			{
				parenthesis();
			}
		try
		{
			evaluate();
		}		
		catch(Exception e)
		{
			value = expression.replaceAll("\\+", "");
		}
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
		String[] whitespace1 = {" *", "* ", " /", "/ ", "\\+ ", " \\+", "- ", " -", "( ", " (", " )", ") ", "^ ", " ^"};
		String[] whitespace2 = {"*", "*", "/", "/", "\\+", "\\+", "-", "-", "(", "(", ")", ")", "^", "^"};
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
	
	/**
	 * Returns the value of the expression.
	 * @return value - the calculated value.
	 */
	public String getValue()
	{
		return value + "";
	}
	
	/**
	 * Uses recursion to make a new calculator for each set of parenthesis.
	 * It breaks the expression into the lowest level of parenthesis first.
	 * Works its way up so it can calculate using order of operations.
	 */
	private void parenthesis()
	{
		String parenthesisExpr = expression.substring(expression.indexOf("("), findEndParenthisis() +1);
		Calculator parenthesisCalc = new Calculator(parenthesisExpr.substring(1, parenthesisExpr.length() - 1));
		parenthesisCalc.Calculate();
		expression.replaceFirst(parenthesisExpr, parenthesisCalc.getValue() + "");
	}
	
	/**
	 * Uses recursion to make a new calculator for each set of parenthesis.
	 * @return endParenthesis - the location (an integer) of the final parenthesis of the expression.
	 */
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
	
	/**
	 * Evaluates the input expression using order of operations to give an accurate value.
	 */
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
	
	/**
	 * Replaces the cell's names with their values in string form.
	 * @param spread - the spreasheet cell values are obtained from.
	 */
	private void replaceCellName(Spreadsheet spread)
	{
		for (int i = 0; i < 12; i++)
		{
			for (int j = 21; j > 0; j--)
			{
				expression = expression.replaceAll(characters[i] + j + "", (spread.getCellVal(i, j).replaceAll("\"", "")));
				expression = expression.replaceAll(characters[i] + j + "", (spread.getCellVal(i, j).replaceAll("\"", "")).toLowerCase());
			}
		}
	}
}
