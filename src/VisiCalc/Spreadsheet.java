package VisiCalc;

/**
 * 
 * @author Devon Grove
 * @author Holt Maki
 *
 */
public class Spreadsheet {
	private String[][] cells;
	
	public Spreadsheet() 
	{
		cells = new String[12][22];
		
		for (int i = 0; i < 12; i++) 
		{
			for (int j = 0; j < 22; j++) 
			{
				cells[i][j] = "0";
			}
			
		}
	}
	
	public void printSpreadsheet() 
	{
		for (int i = 0; i < 12; i++) 
		{
			for (int j = 0; j < 22; j++) 
			{
				System.out.print(cells[i][j] + "\t");
			}
			
			System.out.println();
		}
	}
	
	public String getCell(int column, int row)
	{
		return cells[column][row];
	}
	
	public static void main(String[] args) 
	{
		Spreadsheet test = new Spreadsheet();
		test.printSpreadsheet();
	}
}
