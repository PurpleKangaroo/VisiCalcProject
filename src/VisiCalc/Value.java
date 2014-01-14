package VisiCalc;

/**
 * A class of object that represents the value of a cell
 * @author Holt Maki
 *
 */
public class Value {
	private String displayStr;
	private static Value[][] cellValues = new Value[22][12];//Holds all values so that cells that contain another cells value in a formula can get the cell value
	private static final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K','L'};
	private String inputStr;
	
	/**
	 * Creates an object that represents the value of a cell.
	 * See value subtypes {@linkplain BooleanValue}, {@linkplain DateValue}, {@linkplain FormulaValue}, {@linkplain NumberValue}.
	 * @param valueString - the user input typed into the cell to be interpreted as some type of value.
	 */
	public Value(String valueString)
	{
		inputStr = valueString;
	}
	
	/**
	 * Sets the string to be displayed to the user.
	 * @param str - the string to be displayed.
	 */
	public void setDisplayStr(String str)
	{
		displayStr = str;
	}
	
	/**
	 * Gets the value in String form so that it may be displayed in the spreadsheet
	 * @return displayStr - the value in the String Form that will be displayed in the spreadsheet
	 */
	public String getDisplayStr()
	{
		return displayStr + "";
	}
	
	/**
	 * Gets the String that the user input into the cell. 
	 * This is used when the user clicks on the cell to edit it so that they may remove the ""s in a String for example to change the type of value contained in the cell.
	 * @return inputStr - The String that the user originally input.
	 */
	public String getInputStr()
	{
		return inputStr;
	}
	
	/**
	 * Finds where a letter is is in the alphabet so that it can be found in an Array.
	 * @param letter - the letter that is having its location found
	 * @return index - the index of the letter
	 * @throws CharNotFoundException
	 */
	public int getAlphabetIndex(char letter) throws CharNotFoundException
	{
		String a = letter + "";
		a = a.toUpperCase();
		letter = a.charAt(0);
		boolean found = false;
		int index = -1;
		for (int i = 0; i<12 && !found; i++)
		{
			if (alphabet[i] == letter)
			{
				found = true;
				index = i;
			}
		}
		if (index>=0)
		{
			index++;
			return index;
		}
		else
		{
			
			CharNotFoundException charNotFound = new CharNotFoundException();
			throw charNotFound;
		}
	}
}
