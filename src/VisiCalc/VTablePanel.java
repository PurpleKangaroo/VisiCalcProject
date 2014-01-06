package VisiCalc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URISyntaxException;
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

//TODO:REMEMBER COLORED CELLS
//TODO:L Find a way to display row names
public class VTablePanel extends JPanel
{
	protected VTextField userInputField;
	private JTable table;
	
	public VTablePanel()
	{
		super(new BorderLayout());
		
		table = new JTable(new VTableModel());
		
		table.setPreferredScrollableViewportSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);
		table.setSelectionBackground(new Color(255, 250, 205));
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		table.add
		//TODO Prevent columns from moving
		//TODO MAKE AN AUTORESIZE OPTION FOR THE USER
		
		JScrollPane scroll = new JScrollPane(table);
		
		add(scroll, BorderLayout.CENTER);
		
		userInputField = new VTextField();
		userInputField.setSize(new Dimension(500, 20));
		
		add(userInputField, BorderLayout.SOUTH);
		
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

