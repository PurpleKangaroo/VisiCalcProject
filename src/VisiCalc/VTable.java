package VisiCalc;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class VTable extends JTable{
	
	public VTable(AbstractTableModel a)
	{
		super(a);
	}
	
	public void setValueAt(String value, int row, int col)
	{
		getModel().setValueAt(value, row, col);
	}

}
