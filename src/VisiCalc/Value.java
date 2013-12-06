package VisiCalc;

public class Value {
	private String valueStr;
	private static Value[][] cellValues = new Value[22][12];//Holds all values so that cells that contain another cells value in a formula can get the cell value
	private static final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K','L'};
	
	public Value(String ValueString)
	{
		valueStr = ValueString;
	}
	
	
	public String getValueStr()
	{
		return valueStr;
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
			return index;
		}
		else
		{
			
			CharNotFoundException charNotFound = new CharNotFoundException();
			throw charNotFound;
		}
	}
}
