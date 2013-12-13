package VisiCalc;

/**
 * 
 * @author Devon Grove
 * @author Holt Maki
 *
*/

public class Spreadsheet {
	private String[][] cells = new String[12][22]; 
	private static final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K','L'};
	//TODO GET THIS THING TO RECOGNIZE A USER SETTING THE CELL VALUES BY TYPING A1 = blahblahblah or 34
	
	public Spreadsheet() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				cells[i][j] = "0";
			}
		}
	}
	
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
			System.out.println();
		}
	}

	public void changeSpreadsheetValue(int row, int column, String newValue) {
		cells[column][row] = newValue;
	}
	
	public String getCellVal(int col, int row)
	{
		String a = cells[col][row];
		return a;
	}

	
}
