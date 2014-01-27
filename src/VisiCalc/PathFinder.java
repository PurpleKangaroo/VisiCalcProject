package VisiCalc;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * A class of object that finds the paths of files.
 * @author Holt Maki
 */
public class PathFinder {
	private String VisiCalc_Path;
	
	/**
	 * Creates an object that finds the paths of files.
	 * @since CAH1.0
	 * @throws URISyntaxException
	 */
	public PathFinder() throws URISyntaxException
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL url = classLoader.getResource(".");
		File file = new File(url.toString());
		VisiCalc_Path = file.getAbsolutePath();
		int index = VisiCalc_Path.indexOf("%");
		VisiCalc_Path = VisiCalc_Path.substring(0,index);
		VisiCalc_Path = VisiCalc_Path.substring(0, VisiCalc_Path.lastIndexOf("VisiCalc"));
	}

	/**
	 * Gets the path of the pathfinder.
	 * @return VisiCalc_Path - the path of the pathfinder.
	 */
	public String getVisiCalc_Path()
	{
		return VisiCalc_Path;
	}
	
	/**
	 * Gets the path of another cards against humanity file.
	 * @param fileString - the file that is trying to be found's directory on the origninal.
	 * @return - the path of the file trying to be found
	 */
	public String getVisiCalc_Path(String fileString)
	{
		String pathStr = VisiCalc_Path + "";
		fileString = fileString.substring(fileString.indexOf("/VisiCalc")-1 + "/VisiCalc".length()+1);
		pathStr = pathStr + "/VisiCalc" + fileString;
		return pathStr;
	}
	
}
