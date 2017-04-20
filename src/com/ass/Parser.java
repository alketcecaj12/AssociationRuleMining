package com.ass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Parser {
	
	public static void main (String [] args)throws Exception {
		
		Map<String, Map<Integer, Integer>>m2 = new HashMap<String, Map<Integer, Integer>>();
		Map<String, Map<Integer, Integer>>m3 = new HashMap<String, Map<Integer, Integer>>();
		String file = "data2";
		
		File fi = new File(file);
		File[] files = fi.listFiles();
		for(int fil = 0; fil < files.length; fil++){
			String filename = files[fil].getName();
			BufferedReader br = new BufferedReader (new FileReader(files[fil]));

			String line; 
			while((line = br.readLine())!= null){
				//System.out.println(line);
				String [] r = line.split("\t");
				String record = r[9].substring(1, r[9].length()-1);
				if(record.contains("10000")) continue;
				String riga = r[0]+"\t"+r[1]+"\t"+r[2]+"\t"+r[3]+"\t"+r[4]+"\t"+r[5]+"\t"+r[6]+"\t"+r[7]+"\t"+r[8];

				Map<Integer, Integer>mi = getVector(record);
				m2.put(riga, mi);

			}br.close();

			for(String s : m2.keySet()){
				Map<Integer, Integer>mi = m2.get(s);
				Map<Integer, Integer>mi2 = get01Records(mi);
                m3.put(s, mi2);
			}
			print("File_"+filename, m3);
		}
	//	print("File.csv", m2);
	
    }
	
	public static Map<Integer, Integer>get01Records(Map<Integer, Integer>m){
		Map<Integer, Integer>m2 = new HashMap<Integer, Integer>();
		double sum = 0;  
//		for(Integer i : m.keySet()){
//			sum = sum + m.get(i);
//		}
//		//System.out.println(sum);
//		double mean = sum / m.size();
		//System.out.println(mean);
		for(Integer i : m.keySet()){
			if(m.get(i) != 0){
				//System.out.println(mean+" VS "+m.get(i));
				m2.put(i, 1);
			}
			else m2.put(i, 0);
		}
		return m2; 
	}
	public static String getNumerosity(String line){
		String num = "";
		Set<String>s = new HashSet<String>();
		String [] r = line.split(", ");
		
		for(int i = 0; i < r.length; i++){
			s.add(r[i]);
		}
		if(s.size() < 35){
			num= "low";
		}
		else num = "high";
		
		return num; 
	}

	public static Map getVector(String line){

		Map<Integer, Integer> m = new TreeMap<Integer, Integer>();

		String [] r = line.split(", ");
		
		for(int i = 1; i < 100; i++){
			m.put(i, 0);
		}
		//System.out.println(m);
		for(int i = 0; i < r.length; i++){
			Integer val_i =0 ;
			try {
			val_i =  Integer.parseInt(r[i]);
			}catch(Exception NumberFormatException){
				continue;
			}
				
			
            Integer val = m.get(val_i);
            
            if(val == 0){
            	m.put(val_i, 1);
            }else m.put(val_i, val+1);
		}
		return m;
	}
	public static void print(String file, Map<String, Map<Integer, Integer>>m)throws Exception{
		PrintWriter out = new PrintWriter(new FileWriter(new File(file)));
		for(String s : m.keySet()){
			int count = 0;
			
			for(Integer i :  m.get(s).keySet()){
				count++;
				
				if(count == m.get(s).size()){
					if(m.get(s).get(i)== 0)
						out.println("?");
					else out.println("1");
				}else {
					if(m.get(s).get(i)== 0)
						out.print("?,");
					else out.print("1,");
				}
			}
		}
		out.close();
	}
}