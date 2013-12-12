package VisiCalc;
import java.util.Scanner;

public class SpreadsheetGen {
	//This is a temporary way of printing the spreadsheet while the VFrame is still being implemented.
	//TODO does not work
	public static void main(String[] args)
	{
		Spreadsheet test = new Spreadsheet();
		test.printSpreadsheet();
		Scanner sc = new Scanner(System.in);
		String userInput = sc.nextLine();
		while (!userInput.equalsIgnoreCase("quit"))
		{
			test.printSpreadsheet();
			userInput = sc.nextLine();
		}
		sc.close();
		System.out.println("You have decided to quit.");
	}
}
