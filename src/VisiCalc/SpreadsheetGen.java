package VisiCalc;
import java.util.Scanner;

public class SpreadsheetGen {
	private static final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K','L'};
	
	public static void main(String[] args) throws CharNotFoundException
	{
		Spreadsheet test = new Spreadsheet();
		test.printSpreadsheet();
		Scanner sc = new Scanner(System.in);
		String userInput = sc.nextLine();
		while (!userInput.equalsIgnoreCase("quit"))
		{
			userInput = whiteSpace(userInput);
			if (userInput.contains("clear"))
			{
				test = clear(userInput, test);
			}
			else if (expContainsCellRef(userInput))
			{
				test = changeToOtherCell(userInput, test);
				//recalc(test);
			}
			else if(userInput.contains("="))
			{
				test = setCell(userInput, test);
				//recalc(test);
			}
			else
			{
				System.out.println("ERROR: Not a valid input");
			}
			test.printSpreadsheet();
			userInput = sc.nextLine();
		}
		sc.close();
		System.out.println("You have decided to quit.");
	}
	
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
	
	private static Spreadsheet clear(String userInput, Spreadsheet sheet) throws CharNotFoundException
	{
		userInput = userInput.toUpperCase();
		userInput = userInput.replaceAll("CLEAR", "");
		userInput = userInput.replaceAll(" ", "");
		String cellName = userInput.substring(userInput.indexOf("clear") + 1);
		int row = findCellRow(cellName);
		int col = findCellCol(cellName);
		sheet = modifyCell(row, col, sheet, "0");
		return sheet;
	}
	
	private static Spreadsheet modifyCell(int row, int column, Spreadsheet sheet, String newValue)
	{
		sheet.changeSpreadsheetValue(row, column, newValue);
		return sheet;
	}
	
	private static int findCellRow(String cellName) throws CharNotFoundException
	{
		cellName = cellName.replaceAll(" ", "");
		int cellColumn = getAlphabetIndex(cellName.charAt(0))-1;
		return cellColumn;
	}
	
	private static int findCellCol(String cellName)
	{
		cellName = cellName.replaceAll(" ", "");
		String cellNum = cellName.substring(1, cellName.length());
		int cellRow = Integer.parseInt(cellNum) - 1;
		return cellRow;
	}
	
	private static Spreadsheet changeToOtherCell(String userInput, Spreadsheet sheet) throws CharNotFoundException
	{
		String cellName = userInput.substring(0, userInput.indexOf('='));
		int row = findCellRow(cellName);
		int col = findCellCol(cellName);
		String assignment = userInput.substring(userInput.indexOf('=') + 1, userInput.length());
		int assignR = findCellRow(assignment.replaceAll(" ",""));
		int assignC = findCellCol(assignment.replaceAll(" ",""));
		sheet = modifyCell(row, col, sheet, sheet.getCellVal(assignC, assignR));
		return sheet;
	}
	
	private static String whiteSpace(String a)
	{
		int quoteCount = 0;
		int z = a.length();
		for(int i = 0; i<z; i++)
		{
			if(a.charAt(i) == ' '&& quoteCount%2 ==0)
			{
				String b = a.substring(0,i);
				String c = a.substring(i+1, a.length());
				a = b+c;
				i--;
			}
			if(a.charAt(i) == '\"')
			{
				quoteCount++;
			}
			z = a.length();
		}
		//causes strings to be recognized as formulas: a = a.replaceAll("\"", "");
		return a;
	}
	
	private static Spreadsheet setCell(String userInput, Spreadsheet sheet) throws CharNotFoundException
	{
		String cellName = userInput.substring(0, userInput.indexOf('='));
		int row = findCellRow(cellName);
		int col = findCellCol(cellName);
		String assignment = userInput.substring(userInput.indexOf('=') + 1, userInput.length());
		if (assignment.contains("+") || assignment.contains("-") || assignment.contains("*")
				|| assignment.contains("/") || assignment.contains("^"))
		{
			Calculator calcExpression = new Calculator(assignment, sheet);
			assignment = calcExpression.getValue() + "";
		}
		sheet = modifyCell(row, col, sheet, assignment);
		return sheet;
	}

	private static boolean expContainsCellRef(String userInput) {
		boolean found = false;
		userInput = userInput.toUpperCase();
		userInput = userInput.replaceAll(" ", "");
		String assignment = userInput.substring(userInput.indexOf('=') + 1, userInput.length());
		for(int i = 0; i < 12 && !found; i++)
		{
			for(int n = 0; n < 22 && !found; n++)
			{
				String a = alphabet[i] + "";
				a = a + n;
				if(a.equals(assignment))
				{
					found = true;
				}
			}
		}
		return found;
	}
	
	/**
	 * Recalculates the values of the entire spreadsheet (usually after an update).
	 **/
	private static void recalc(Spreadsheet sheet) throws CharNotFoundException
	{
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				sheet.changeSpreadsheetValue(i, j, sheet.getCellVal(i, j));
			}
		}
	}
	
}
