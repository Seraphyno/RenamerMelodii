/**
 * @author Seraphyno
 * @since 02.02.2018
 * @version 1.0
 */
package fileProcessor;

import java.io.*;

/**
 * Logger class meant to process the history file<br>
 * Holds data in a history file located in the program's source folder
 */
public class Logger {

	/**
	 * Method meant to create a writer to the file, write a new line, then closes the file
	 * @param text the input string meant to be written as a new line in the file
	 * @exception IOException
	 */
	public void write(String text) {
		
		try(FileWriter fw = new FileWriter("history.txt", true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw)) 
		{
		    out.println(text);
		    out.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
