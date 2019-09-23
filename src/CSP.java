import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import ItemBag.ItemBag;


//Robert Dutile & Myo Min Thant

public class CSP {
    public static void main (String[] args) {
    	
    	//check for argument
        if(args.length < 1) {
        	System.out.println("Error, no input file specified");
        	return;
        }
        
        //init variables
        String inputFileName = args[0];
        
        ArrayList<String> itemNames = new ArrayList<String>();
        ArrayList<Integer> weights = new ArrayList<Integer>();
        
        ArrayList<String> bagNames = new ArrayList<String>();
        ArrayList<Integer> capacities = new ArrayList<Integer>();
        
        int upperLimit = 0;
        int lowerLimit = 0;
        
        
        //read file
        try {
            File file = new File(inputFileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            if ((line = br.readLine()) != null) {
            } else {
            	System.out.println("error, empty file");
            	return;
            }
            while((line = br.readLine()) != null){
                if(line.contains("####")) {
                	break;
                }
                String[] splitLine = line.split(" ");
                
                itemNames.add(splitLine[0]);
                weights.add(Integer.valueOf(splitLine[1]));
            }
            while((line = br.readLine()) != null) {
                if(line.contains("####")) {
                	break;
                }
                
                String[] splitLine = line.split(" ");
                
                bagNames.add(splitLine[0]);
                capacities.add(Integer.valueOf(splitLine[1]));
            }
            while((line = br.readLine()) != null) {
                if(line.contains("####")) {
                	break;
                }
                
                String[] splitLine = line.split(" ");
                
                upperLimit = Integer.parseInt(splitLine[0]);
                lowerLimit = Integer.parseInt(splitLine[1]);
            }
            while((line = br.readLine()) != null) {
                if(line.contains("####")) {
                	break;
                }
                
                String[] splitLine = line.split(" ");
                
                //read unary inclusive constraints
            }
            while((line = br.readLine()) != null) {
                if(line.contains("####")) {
                	break;
                }
                
                String[] splitLine = line.split(" ");
                
                //read unary exclusive constraints
            }
            while((line = br.readLine()) != null) {
                if(line.contains("####")) {
                	break;
                }
                
                String[] splitLine = line.split(" ");
                
                //read binary equals constraints
            }
            while((line = br.readLine()) != null) {
                if(line.contains("####")) {
                	break;
                }
                
                String[] splitLine = line.split(" ");
                
                //read binary not equals constraints
            }
            while((line = br.readLine()) != null) {
                
                String[] splitLine = line.split(" ");
                
                //read mutual inclusive constraints
            }
            
        } catch(Exception e) {
        	System.out.println("Exception thrown in file read");
        	return;
        }
        
        //setup ItemBag
        ItemBag itemBag = new ItemBag(lowerLimit, upperLimit);
        
        for(int i = 0; i < itemNames.size(); i++) {
        	itemBag.addWeight(itemNames.get(i).charAt(0), weights.get(i).intValue());
        }
        
        for(int j = 0; j < bagNames.size(); j++) {
        	itemBag.addValue(bagNames.get(j).charAt(0), capacities.get(j).intValue());
        }

        
    }



}
