/**
 * @author Seraphyno
 * @since 02.02.2018 
 * @version 1.0
 */
package main.java.fileProcessor;

import java.awt.Color;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

/**
 * Class meant to parse the content of a folder and rename files according to rules
 * Keeps a log file named history.txt
 * @see Logger 
 */
public class RenameFile {

	private String absolutePath;
	private File directory;
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static Date date = new Date();
	
	public RenameFile(String absolutePath) {
		this.absolutePath = absolutePath;
		directory = new File(absolutePath);
	}


	public void rename(JTextArea log) {
        if (directory.exists()) {
	        File[] filesInDirectory = directory.listFiles();
	        if (filesInDirectory.length > 0) {
	        	log.setForeground(Color.BLACK);
	        	log.append(dateFormat.format(date) + "\n");
		        for(File file:filesInDirectory) {
		            String name = file.getName();
		            String newName = name.replaceAll("_", " ")
		            		.replaceAll("\\((.*?)\\)", "")
		            		.replaceAll("[0-9]", "")
		            		.replaceAll("Various Artists - |Various Artists", "")
		            		.trim()
		            		.replaceAll(" .mp|.mp",".mp3");
		            if (name.equals(newName)) {
		            	log.setForeground(Color.BLACK);
		            	log.append("Couldn't optimize the name for: " + name + "\n");
		            } else {
			            String newPath = this.absolutePath + "\\" + newName;
			            System.out.println(newPath);
			            boolean success = file.renameTo(new File(newPath));
			            if (success == true) {
			            	log.append(name + " changed to " + newName + "\n");
			            }
		            }
		        }
	        } else {
	        	log.setForeground(Color.RED);
	        	log.append("The directory is empty!\n");
	        	throw new IllegalArgumentException("The directory is empty!");
	        }
        } else {
        	log.setForeground(Color.RED);
        	log.append("There is no such directory!\n");
        	throw new IllegalAccessError("There is no such directory!");
        }

	}
}
