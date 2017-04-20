package com.ass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Count {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
        Map<String, Integer>m = new HashMap<String, Integer>();
        
		BufferedReader br = new BufferedReader (new FileReader(new File("datainarff.arff")));

		String line; 
		while((line = br.readLine())!= null){
			if(line.startsWith("?")){
			String [] r = line.split(",");
			System.out.println(r.length); 
			for(int i = 0; i < r.length; i++){
				String s = r[i];
				Integer si = m.get(s);
				if(si == null){
					m.put(s, 1);
				}else m.put(s, si+1);
			
			}
			break;
			}
		}br.close();
		System.out.println(m);
		
	}

}
