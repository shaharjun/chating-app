package com.gc.chatapp.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProfilePicture {
	
	public static String getBase64(String emailId) throws IOException{
		String profilePicture = "";
		File file = new File("src/main/webapp/resources/profilepicture/"+emailId+".txt");
		if(file.exists() == false)
			file = new File("src/main/webapp/resources/profilepicture/default.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		while((st=br.readLine()) != null)
			profilePicture += st;
		br.close();
		return profilePicture;
	}
	
	
	public static String storeBase64(String emailId, String base64) throws IOException{
		String fileName = "src/main/webapp/resources/profilepicture/"+emailId+".txt";
		File file = new File(fileName);
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(base64);
		bw.close();
		
		return  fileName;
	}
	
}
