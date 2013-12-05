package VisiCalc;

import java.util.GregorianCalendar;
import java.util.ArrayList;

/**
 * A class of object that represents a cell in the spreadsheet.
 * This class of object is made so that it can adjust to different types of contents (e.g. date, int, String, etc.)
 * @author Holt Maki
 *
 */

//TODO - make changes so that string is in "double quotes"
public class Cell {
	private static String[][] cellValues;
	//TODO - either keep this in this class or move to value class. 
	//There should be a static variable that can be accesed for adding the other cell values together?????
	//THINKING behind that is that then the class contains all of the info about the other cells. 
	//This might make getting formulas easier.
	private String cellStr;
	private ValueType type;
	private Value Value;
	private boolean currency;
	//TODO - make the formula accept comands like ">frac" and ">dec" so that numbers can be converted from fractions to decimals
	public Cell(String cellString)
	{
		cellStr = cellString;
		setCurrency();
		setValueType();
		setValue();
	}
	
	private void setValue()
	{
		switch(type)//USE A SWITCH TO DERTERMINE THE TYPE OF Value THAT THE CELL SHOULD CONTAIN
		{
		case STRING:
			Value = new StringValue(cellStr);
			break;
		case DATE:
			Value = new DateValue(cellStr);
			break;
		case NUMBER:
			Value = new NumberValue(cellStr);
			break;
		case FORMULA:
			Value = new FormulaValue(cellStr);
			break;
		case FRACTION:
			Value = new FractionValue(cellStr);
			break;
		case BOOLEAN:
			Value = new BooleanValue(cellStr);
			break;	
		}
	}
	
	private void setCurrency()
	{
		if(cellStr.contains("$"))
		{
			currency = true;
			cellStr.replaceAll("$", "");
		}
		else
		{
			currency = false;
		}
	}
	
	private void setValueType()
	{
		boolean found = false;
		type = null;
		found = dateTest(cellStr);
		
		if (found == false)
		{
			stringTest(cellStr);
		}
		
		if (found == false)
		{
			found = booleanTest(cellStr);
		}

		if (found == false)
		{
			found = fractionTest(cellStr);
		}
		
		if (found == false)
		{
			found = numberTest(cellStr);
		}
		
		if(found == false)
		{
			found = formulaTest(cellStr);
		}

	}
	
	private String getFormattedCellString(String Str)
	{
		for (int n=0; n<12; n++)
		{
			char[] chars = {'0', '1', '2', '3', '4','5','6', '7','8','9',')','('};
			String i = chars[n] + "";
			String subtractBefore = i + "-";
			String subtractAfter = "-" + i;
			String devideBefore = " /" + i;
			String exponentBefore = " ^"+ i;
			
			Str = Str.replaceAll(i + "\\+", (i + " +"));
			Str = Str.replaceAll("\\+" + i, "+ " + i);
			Str = Str.replaceAll(i + "\\*", i + " *");
			Str = Str.replaceAll("\\*" + i, "* " + i);
			Str = Str.replaceAll(subtractBefore, i + " -");
			Str = Str.replaceAll("\\^" + i, "^ " + i);//TODO If there are problems, remove the \\
			Str = Str.replaceAll(i + "\\^", i + " ^");//TODO If there are problems, remove the \\
			
			while (Str.toLowerCase().contains(subtractAfter) && Str.indexOf(subtractAfter) >0 && Str.substring(Str.indexOf(subtractAfter)-1, Str.indexOf(subtractAfter)).contains(" ") == false)
			{
				Str = Str.replace(subtractAfter,"- " + i);
			}
			while (Str.toLowerCase().contains(devideBefore) )
			{
				Str = Str.replace(devideBefore," / " + i);
			}
			
			
			if (Str.toLowerCase().contains("+-"))
			{
				Str = Str.replaceAll("\\+-", "+ -");
			}
			if (Str.toLowerCase().contains("--"))
			{
				Str = Str.replaceAll("--", "- -");
			}
			if (Str.toLowerCase().contains("*-"))
			{
				Str = Str.replaceAll("\\*-", "* -");
			}
			if (Str.toLowerCase().contains("^-"))
			{
				Str = Str.replaceAll("\\*-", "* -");
			}
			
		}
		return Str;
	}
	
	private boolean dateTest(String cellString)
	{
		boolean date = false;
		if (cellString.charAt(2) == '/' && 
			cellString.charAt(5) == '/'&& 
			cellString.length() == 9)
		{
			try	//IF there is not an int here, an exception will be thrown when a non int is parsed then multiplied.							{
			{
				String dateStr = cellString.replaceAll("/", "");
				for (int i = 0; i<7; i++)
				{
					@SuppressWarnings("unused")
					int j = Integer.parseInt(dateStr.charAt(i)+ "");
					j*=2;
				}
				int monthInt = Integer.parseInt(dateStr.substring(0,2));
				int yr = Integer.parseInt(dateStr.substring(4));
				int day = Integer.parseInt(dateStr.substring(2,4));
				if (validateDate(day, monthInt, yr))
				{
					date = true;
					type = ValueType.DATE;
				}
			}
		
			catch (Exception exception)
			{
				date = false;
				type = null;
			}
		}
		
		return date;
	}

	private boolean booleanTest(String cellString)
	{
		boolean booleanTest;
		if(cellString.equalsIgnoreCase("True") || cellString.equalsIgnoreCase("False"))
		{
			booleanTest = true;
			type = ValueType.BOOLEAN;
		}
		else
		{
			 booleanTest = false;
			 type = null;
		}
		
		return booleanTest;
	}
	
	private boolean fractionTest(String cellString)
	{
		boolean fractionTest = true;
		type = ValueType.FRACTION;
		char[] validChars = {'1', '2', '3', '4', '5', '6', '7', '8','9', '0'};
		String fracString = cellString + "";
		fracString.replace("/", "");
		
		for(int i = 0; i<cellString.length() && fractionTest == true; i++)
		{
			boolean validChar = false;
			for (int n = 0; n<10; n++)
			{
				if(cellString.charAt(i) == validChars[n])
				{
					validChar = true;
				}
			}
			if (!validChar)
			{
				fractionTest = false;
				type = null;
			}
		}
		return fractionTest;
	}

	private boolean numberTest(String cellString)
	{
		boolean numTest = true;
		type = ValueType.NUMBER;
		try
		{
			@SuppressWarnings("unused")
			float a = Float.parseFloat(cellString);
			a*=3;
		}
		catch (Exception exception)
		{
			numTest = false;
			type = null;
		}
		
		return numTest;
	}
	
	private boolean formulaTest(String cellString)
	{
		char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'};
		ArrayList<String> cellNames = new ArrayList<String>();
		for(int i = 1; i<23; i++)
		{
			for (int n = 0; n<12; n++)
			{
				cellNames.add(i + letters[n] + "");
			}
		}
		boolean found = false;
		for(int i = 1; !found && i<264; i++)
		{
			if(cellNames.contains(cellNames.get(i)))
			{
				found = true;
			}
		}
		
		String cellString1 = getFormattedCellString(cellString + "");
		boolean formula = false;
		if(cellString1.contains(" + ") || cellString1.contains(" / ") || cellString1.contains(" * ") || cellString1.contains(" - ") || cellString1.contains(" ^ ") || cellString1.contains("\\sum\\") ||cellString1.contains("\\avg\\")||found);
		{
			type = ValueType.FORMULA;
			formula = true;
		}
		
		
		return formula;
	}
	
	private boolean stringTest(String cellString)
	{
		boolean found = false;
		if(cellString.charAt(0) == '\"' && cellString.charAt(cellString.length() - 1) == '\"')
		{
			type = ValueType.STRING;
			found = true;
		}
		else
		{
			type = null;
			found = false;
		}
		return found;
	}
	
	private boolean validateDate(int day, int month, int year)
	{
		boolean valid = false;
		if (month<= 12 && month>0)
		{
			GregorianCalendar a = new GregorianCalendar();
			boolean leapYear = a.isLeapYear(year);
			
			if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
			{
				if (day <=31 || day >0)
				{
					valid = true;
				}
				
			}
			
			else if (month == 4 || month == 6 || month == 9 || month == 11)
			{
				if (day <=30 || day > 0)
				{
					valid = true;
				}
				
			}
			
			else if (month == 2 && leapYear)
			{
				if (day <=29 || day > 0)
				{
					valid = true;
				}
				
			}
			
			else if (month == 2 && !leapYear)
			{
				if (day <=28 || day > 0)
				{
					valid = true;
				}
				
			}
		}
		return valid;
	}
	
	/**
	 * Gets the contents of the cell in String format so that it can be put into the table.
	 * @return cellString - the String that the cell will contain in the spreadsheet.
	 */
	public String getCellString()
	{
		String cellString = cellStr + "";
		if(currency == true)
		{
			cellString = "$" + cellStr;
		}
		return cellString;
	}
}
