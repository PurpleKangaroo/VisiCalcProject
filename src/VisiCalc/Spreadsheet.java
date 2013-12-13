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
	String[][] cells = new String[12][22]; 
	private static final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K','L'};
	//TODO GET THIS THING TO RECOGNIZE A USER SETTING THE CELL VALUES BY TYPING A1 = blahblahblah or 34
	
	public Spreadsheet() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				cells[i][j] = "0";
			}
		}
	}
	
	public void printSpreadsheet() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				System.out.print(cells[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void changeSpreadsheetValue(int row, int column, String newValue) {
		cells[row][column] = newValue;
	}
	
}
