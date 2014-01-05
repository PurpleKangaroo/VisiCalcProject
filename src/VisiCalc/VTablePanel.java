package VisiCalc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
	
	public VTablePanel()
	{
		super(new BorderLayout());
		
		JTable table = new JTable(new VTableModel());
		
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
		
		JMenuBar menu = new JMenuBar();
		
		JMenu file = new JMenu("File");
		JMenu options = new JMenu("Options");
		JMenu help = new JMenu("Help");
		
		JMenuItem exit = new JMenuItem("Exit", MouseEvent.MOUSE_CLICKED);
		exit.addActionListener(new ExitListener());
		
		JMenuItem open = new JMenuItem("Open", MouseEvent.MOUSE_CLICKED);
		open.addActionListener(new OpenListener());
		
		JMenuItem save = new JMenuItem("Save", MouseEvent.MOUSE_CLICKED);
		//FIXME: add a listener that checks if the spreadsheet has a file name and then 
		
		JMenuItem saveAs = new JMenuItem("Save As...", MouseEvent.MOUSE_CLICKED);
		//FIXME: Have this actually do something when clicked
		
		file.add(exit);
		file.add(open);
		file.add(save);
		file.add(saveAs);
		
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
					File loadFile = saver.getSelectedFile();
					
				}
				
			}
			
			catch(Exception e)
			{
				//NOTHING
			}
			
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

