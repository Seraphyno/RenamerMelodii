package fileProcessor;

import java.io.*;

public class Logger {

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
