package VisiCalc;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

public class VFrame extends JFrame
{
	private VTable table;
	private Spreadsheet values;
	
	public VFrame()
	{
		values = new Spreadsheet();
		
	}
	
	private static class VTable extends JTable
	{
		
	}

}
