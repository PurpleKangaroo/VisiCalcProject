package VisiCalc;
import java.util.Scanner;

public class SpreadsheetGen {
	private static final String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K","L"};
	
	public static void main(String[] args)
	{
		Spreadsheet test = new Spreadsheet();
		test.printSpreadsheet();
		Scanner sc = new Scanner(System.in);
		String userInput = sc.nextLine();
		while (!userInput.equalsIgnoreCase("quit"))
		{
			try
			{
				userInput = whiteSpace(userInput);
				//Case 1: CLEAR COMMAND
				if (userInput.toLowerCase().contains("clear"))
				{
					test = clear(userInput, test);
				}
				//Case 1: CONCAT COMMAND
				else if (userInput.toLowerCase().contains("concat"))
				{
					test = concat(userInput, test);
				}
				else if (userInput.toLowerCase().contains("avg") || userInput.toLowerCase().contains("average"))
				{
					//test = avg(userInput, test);
				}
				else if (userInput.toLowerCase().contains("sum"))
				{
					//test = sum(userInput, test);
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
			
			catch(CharNotFoundException e)
			{
				System.out.print("Invalid input: Cell does not exist\n");
				
			}
			
		}
		sc.close();
		System.out.println("You have decided to quit.");
	}
	
	private static Spreadsheet concat(String userInput, Spreadsheet test) throws CharNotFoundException {
		if (expContainsCellRef(userInput))
		{
			String cellName = userInput.substring(0, userInput.indexOf('='));
			userInput = userInput.substring(userInput.indexOf("concat")+6);
			int row = findCellRow(cellName);
			int col = findCellCol(cellName);
			String assignment = new String();
			while(userInput.contains(","))
			{
				String sub = new String();
				int next = userInput.indexOf(",");
				if(next >0)
				{
					sub = userInput.substring(0 , sub.indexOf(","));
				}
				int nextquote = sub.indexOf("\"");
				if(nextquote > 0)
				{
					sub = sub.substring(nextquote);
					sub.replace("\"", "");
					nextquote = sub.indexOf("\"");
					if(nextquote > 0)
					{
						assignment = assignment + sub.substring(0,sub.indexOf("\""));
					}
					userInput = userInput.substring(sub.length() + 2);
				}
				else
				{
					//TODO: This is the case where the assignment adds a cells contents
				}
			}
			
			test = modifyCell(row, col, test, assignment);
		}
		
		return test;
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
			if (alphabet[i].charAt(0) == letter)
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
		if (expContainsCellRef(userInput))
		{
			String cellName = userInput.substring(5, userInput.length());
			int row = findCellRow(cellName);
			int col = findCellCol(cellName);
			sheet = modifyCell(row, col, sheet, "0");
		}
		else
		{
			for (int i = 0; i < 12; i++)
			{
				for (int j = 0; j < 22; j++)
				{
					sheet = modifyCell(i, j, sheet, "0");
				}
			}
			
		}
		
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
		int stringLength = a.length();
		for(int i = 0; i < stringLength; i++)
		{
			if(a.charAt(i) == ' ')
			{
				String b = a.substring(0,i);
				String c = a.substring(i+1, a.length());
				a = b+c;
				i--;
			}
			stringLength = a.length();
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
		userInput = userInput.substring(userInput.indexOf('=') + 1, userInput.length());
		userInput = userInput.toUpperCase().replaceAll("CLEAR", "");
		for(int i = 0; i < 12 && !found; i++)
		{
			for(int n = 0; n < 22 && !found; n++)
			{
				String a = alphabet[i] + "";
				a = a + n;
				if(a.equalsIgnoreCase(userInput.replaceAll(" ", "")))
				{
					found = true;
				}
			}
		}
		return found;
	}
	
	/* CURRENTLY JUST A COPY OF THE CLEAR METHOD. NEED TO IMPLEMENT
	 private static Spreadsheet avg(String userInput, Spreadsheet sheet) throws CharNotFoundException
	 
	{
		if (expContainsCellRef(userInput))
		{
			String cellName = userInput.substring(5, userInput.length());
			int row = findCellRow(cellName);
			int col = findCellCol(cellName);
			sheet = modifyCell(row, col, sheet, "0");
		}
		else
		{
			for (int i = 0; i < 12; i++)
			{
				for (int j = 0; j < 22; j++)
				{
					sheet = modifyCell(i, j, sheet, "0");
				}
			}
			
		}
		
		return sheet;
	}
	*/
	
	/**
	 * Recalculates the values of the entire spreadsheet (usually after an update).
	 **/
	/*
	private static void recalc(Spreadsheet sheet) throws CharNotFoundException
	{
		for (int i = 0; i < 12; i++)
		{
			for (int j = 0; j < 22; j++)
			{
				//sheet.changeSpreadsheetValue(i, j, sheet.getCellVal(i, j));
				
			}
		}
	}
	*/
}
