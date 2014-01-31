package VisiCalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

/**
 * 
 * @author Devon Grove
 * @author Holt Maki
 *
*/
//TODO NEEDS SERIOUS FIXING. RIGHT NOW IT STORES EVERYTHING AS STRING, NOT CELL
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
		for (int i = 0; i < 22; i++)
		{
			System.out.print(i + 1);
		}
		System.out.println();
		for (int i = 0; i < 12; i++)
		{
			System.out.print(alphabet[i] + " |  ");
			for (int j = 0; j < 22; j++) {
				String a = cells[i][j].getCellString();
				for (int b = getMaxLength();a.length()<b;)
				{
					a = a +" ";
				}
				System.out.print(a + " ");
			}
			System.out.println();
		}
	}

	public void changeSpreadsheetValue(int row, int column, String newValue) {
		cells[column][row] = new Cell(newValue);
	}
	
	/**
	 * Getter for the maximum length of a cell so the spreadsheet knows how wide to make everything.
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
