package com.ass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteData {
	
	public static void main (String [] args) throws Exception{
		
		String file = "ateco.csv";
		List<String>li = new ArrayList<String>();
		Map<String, String>map = new HashMap<String, String>();
		
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line; 
		while((line = br.readLine())!= null){
			String [] r = line.split(";");
//			@relation supermarket
//			@attribute 'department1' { t}
//			@attribute 'department2' { t}
			String act = r[1].trim().replace("\"", "");
			String rec = "@attribute '"+act+"' { t}";
			li.add(rec);
		}br.close();
		
		print("datainarff.arff", li);
	}
	
	public static void print(String file, List<String>list)throws Exception{
		PrintWriter out = new PrintWriter(new FileWriter(new File(file)));
		for(int i = 0; i < list.size(); i++){
			out.println(list.get(i));
		}
		out.close();
	}

}
