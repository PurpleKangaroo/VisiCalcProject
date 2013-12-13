package VisiCalc;

import java.util.ArrayList;

public enum Operator {
	PARENTHESIS_OPEN(1,'('),
	PARENTHESIS_CLOSE(1, ')'),
	EXPONENT(2,'^'),
	MULTIPLICATION(3, '*'),
	DIVISION(3, '/'),
	ADDITION(4, '+'),
	SUBTRACTION(4, '-');
	
	private final int priority;
	private final char symbol;
	private static final char[] symbolList = {'(', ')', '^', '*', '/', '+', '-'};
	
	Operator(int priority, char symbol)
	{
		this.priority = priority;
		this.symbol = symbol;
	}
	
	public static Operator[] findOperations(String expression)
	{
		expression = expression.toUpperCase();
		ArrayList<Operator> operators = new ArrayList<Operator>();
		for (int i = 0; i<expression.length(); i++)
		{
			for(int n = 0; n<symbolList.length; n++)
			{
				if(expression.charAt(i) == symbolList[n])
				{
					operators.add(getOperator(symbolList[n]));
				}
			}
		}
		Operator[] operatorArray = (Operator[]) operators.toArray();
		return operatorArray;
	}
	
	private static Operator getOperator(char symbol)
	{
		Operator a = null;
		if(symbol == Operator.ADDITION.symbol)
		{
			a = Operator.ADDITION;
		}
		if(symbol == Operator.PARENTHESIS_OPEN.symbol)
		{
			a = Operator.PARENTHESIS_OPEN;
		}
		if(symbol == Operator.PARENTHESIS_CLOSE.symbol)
		{
			a = Operator.PARENTHESIS_CLOSE;
		}
		if(symbol == Operator.EXPONENT.symbol)
		{
			a = Operator.EXPONENT;
		}
		if(symbol == Operator.MULTIPLICATION.symbol)
		{
			a = Operator.MULTIPLICATION;
		}
		if(symbol == Operator.DIVISION.symbol)
		{
			a = Operator.DIVISION;
		}
		if(symbol == Operator.SUBTRACTION.symbol)
		{
			a = Operator.SUBTRACTION;
		}
		return a;
	}
}
