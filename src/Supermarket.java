/**
 * Chapter 5: Market basket analysis 
 * http://kdd.org/kdd-cup/view/kdd-cup-2009
 * 
 * @author Bostjan Kaluza, http://bostjankaluza.net
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import weka.associations.AssociationRule;
import weka.associations.AssociationRules;
import weka.associations.FPGrowth;
import weka.core.Instances;


public class Supermarket {

public static void main(String args[]) throws Exception {
		// load data
	
	File f = new File("E:\\aziende3\\high");
	File [] files = f.listFiles();
	
	for(int i = 0; i < files.length; i++){
		
		String file_i = files[i].getName();
		if(file_i.equals("Inter9_Milano_5000.0.csv") || file_i.equals("Inter_99_Milano_5000.0.csv")
				|| file_i.equals("Inter_33_Milano_5000.0.csv")
				|| file_i.equals("Inter_33_Roma_5000.0.csv")|| file_i.equals("Intra_33_Milano_7000.0.csv")|| file_i.equals("Inter_99_Milano_7000.0.csv")) continue;
		//System.out.println("----------- FPGrowth ----------"+files[i].getName());
		Instances data = new Instances(new BufferedReader(new FileReader(files[i])));
		
//      build model
//		System.out.println("-------------------- Apriori --------------------------------------");
//		Apriori model = new Apriori();
//		model.buildAssociations(data);
//		System.out.println(model);
//		
		 
		System.out.println(i+" ----------- FPGrowth ----------"+files[i].getName());
		
		FPGrowth fpgModel = new FPGrowth();
		fpgModel.setNumRulesToFind(15);
		fpgModel.buildAssociations(data);
		 
	    AssociationRules ar  =  fpgModel.getAssociationRules();
	   
	    int num = fpgModel.getAssociationRules().getNumRules();
	    System.out.println(num);
	    List<AssociationRule> listar = ar.getRules();
	    for(int k = 0; k <num ; k++){
	    	System.out.println(k+" "+listar.get(k));
	    }
          print(listar, "G:\\FPgrowthRules3\\FPgrowth_4_"+files[i].getName(), num);
	
	}
		
	}

public static void print(List<AssociationRule>li, String file_i, int num)throws Exception{
	PrintWriter out = new PrintWriter(new FileWriter(new File(file_i)));
	
	for(int k = 0; k <num ; k++){
    	out.println(li.get(k));
    }
	out.close();	
	
}
}
