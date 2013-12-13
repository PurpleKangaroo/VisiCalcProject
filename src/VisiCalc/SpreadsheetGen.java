package VisiCalc;
import java.util.Scanner;

public class SpreadsheetGen {
	private static final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K','L'};
	//This is a temporary way of printing the spreadsheet while the VFrame is still being implemented.
	
	public static int getAlphabetIndex(char letter) throws CharNotFoundException
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
	
	public static void main(String[] args) throws CharNotFoundException
	{
		
		
		Spreadsheet test = new Spreadsheet();
		test.printSpreadsheet();
		Scanner sc = new Scanner(System.in);
		String userInput = sc.nextLine();
		while (!userInput.equalsIgnoreCase("quit"))
		{
			userInput.replaceAll(" ", "");
			String cellName = userInput.substring(0, userInput.indexOf('='));
			String assignment = userInput.substring(userInput.indexOf('=') + 1, userInput.length());
			String cellNum = cellName.substring(1, cellName.length());
			int cellRow = Integer.parseInt(cellNum) - 1;
			int cellColumn = getAlphabetIndex(cellName.charAt(0));
			test.changeSpreadsheetValue(cellRow, cellColumn, assignment);
			test.printSpreadsheet();
			userInput = sc.nextLine();
		}
		sc.close();
		System.out.println("You have decided to quit.");
	}
}
