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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JTextArea;

/**
 * Class meant to parse the content of a folder and rename files according to rules
 * Keeps a log file named history.txt
 * @see Logger 
 */
public class RenameFile {

	private File directory;
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static String date = dateFormat.format(new Date());
	
	public RenameFile(String absolutePath) {
		directory = new File(absolutePath);
	}
	
	/**
	 * Lists all the files from a parent directory and its subdirectories
	 * @param log The Log area from the UI
	 * @return An ArrayList with all files 
	 */
	public ArrayList<String> getFiles(JTextArea log) {
		//the final list of files (names)
		ArrayList<String> files = new ArrayList<>();
		
		if (directory.exists()) {
			//get all subfolders
			File[] directories = directory.listFiles(File::isDirectory);
			
			//get all files in parent folder and add them to the list of names
			File[] fileArray = directory.listFiles((f) -> !f.isDirectory());
			
			for (File file : fileArray) {
				files.add(file.getAbsolutePath());
			}
			//go through all subfolders and add the files
			for (File currentDirectory : directories) {
				File [] directoryFileArray = currentDirectory.listFiles();
				for (File file : directoryFileArray) {
					files.add(file.getAbsolutePath());
				}
			}
		} else {
			log.setForeground(Color.RED);
        	log.append("There is no such directory!\n");
        	throw new IllegalAccessError("There is no such directory!");
		}
		return files;
	}
	
	/**
	 * Changes a String based on several criterias
	 * @param name The current name of the file
	 * @return The new name of the file
	 */
	public String changeName(String name) {
		String path = name.substring(0, name.lastIndexOf('\\'));
		String actualName = name.substring(name.lastIndexOf('\\') + 1);
		String newName = actualName
				.replaceAll("_", " ")
        		.replaceAll("\\((.*?)\\)", "")
        		//added nullification of brackets and their content
        		.replaceAll("\\[[^\\[]*\\]", "")
        		.replaceAll("[0-9]", "")
        		.replaceAll("Various Artists - |Various Artists", "")
        		//added some new keywords
        		.replaceAll("OST|Official Soundtrack|Soundtrack", "")
        		.replaceAll("^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}\\.([a-z]+)?$", "")
        		.trim()
        		.replaceAll(" .mp|.mp",".mp3");
		
		return path + "\\" + newName;
	}
	
	/**
	 * Takes an existing ArrayList of filenames and returns a Map containing pairs<br>
	 * of type: (existing_name, optimized_name)
	 * @param nameList An ArrayList with all files 
	 * @param log The Log area from the UI
	 * @return HashMap containig pairs of filenames as Strings
	 */
	public HashMap<String,String> changeNames(ArrayList<String> nameList, JTextArea log) {
		HashMap<String, String> names = new HashMap<>();
		for (int i = 0; i < nameList.size(); i++) {
			String currentName = nameList.get(i); 
			String newName = changeName(currentName);
			if (newName.equals(currentName)) {
				log.setForeground(Color.BLACK);
            	log.append("Couldn't optimize the name for: " + currentName + "\n");
			} else {
				if (names.containsKey(currentName)) {
					log.setForeground(Color.RED);
					log.append("The file already exists!\n");
				} else {
					names.put(currentName, newName);
				}
			}
		}
		return names;
	}

	/**
	 * Method to build the required components and which further completes the renaming
	 * @param log The Log area from the UI
	 */
	public void rename(JTextArea log) {
		ArrayList<String> files = getFiles(log);
		HashMap<String,String> namePairs = changeNames(files, log);
		
		if (namePairs.size() > 0) {
			Logger fileLog = new Logger();
			log.setForeground(Color.BLACK);
        	log.append(date + "\n");
        	fileLog.write(date);
        	namePairs.forEach((k,v) -> {
        			boolean success = new File(k).renameTo(new File(v));
		            if (success == true) {
		            	log.append(k + " changed to " + v + "\n");
		            }
        	});
		}
	}
}
