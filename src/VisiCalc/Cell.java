package VisiCalc;

import java.util.GregorianCalendar;
import java.util.ArrayList;

/**
 * A class of object that represents a cell in the spreadsheet.
 * This class of object is made so that it can adjust to different types of contents (e.g. date, int, String, etc.)
 * @author Holt Maki
 * @author Devon Grove
 */

//TODO - make changes so that string is in "double quotes"
public class Cell {
	private String cellStr; //Example display: "Devon"
	private String displayString; //Example display: Devon
	private ValueType type;
	private Value value;
	private boolean currency;
	//TODO - make the formula accept commands like ">frac" and ">dec" so that numbers can be converted from fractions to decimals
	
	/**
	 * Creates a cell object.
	 * See {@linkplain Value}, the object type belonging to the cell's contents themselves.
	 * @param cellString - the user input typed into the cell to be interpreted as some type of value.
	 */
	public Cell(String cellString)//Receives the user's input.
	{
		cellStr = cellString;
		setCurrency();
		setValueType();
		setValue();
	}
	
	/**
	 * Sets the type of value contained in the cell based on what setValueType method has determined.
	 */
	private void setValue()
	{
		switch(type)//USE A SWITCH TO DERTERMINE THE TYPE OF Value THAT THE CELL SHOULD CONTAIN
		{
		case STRING:
			value = new StringValue(cellStr);
			break;
		case DATE:
			value = new DateValue(cellStr);
			break;
		case NUMBER:
			value = new NumberValue(cellStr);
			break;
		case FORMULA:
			value = new FormulaValue(cellStr);
			break;
		case BOOLEAN:
			value = new BooleanValue(cellStr);
			break;
		}
	}
	
	/**
	 * Determines if the user input should be interpreted as currency and sets whether this is true or false.
	 */
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
	
	/**
	 * Tests for each value type to determine which the cell should contain.
	 */
	private void setValueType()//TODO: this does not appear to actually set the value type?
	{
		boolean found = false;
		type = null;
		found = dateTest(cellStr);
		
		if (!found)
		{
			found = stringTest(cellStr);
		}
		
		if (!found)
		{
			found = booleanTest(cellStr);
		}

		if (!found)
		{
			found = fractionTest(cellStr);
		}
		
		if (!found)
		{
			found = numberTest(cellStr);
		}
		
		if (!found)
		{
			found = formulaTest(cellStr);
		}

	}
	
	
	/**
	 * Tests the value stored in the cell to see if it should be of the date type.
	 * @param cellString - the value stored in the cell to be tested as a date.
	 * @return dayTest - whether or not the cellString is a date.
	 */
	private boolean dateTest(String cellString)
	{
		boolean dayTest = false;
		try
		{
			if (cellString.charAt(2) == '/' && cellString.charAt(5) == '/'&& cellString.length() == 9)
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
						dayTest = true;
						type = ValueType.DATE;
					}
				}
			
				catch (Exception exception)
				{
					dayTest = false;
					type = null;
				}
			}
		}
		catch (Exception e)
		{
			
		}
		
		return dayTest;
	}

	/**
	 * Tests the value stored in the cell to see if it should be of the Boolean type.
	 * @param cellString - the value stored in the cell to be tested as a Boolean.
	 * @return booleanTest - whether or not the cellString is a Boolean.
	 */
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
	
	/**
	 * Tests the value stored in the cell to see if it should be of the fraction type.
	 * @param cellString - the value stored in the cell to be tested as a fraction.
	 * @return fracTest - whether or not the cellString is a fraction.
	 */
	private boolean fractionTest(String cellString)
	{
		boolean fracTest = true;
		type = ValueType.FRACTION;
		char[] validChars = {'1', '2', '3', '4', '5', '6', '7', '8','9', '0'};
		String fracString = cellString + "";
		fracString.replace("/", "");
		
		for(int i = 0; i<cellString.length() && fracTest == true; i++)
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
				fracTest = false;
				type = null;
			}
		}
		return fracTest;
	}

	/**
	 * Tests the value stored in the cell to see if it should be of the number type.
	 * @param cellString - the value stored in the cell to be tested as a number.
	 * @return numTest - whether or not the cellString is a number.
	 */
	private boolean numberTest(String cellString)
	{//TODO: FIX
		boolean numTest = true;
		type = ValueType.NUMBER;
		try
		{
			@SuppressWarnings("unused")
			float a = Float.parseFloat(cellString);
			a-=3;
		}
		catch (Exception exception)
		{
			numTest = false;
			type = null;
		}
		
		return numTest;
	}
	
	/**
	 * Tests the value stored in the cell to see if it should be of the formula type.
	 * @param cellString - the value stored in the cell to be tested as a formula.
	 * @return formTest - whether or not the cellString is a formula.
	 */
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
		
		String cellString1 = cellString + "";
		boolean formTest = false;
		if(cellString1.contains(" + ") || cellString1.contains(" / ") || cellString1.contains(" * ") || cellString1.contains(" - ") || cellString1.contains(" ^ ") || cellString1.contains("\\sum\\") ||cellString1.contains("\\avg\\")||found);
		{
			type = ValueType.FORMULA;
			formTest = true;
		}
		
		
		return formTest;
	}
	
	/**
	 * Tests the value stored in the cell to see if it should be of the string type.
	 * @param cellString - the value stored in the cell to be tested as a string.
	 * @return strTest - whether or not the cellString is a string.
	 */
	private boolean stringTest(String cellString)
	{
		boolean strTest = false;
		if(cellString.charAt(0) == '\"' && cellString.charAt(cellString.length() - 1) == '\"')
		{
			type = ValueType.STRING;
			strTest = true;
		}
		else
		{
			type = null;
			strTest = false;
		}
		return strTest;
	}
	
	/**
	 * Checks if a proposed date is valid.
	 * @param day - the day within the date to be tested.
	 * @param month - the month within the date to be tested.
	 * @param year - the year within the date to be tested.
	 * @return valid - whether or not the day, month, and year provided make a valid date.
	 */
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

	/**
	 * Gets the value in String form so that it may be displayed in the spreadsheet
	 * @return displayString - the value in the String Form that will be displayed in the spreadsheet
	 */
	public String getDisplayString() { //TODO: Value has another method just like this one. Are they both necessary?
		return displayString;
	}
	
	/**
	 * Getter for the value of the cell.
	 * @return value - the value of the cell.
	 */
	public Value getValue()
	{
		return value;
	}
	
}
