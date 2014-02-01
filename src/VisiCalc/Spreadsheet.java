package VisiCalc;

/**
 * 
 * @author Devon Grove
 * @author Holt Maki
 *
*/
public class Spreadsheet {
	private String fileName;
	private Cell[][] cells; 
	private static final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K','L'};
	
	public Spreadsheet() {
		cells = new Cell[12][22];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				cells[i][j] = new Cell("0");
				//FIXME: make it so that the cell is initialized as a number, but fix number test in cell first
			}
		}
	}
	
	/**
	 * Getter for the maximum length of a cell so the spreadsheet knows how wide to make everything.
	 * @return the number of characters in the cell which has the greatest length.
	 */
	private int getMaxLength()
	{
		int a = 0;
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				if (cells[i][j].getCellString().length() > a)
				{
					a = cells[i][j].getCellString().length();
				}
			}
		}
		return a;
	}
	
	/**
	 * Prints out the spreadsheet.
	 */
	public void printSpreadsheet() {
		int maxLength = getMaxLength() + 1; /** need extra space at end **/
		maxLength = maxLength > 3 ? maxLength : 3;
		String s;
		
		for (int i = 0; i < 13; i++){
			for (int j = 0; j < 23; j++) {
				if ((i == 0) && (j == 0)) {
					System.out.print("  ");
				}
				else if (i == 0) {
					System.out.print(padRight(String.format("%2d", j), maxLength - 1));
				}
				else if (j == 0) {
					s = (char)('A' + i - 1) + "| ";
					System.out.print(s);
				}
				else {
					s = cells[i-1][j-1].getCellString();
					System.out.print(padRight(s, maxLength - s.length()));
				}
					
			}
			System.out.println();
		}
	}

	public String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}
	
	public void changeSpreadsheetValue(int row, int column, String newValue) {
		cells[column][row] = new Cell(newValue);
	}
	
	/**
	 * Getter for value of the specified cell (as a string).
	 * @param col - the column of the cell to return the value of.
	 * @param row - the row of the cell to return the value of.
	 * @return the value of the declared cell as a string.
	 */
	public String getCellVal(int col, int row)
	{
		String a = cells[col][row].getCellString();
		return a;
	}
	
	/**
	 * Returns all the cells in the spreadsheet as an array.
	 * @return the array of cells.
	 */
	protected Cell[][] getCells()
	{
		return cells;
	}
}
