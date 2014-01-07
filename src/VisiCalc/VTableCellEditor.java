package VisiCalc;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class VTableCellEditor extends DefaultCellEditor
{
	private JTextField textField;
	
	public VTableCellEditor(JTextField textfield) {
		super(textfield);
		textField = textfield;
	}
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col)
	{
		textField = (JTextField) super.getTableCellEditorComponent(table, value, isSelected, row, col);
		textField.setText((String) value);
		return textField;
	}
	
	public Object getCellEditorValue()
	{
		textField = (JTextField)getComponent();
		String str = textField.getText();
		return str;
	}
	
	public boolean stopCellEditing()
	{
		textField = (JTextField)getComponent();
		
	}

}
