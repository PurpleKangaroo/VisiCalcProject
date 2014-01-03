package VisiCalc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
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
		//TODO MAKE AN AUTORESIZE OPTION FOR THE USER
		
		JScrollPane scroll = new JScrollPane(table);
		add(scroll, BorderLayout.CENTER);
		
		userInputField = new VTextField();
		userInputField.setSize(new Dimension(500, 20));
		add(userInputField, BorderLayout.SOUTH);
		
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

