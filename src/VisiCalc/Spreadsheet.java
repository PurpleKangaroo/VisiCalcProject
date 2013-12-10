package VisiCalc;

/**
 * 
 * @author Devon Grove
 * @author Holt Maki
 *
 */
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
	}
	
}
