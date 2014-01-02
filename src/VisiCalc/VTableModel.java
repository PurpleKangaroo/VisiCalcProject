package VisiCalc;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 * A class of object that is similar to JTable, except that it will be able to have row names on the side.
 * @author Holt Maki
 *
 */
public class VTableModel extends AbstractTableModel {
	private static final String[] columnNames = {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
	private static final String[] rowNames = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"};
	private Cell[][] rawCells;
	private String[][] displayCells;
	private Spreadsheet spreadsheet;
	
	public VTableModel()
	{
		super();
		spreadsheet = new Spreadsheet();
		
		rawCells = spreadsheet.getCells();
		
		displayCells = new String[12][22];
		getDisplayCells();
		//TODO addTableModelListner();
	}
	
	private void getDisplayCells()
	{
		for(int i = 0; i<22; i++)
		{
			for(int n = 0; n<12; n++)
			{
				displayCells[n][i] = rawCells[n][i].getValue().getDisplayStr();
			}
		}
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return rowNames.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		if(col<1)
		{
			return rowNames[row];
		}
		else
		{
			return displayCells[col-1][row];
		}
	}
	
	public boolean isCellEditable(int row, int col)
	{
		if (col<1)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public void setValueAt(String value, int row, int col)
	{
		rawCells[col][row] = new Cell(value);
		fireTableCellUpdated(row, col);
	}

}
