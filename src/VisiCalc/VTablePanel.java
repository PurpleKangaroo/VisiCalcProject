package VisiCalc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URISyntaxException;
import java.util.EventObject;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellEditor;

//TODO:REMEMBER COLORED CELLS
//TODO:L Find a way to display row names
public class VTablePanel extends JPanel
{
	private static final String[] columnNames = {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
	private static final String[] rowNames = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"};
	protected VTextField userInputField;
	private VTable table;
	
	public VTablePanel()
	{
		super(new BorderLayout());
		
		table = new VTable(new VTableModel());
		table.setPreferredScrollableViewportSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);
		table.setSelectionBackground(new Color(255, 250, 205));
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		
		//TODO Prevent columns from moving
		//TODO MAKE AN AUTORESIZE OPTION FOR THE USER
		
		JScrollPane scroll = new JScrollPane(table);
		
		add(scroll, BorderLayout.CENTER);
		
		userInputField = new VTextField();
		userInputField.setSize(new Dimension(500, 20));
		
		add(userInputField, BorderLayout.SOUTH);
		
		table.setCellEditor(new TableCellEditor(){

			@Override
			public void addCellEditorListener(CellEditorListener arg0) {
				class CellEditorListen implements CellEditorListener{

					@Override
					public void editingCanceled(ChangeEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void editingStopped(ChangeEvent arg0) {
						stopCellEditing();
						
					}
					
				}
				
			}

			@Override
			public void cancelCellEditing() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public Object getCellEditorValue() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isCellEditable(EventObject arg0) {
				int col = table.getSelectedColumn();
				if (col<1)
				{
					return false;
				}
				else
				{
					return true;
				}
			}

			@Override
			public void removeCellEditorListener(CellEditorListener arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean shouldSelectCell(EventObject arg0) {
				int col = table.getSelectedColumn();
				if (col<1)
				{
					return false;
				}
				else
				{
					return true;
				}
			}

			@Override
			public boolean stopCellEditing() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Component getTableCellEditorComponent(JTable arg0,
					Object arg1, boolean arg2, int arg3, int arg4) {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{
				String text = userInputField.getText();
				while(text.contains(" "))
				{
					text.replaceAll(" ", "");
				}
				int row = table.getSelectedRow();
				if(row<0)
				{
					for (int i = 0; i < 12; i++) 
					{
						for (int j = 0; j < 22; j++) 
						{
							String cellName = columnNames[i] + rowNames[j];
							String text1 = text.substring(0,cellName.length() + 1).toUpperCase();
							if(text.equalsIgnoreCase(cellName + "="))
							{
								table.clearSelection();
								
							}
						}
					}
					
				}
				//FIXME
			}
		});
		
		JMenuBar menu = new JMenuBar();
		
		JMenu file = new JMenu("File");
		JMenu options = new JMenu("Options");
		JMenu help = new JMenu("Help");
		
		JMenuItem exit = new JMenuItem("Exit", MouseEvent.MOUSE_CLICKED);
		exit.addActionListener(new ExitListener());
		
		JMenuItem open = new JMenuItem("Open", MouseEvent.MOUSE_CLICKED);
		open.addActionListener(new OpenListener());
		
		JMenuItem save = new JMenuItem("Save", MouseEvent.MOUSE_CLICKED);
		save.addActionListener(new SaveListener());
		//FIXME: add a listener that checks if the spreadsheet has a file name and then 
		
		JMenuItem saveAs = new JMenuItem("Save As...", MouseEvent.MOUSE_CLICKED);
		saveAs.addActionListener(new SaveAsListener());
		
		file.add(open);
		file.add(save);
		file.add(saveAs);
		file.add(exit);
		
		menu.add(file);
		menu.add(options);
		menu.add(help);
				
		add(menu, BorderLayout.NORTH);
	}
	
	private class ExitListener implements ActionListener 
	{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
	}
	
	private class OpenListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {//FIXME: MAKE THIS WORK - 1.Pathfinder so that it starts at the visicalc saves folder.
			try//FIXME: 2. Make it actually open files
			{
				System.out.println(new PathFinder().getVisiCalc_Path("VisiCalc Saves"));
				JFileChooser opener = new JFileChooser(new File((new PathFinder()).getVisiCalc_Path("VisiCalc Saves")));
				Scanner in = null;
				if(opener.showOpenDialog(null)== JFileChooser.APPROVE_OPTION)
				{
					File loadFile = opener.getSelectedFile();
					
				}
			}
			catch(Exception E)
			{
				//NOTHING
			}
			
			
		}
		
	}
	
	private class CtrlSListener //implements (Sometype of listener)
	{
		//FIXME fill
	}

	private class SaveAsListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			//FIXME: MAKE THIS WORK - 1.Pathfinder so that it starts at the visicalc saves folder.
			try//FIXME: 2. Make it actually open files
			{
				System.out.println(new PathFinder().getVisiCalc_Path("VisiCalc Saves"));
				JFileChooser saver = new JFileChooser(new File((new PathFinder()).getVisiCalc_Path("VisiCalc Saves")));
				Scanner in = null;
				if(saver.showSaveDialog(null)== JFileChooser.APPROVE_OPTION)
				{
					File saveFile = saver.getSelectedFile();
					
				}
				
			}
			
			catch(Exception e)
			{
				//NOTHING
			}
			
		}
	}
	
	private class SaveListener implements ActionListener
	{
		//FIXME - fill

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private void cellName()//FIXME use this to make the text field show the selected cell. But make sure the user can still type.
	{
		try
		{
		String[] columnNames = {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};		
		String cellName = columnNames[table.getSelectedColumn()-1] + (table.getSelectedRow()-1);
		String display = cellName + "  = ";
		userInputField.setText(display);
		}
		catch(Exception e)
		{
			
		}
	}
	
	private static void create()
	{
		JFrame frame = new JFrame("VisiCalc");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VTablePanel panel = new VTablePanel();
		panel.setOpaque(true);
		frame.setContentPane(panel);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){create();}
		});
	}
}

