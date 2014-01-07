package VisiCalc;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class VTableCellEditor extends DefaultCellEditor
{
	private JTextField textField;
	
	public VTableCellEditor(JTextField textfield) {
		super(textfield);
		textField = textfield;
	}
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int )
	
	

}
