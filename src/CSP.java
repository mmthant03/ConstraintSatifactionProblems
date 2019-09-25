import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Set;

import ItemBag.Constraint;
import ItemBag.ItemBag;
import ItemBag.Rule;
import solvers.Solution;


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
        
        ArrayList<Character> uiCons = new ArrayList<Character>();
        ArrayList<Character> ueCons = new ArrayList<Character>();
        ArrayList<Character> beCons = new ArrayList<Character>();
        ArrayList<Character> bneCons = new ArrayList<Character>();
        ArrayList<Character> miCons = new ArrayList<Character>();
        
        int upperLimit = 0;
        int lowerLimit = 0;
        
        int k = 0;
        
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
                
                // read unary inclusive restraints
                
                for(k = 0; k < splitLine.length; k++) {
                	uiCons.add(Character.valueOf(splitLine[k].charAt(0)));
                }
            }
            while((line = br.readLine()) != null) {
                if(line.contains("####")) {
                	break;
                }
                
                String[] splitLine = line.split(" ");
                
                //read unary exclusive constraints
                for(k = 0; k < splitLine.length; k++) {
                	ueCons.add(Character.valueOf(splitLine[k].charAt(0)));
                }
            }
            while((line = br.readLine()) != null) {
                if(line.contains("####")) {
                	break;
                }
                
                String[] splitLine = line.split(" ");
                
                //read binary equals constraints
                for(k = 0; k < splitLine.length; k++) {
                	beCons.add(Character.valueOf(splitLine[k].charAt(0)));
                }
            }
            while((line = br.readLine()) != null) {
                if(line.contains("####")) {
                	break;
                }
                
                String[] splitLine = line.split(" ");
                
                //read binary not equals constraints
                for(k = 0; k < splitLine.length; k++) {
                	bneCons.add(Character.valueOf(splitLine[k].charAt(0)));
                }
            }
            while((line = br.readLine()) != null) {
                
                String[] splitLine = line.split(" ");
                
                //read mutual inclusive constraints
                for(k = 0; k < splitLine.length; k++) {
                	miCons.add(Character.valueOf(splitLine[k].charAt(0)));
                }
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

        if(uiCons.size() > 0) {
        	itemBag.addConstraints(Rule.UnaryInc, uiCons);
        }

        if(ueCons.size() > 0) {
        	itemBag.addConstraints(Rule.UnaryExc, ueCons);
        }

        if(beCons.size() > 0) {
        	itemBag.addConstraints(Rule.BinaryEq, beCons);
        }
        
        if(bneCons.size() > 0) {
        	itemBag.addConstraints(Rule.BinaryNEq, bneCons);
        }
        
        if(miCons.size() > 0) {
        	itemBag.addConstraints(Rule.MutualInc, miCons);
        }

        itemBag.display();

        
    }
    
    //backtracking algorithm
    
    public Solution backTrackSearch(ItemBag itemBag) {
    	Solution initSol = new Solution();
    	Set bagKeys = itemBag.bagValue.keySet();
    	Object[] bags = bagKeys.toArray();
    	for(int m = 0; m < bags.length; m++) {
    		initSol.bagContents.put((Character) bags[m], new ArrayList<Character>());
    	}
    	
    	return backTrack(initSol, itemBag);
    }
    
    
    public Solution backTrack(Solution workingSol, ItemBag itemBag) {
    	if(isComplete(workingSol, itemBag)) {
    		return workingSol;
    	}
    }
    /*
    public boolean isComplete(Solution checkSol, ItemBag itemBag) {
    	if(checkSol.isFailure) {
    		return false;
    	}
    	Constraint checker;
    	int l = 0;
    	//check bag limits
    	for(l = 0; l < itemBag.bagValue.size(); l++) {
    		itemBag.bagValue.
    	}
    	for(l = 0; l < itemBag.constraints.size(); l++) {
    		checker = itemBag.constraints.get(l);
    		//switch
    	}
    }
    */

}
