package VisiCalc;

/**
 * 
 * @author Devon Grove
 * @author Holt Maki
 *
*/
//TODO NEEDS SERIOUS FIXING. RIGHT NOW IT STORES EVERYTHING AS STRING, NOT CELL
public class Spreadsheet {
	private Cell[][] cells; 
	private static final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K','L'};
	
	public Spreadsheet() {
		cells = new Cell[12][22];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				cells[i][j] = new Cell("\"0\"");
			}
		}
	}
	
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
	
	public void printSpreadsheet() {
		
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				String a = cells[i][j].getCellString();
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
		cells[column][row] = new Cell(newValue);
	}
	
	public String getCellVal(int col, int row)
	{
		String a = cells[col][row].getCellString();
		return a;
	}
	
	protected Cell[][] getCells()
	{
		return cells;
	}
	
}
