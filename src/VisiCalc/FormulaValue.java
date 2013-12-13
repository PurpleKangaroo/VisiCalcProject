package VisiCalc;

public class FormulaValue extends Value{
	private String formula;
	
	public FormulaValue(String valueString)
	{
		super(valueString);
		
	}
	
	private void parseFormula()
	{
		String input = getFormattedCellString(getInputStr());
		
	}
	
	private String getFormattedCellString(String Str)
	{
		for (int n=0; n<12; n++)
		{
			char[] chars = {'0', '1', '2', '3', '4','5','6', '7','8','9',')','('};
			String i = chars[n] + "";
			String subtractBefore = i + "-";
			String subtractAfter = "-" + i;
			String devideBefore = " /" + i;
			String exponentBefore = " ^"+ i;
			
			Str = Str.replaceAll(i + "\\+", (i + " +"));
			Str = Str.replaceAll("\\+" + i, "+ " + i);
			Str = Str.replaceAll(i + "\\*", i + " *");
			Str = Str.replaceAll("\\*" + i, "* " + i);
			Str = Str.replaceAll(subtractBefore, i + " -");
			Str = Str.replaceAll("\\^" + i, "^ " + i);//TODO If there are problems, remove the \\
			Str = Str.replaceAll(i + "\\^", i + " ^");//TODO If there are problems, remove the \\
			
			while (Str.toLowerCase().contains(subtractAfter) && Str.indexOf(subtractAfter) >0 && Str.substring(Str.indexOf(subtractAfter)-1, Str.indexOf(subtractAfter)).contains(" ") == false)
			{
				Str = Str.replace(subtractAfter,"- " + i);
			}
			while (Str.toLowerCase().contains(devideBefore) )
			{
				Str = Str.replace(devideBefore," / " + i);
			}
			
			
			if (Str.toLowerCase().contains("+-"))
			{
				Str = Str.replaceAll("\\+-", "+ -");
			}
			if (Str.toLowerCase().contains("--"))
			{
				Str = Str.replaceAll("--", "- -");
			}
			if (Str.toLowerCase().contains("*-"))
			{
				Str = Str.replaceAll("\\*-", "* -");
			}
			if (Str.toLowerCase().contains("^-"))
			{
				Str = Str.replaceAll("\\*-", "* -");
			}
			
		}
		return Str;
	}
	
	private void setValue()
	{
		
	}
	
	private void sum()
	{
		
	}
	
	private void avg()
	{
		
	}
	
	private void add(int first, int second)
	{
		
	}
}
