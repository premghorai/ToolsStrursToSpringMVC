package com.file.readWrite;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class FileMultiLine {

	public static void main(String[] args) {
		// Declaring reference of File class
		File file = null;
		// Declaring reference of FileWriter class
		FileWriter filewriter = null;
		String data = "import javax.servlet.http.HttpServletRequest";
		String data1 = "import javax.servlet.http.HttpServletResponse;";

		List<String> li = new ArrayList<String>();
		li.add("prem");
		li.add("prem1");
		try {
			file = new File("D://fileread//text//xxd.txt");
			// Creating Object of FileWriter class
			filewriter = new FileWriter(file);
			// Writing to the file
			filewriter.write(data);
			filewriter.write("\r");
			filewriter.write(data1);
			// Closing the stream
			filewriter.close();
			System.out.println("File writing done.");
		}
		// Handing Exception
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (filewriter != null) {
					filewriter.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
