/**
 * @author Seraphyno
 * @since 02.02.2018 
 * @version 1.0
 */
package fileProcessor;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class meant to parse the content of a folder and rename files according to rules
 * Keeps a log file named history.txt
 * @see Logger 
 */
public class RenameFile {

	private static String absolutePath = "C:\\Users\\Sokol\\Desktop\\test";
	private static File directory = new File(absolutePath);
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static Date date = new Date();
	
	public static void main(String[] args) {
        if (directory.exists()) {
	        File[] filesInDirectory = directory.listFiles();
	        if (filesInDirectory.length > 0) {
	        	Logger logger = new Logger();
	        	logger.write(dateFormat.format(date));
		        for(File file:filesInDirectory) {
		            String name = file.getName();
		            String newName = name.replaceAll("_", " ")
		            		.replaceAll("\\((.*?)\\)", "")
		            		.replaceAll("[0-9]", "")
		            		.replaceAll("Various Artists - |Various Artists", "")
		            		.trim()
		            		.replaceAll(" .mp|.mp",".mp3");
		            logger.write(name + " should be changed to: " + newName);
		            if (name.equals(newName)) {
		            	System.out.println("Couldn't optimize the name!");
		            } else {
			            String newPath = absolutePath + "\\" + newName;
			            file.renameTo(new File(newPath));
			            System.out.println(name + " changed to " + newName);
		            }
		        }
	        } else {
	        	throw new IllegalArgumentException("The directory is empty!");
	        }
        } else {
        	throw new IllegalAccessError("There is no such directory!");
        }

	}
}
