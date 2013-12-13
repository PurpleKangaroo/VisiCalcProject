package VisiCalc;

/**
 * 
 * @author Devon Grove
 * @author Holt Maki
 *
 */
/*
public class Spreadsheet {
	private Cell[][] cells;
	
	public Spreadsheet()
	{
		cells = new Cell[22][12];
		
		for (int i = 0; i < 22; i++) 
		{
			for (int j = 0; j < 12; j++) 
			{
				cells[i][j] = new Cell("0");
			}
			
		}
	}
	
	public void printSpreadsheet() 
	{
		for (int i = 0; i < 22; i++) 
		{
			for (int j = 0; j < 12; j++) 
			{
				System.out.print(cells[i][j] + "\t");
			}
			
			System.out.println();
		}
	}
	
	public Cell getCell(int column, int row)
	{
		return cells[column][row];
	}
	
	public String getCellValueString()
	{
		return "THIS METHOD NOT COMPLETE";
		//TODO complete getCellValueString method
	}
	
}
*/
public class Spreadsheet {
<<<<<<< HEAD
	private String[][] cells = new String[12][22]; 
=======
	String[][] cells = new String[12][22]; 
>>>>>>> 1aaa8e516523e73a23d1148c2cda45b1b5240429
	private static final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K','L'};
	//TODO GET THIS THING TO RECOGNIZE A USER SETTING THE CELL VALUES BY TYPING A1 = blahblahblah or 34
	
	public Spreadsheet() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				cells[i][j] = "0";
			}
		}
	}
	
<<<<<<< HEAD
	private int getMaxLength()
	{
		int a = 0;
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				if (cells[i][j].length() > a)
				{
					a = cells[i][j].length();
				}
			}
		}
		return a;
	}
	
	public void printSpreadsheet() {
		
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				String a = cells[i][j];
				for (int b = getMaxLength();a.length()<b;)//work on formatitng the spreadshert
				{
					a = a +" ";
				}
				System.out.print(a + " ");
			}
=======
	public void printSpreadsheet() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				System.out.print(cells[i][j] + " ");
			}
			System.out.println();
>>>>>>> 1aaa8e516523e73a23d1148c2cda45b1b5240429
		}
	}
	
	public void changeSpreadsheetValue(int row, int column, String newValue) {
<<<<<<< HEAD
		cells[column][row] = newValue;
	}
	
	public String getCellVal(int col, int row)
	{
		String a = cells[col][row];
		return a;
	}

	
}
=======
		cells[row][column] = newValue;
	}
	
}
>>>>>>> 1aaa8e516523e73a23d1148c2cda45b1b5240429
