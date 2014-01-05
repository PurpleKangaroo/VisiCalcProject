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
				cells[i][j] = new Cell("\"0\"");
				//FIXME: make it so that the cell is initialized as a number, but fix number test in cell first
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
	
	public void save(String filename) throws FileNotFoundException, URISyntaxException
	{
		fileName = filename;
		File saveFile = new File((new PathFinder()).getVisiCalc_Path(filename+".txt"));
		PrintWriter out = new PrintWriter(saveFile);
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				out.println(cells[i][j].getValue().getInputStr());
			}
		}
	}
	
	public void save() throws URISyntaxException, FileNotFoundException
	{
		File saveFile = new File((new PathFinder()).getVisiCalc_Path(fileName+".txt"));
		PrintWriter out = new PrintWriter(saveFile);
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				out.println(cells[i][j].getValue().getInputStr());
			}
		}
		//FIXME add a try catch for this elswhere in the source code in case of the spreadsheet not having a filename.
	}
	
	public void load(String filename)
	{
		fileName = filename;
		//FIXME fill
	}
	
}
