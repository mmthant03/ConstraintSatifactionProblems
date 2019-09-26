import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
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

		//setup ItemBag
		ItemBag itemBag = new ItemBag(lowerLimit, upperLimit);
        
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
                
                //itemNames.add(splitLine[0]);
                //weights.add(Integer.valueOf(splitLine[1]));
				itemBag.addWeight(splitLine[0].charAt(0), Integer.valueOf(splitLine[1]));

            }
            while((line = br.readLine()) != null) {
                if(line.contains("####")) {
                	break;
                }
                
                String[] splitLine = line.split(" ");
                
                //bagNames.add(splitLine[0]);
                //capacities.add(Integer.valueOf(splitLine[1]));
				itemBag.addValue(splitLine[0].charAt(0), Integer.valueOf(splitLine[1]));
            }
            while((line = br.readLine()) != null) {
                if(line.contains("####")) {
                	break;
                }
                
                String[] splitLine = line.split(" ");
                
                upperLimit = Integer.parseInt(splitLine[0]);
                lowerLimit = Integer.parseInt(splitLine[1]);
                itemBag.upperLimit = upperLimit;
                itemBag.lowerLimit = lowerLimit;
            }
            while((line = br.readLine()) != null) {
                if(line.contains("####")) {
                	break;
                }

                String[] splitLine = line.split(" ");
                
                // read unary inclusive restraints

				for(String l : splitLine) {
					uiCons.add(Character.valueOf(l.charAt(0)));
				}
				itemBag.addConstraints(Rule.UnaryInc, uiCons);
				uiCons = new ArrayList<>();
                
//                for(k = 0; k < splitLine.length; k = k + 2) {
//                	uiCons.add(Character.valueOf(splitLine[k].charAt(0)));
//                	uiCons.add(Character.valueOf(splitLine[k+1].charAt(0)));
//					itemBag.addConstraints(Rule.UnaryInc, uiCons);
//					uiCons = new ArrayList<>();
//                }


            }
            while((line = br.readLine()) != null) {
                if(line.contains("####")) {
                	break;
                }
                
                String[] splitLine = line.split(" ");

				for(String l : splitLine) {
					ueCons.add(Character.valueOf(l.charAt(0)));
				}
				itemBag.addConstraints(Rule.UnaryExc, ueCons);
				ueCons = new ArrayList<>();

                //read unary exclusive constraints
//                for(k = 0; k < splitLine.length; k = k + 2) {
//                	ueCons.add(Character.valueOf(splitLine[k].charAt(0)));
//					ueCons.add(Character.valueOf(splitLine[k+1].charAt(0)));
//					itemBag.addConstraints(Rule.UnaryExc, ueCons);
//					ueCons = new ArrayList<>();
//
//                }
            }
            while((line = br.readLine()) != null) {
                if(line.contains("####")) {
                	break;
                }
                
                String[] splitLine = line.split(" ");

				for(String l : splitLine) {
					beCons.add(Character.valueOf(l.charAt(0)));
				}
				itemBag.addConstraints(Rule.BinaryEq, beCons);
				beCons = new ArrayList<>();

                //read binary equals constraints
//                for(k = 0; k < splitLine.length; k++) {
//                	beCons.add(Character.valueOf(splitLine[k].charAt(0)));
//                }
            }
            while((line = br.readLine()) != null) {
                if(line.contains("####")) {
                	break;
                }
                
                String[] splitLine = line.split(" ");

				for(String l : splitLine) {
					bneCons.add(Character.valueOf(l.charAt(0)));
				}
				itemBag.addConstraints(Rule.BinaryNEq, bneCons);
				bneCons = new ArrayList<>();
                
                //read binary not equals constraints
//                for(k = 0; k < splitLine.length; k++) {
//                	bneCons.add(Character.valueOf(splitLine[k].charAt(0)));
//                }
            }
            while((line = br.readLine()) != null) {
                
                String[] splitLine = line.split(" ");

				for(String l : splitLine) {
					miCons.add(Character.valueOf(l.charAt(0)));
				}
				itemBag.addConstraints(Rule.MutualInc, miCons);
				miCons = new ArrayList<>();

                //read mutual inclusive constraints
//                for(k = 0; k < splitLine.length; k++) {
//                	miCons.add(Character.valueOf(splitLine[k].charAt(0)));
//                }
            }
            
        } catch(Exception e) {
        	System.out.println("Exception thrown in file read");
        	return;
        }
        

        
//        for(int i = 0; i < itemNames.size(); i++) {
//        	itemBag.addWeight(itemNames.get(i).charAt(0), weights.get(i).intValue());
//        }
//
//        for(int j = 0; j < bagNames.size(); j++) {
//        	itemBag.addValue(bagNames.get(j).charAt(0), capacities.get(j).intValue());
//        }

//        if(uiCons.size() > 0) {
//        	itemBag.addConstraints(Rule.UnaryInc, uiCons);
//        }
//
//        if(ueCons.size() > 0) {
//        	itemBag.addConstraints(Rule.UnaryExc, ueCons);
//        }

//        if(beCons.size() > 0) {
//        	itemBag.addConstraints(Rule.BinaryEq, beCons);
//        }
        
//        if(bneCons.size() > 0) {
//        	itemBag.addConstraints(Rule.BinaryNEq, bneCons);
//        }
        
//        if(miCons.size() > 0) {
//        	itemBag.addConstraints(Rule.MutualInc, miCons);
//        }

        itemBag.display();

        Solution sol = backTrackSearch(itemBag);

        for (char bag : sol.bagContents.keySet()) {
            System.out.print(bag + " ");
            for (char item : sol.bagContents.get(bag)) {
                System.out.print(item + " ");
            }
            System.out.println();

        }

        
    }
    
    //backtracking algorithm
    
    public static Solution backTrackSearch(ItemBag itemBag) {
    	Solution initSol = new Solution();
    	Set bagKeys = itemBag.bagValue.keySet();
    	Object[] bags = bagKeys.toArray();
    	for(int m = 0; m < bags.length; m++) {
    		initSol.bagContents.put((Character) bags[m], new ArrayList<Character>());

    	}
    	return backTrack(initSol, itemBag);
    }
    
    
    public static Solution backTrack(Solution workingSol, ItemBag itemBag) {
    	if(isComplete(workingSol, itemBag)) {
    		System.out.println("Reached Completeness");
    		return workingSol;
    	}

    	Set b = workingSol.bagContents.keySet();
    	Object[] bags = b.toArray();

    	for(int k = 0; k < bags.length; k++) {
    	    char currentBag = (Character) bags[k];
    	    // get all the items
            ArrayList<Character> allItems = itemBag.getAvailableItems();
            // for each item, try putting it into the current bag
            for (Character item : allItems) {
                // The bag cannot contain two same items,
                // skip if it is the same item
                if(workingSol.containItem(currentBag, item)) continue;
                if(!constraintCheck(currentBag,item,itemBag,workingSol)) {
					System.out.println("Reached here");
                	continue;
				}
                // add the item into the bag

                workingSol.addContent(currentBag, item);
                int returnCap = itemBag.addItemToBag(currentBag, item);
                if(returnCap<0) {
                    workingSol.removeContent(currentBag, item);
					System.out.println(returnCap);
					continue;
                }
				System.out.println("Reached here1");
                // recursively search for solution
                workingSol = backTrack(workingSol, itemBag);
                // if the solution ends up with no failure, return that solution
                if(workingSol.isFailure == false) {
                    return workingSol;
                }
                // solution has failure, thus remove that item and further prune that item
                workingSol.removeContent(currentBag, item);
            }
        }
    	return workingSol;
    }

    public static boolean constraintCheck(Character currentBag, Character value, ItemBag itemBag, Solution workingSol) {
		System.out.println("Current Bag : " + currentBag + ", Current Item : " + value);
		boolean uiFlag = true;
		boolean ueFlag = true;
		boolean beFlag = true;
		boolean bnFlag = true;
		boolean miFlag = true;
    	for(Constraint con: itemBag.constraints) {
			ArrayList<Character> chars = con.constraints;
    		switch(con.rule) {
				case UnaryInc:
					if(Character.isUpperCase(value) && Character.isLowerCase(currentBag)) {
						if(chars.contains(value)) {
							if(chars.contains(currentBag)) {
								uiFlag = true;
								continue;
							}
							else {
								uiFlag = false;
							}
						} else if (!chars.contains(value)) {
							continue;
						}
					}
					break;
				case UnaryExc:
					if(Character.isUpperCase(value) && Character.isLowerCase(currentBag)) {
						if(chars.contains(value)) {
							if(!chars.contains(currentBag)) {
								ueFlag = true;
								continue;
							}
							else {
								ueFlag = false;
							}
						} else if (!chars.contains(value)) {
							continue;
						}
					}
					break;
				case BinaryEq:
					if(Character.isLowerCase(value)) return false;
					ArrayList<Character> items = workingSol.bagContents.get(currentBag);
					if( items.size() < 1 ) continue;
					if( items.size() == 1) {
						if(chars.contains(items.get(0))) {
							if(chars.contains(value)) return true;
							else continue;
						}
						else {
							if(!chars.contains(value)) continue;
							else return false;
						}
					}
					items.add(value);
					if( items.containsAll(chars) ) {
						items.remove(value);
						beFlag = true;
						continue;
					}
					else {
						items.remove(value);
						beFlag = false;
					}
					break;
				case BinaryNEq:
					if(Character.isLowerCase(value)) return false;
					ArrayList<Character> items1 = workingSol.bagContents.get(currentBag);
					if( items1.size() < 1 ) continue;
					if( items1.size() == 1) {
						if(chars.contains(items1.get(0))) {
							if(chars.contains(value)) return false;
							else continue;
						}
					}
					items1.add(value);
					if( items1.containsAll(chars) ) {
						items1.remove(value);
						return false;
					}
					else {
						items1.remove(value);
						bnFlag = true;
					}
					break;
				case MutualInc:
					if(!chars.contains(currentBag)) continue;
					ArrayList<Character> miItems = workingSol.bagContents.get(currentBag);
					ArrayList<Character> conBags = new ArrayList<>();
					ArrayList<Character> conItems = new ArrayList<>();
					for (char c : chars) {
						if(Character.isLowerCase(c)) conBags.add(c);
						else conItems.add(c);
					}
					if( miItems.size() < 1 ) continue;
					if( miItems.size() == 1) {
						if(chars.contains(miItems.get(0))) {
							if(chars.contains(value)) return false;
							else continue;
						}
					}
					miItems.add(value);
					if( miItems.containsAll(chars) ) {
						miItems.remove(value);
						return false;
					}
					else {
						miItems.remove(value);
						miFlag = true;
					}
					break;

			}
		}
    	//System.out.println("Constraint final boolean " + (uiFlag&&ueFlag&&beFlag&&bnFlag&&miFlag));
		if(uiFlag && ueFlag && beFlag && bnFlag && miFlag) {
			return true;
		} else {
			//System.out.println("Constraint Error" + uiFlag + ueFlag + beFlag + bnFlag + miFlag);
			return false;
		}
	}


    
    public static boolean isComplete(Solution checkSol, ItemBag itemBag) {
    	if(checkSol.isFailure) {
    		return false;
    	}
    	Constraint checker;
    	int l = 0;
    	Set bagKeys = checkSol.bagContents.keySet();
    	Object[] bags = bagKeys.toArray();
    	
    	//check bag limits
    	for(l = 0; l < bags.length; l++) {
    		int amountInBag = checkSol.numInBag(bags[l]);
    		if(amountInBag < itemBag.lowerLimit && itemBag.lowerLimit != 0) {
    			return false;
    		} else if(amountInBag > itemBag.upperLimit && itemBag.upperLimit != 0) {
    			return false;
    		}
    	}

    	// check capacity
		for(char bag : checkSol.bagContents.keySet()) {
			int currentCap = itemBag.bagValue.get(bag);
			int minVal = itemBag.minimumValue.get(bag);
			if(currentCap > minVal) {
				return false;
			}
		}

    	//check for duplicates:
    	ArrayList<Character> items = new ArrayList<Character>();
    	for(Map.Entry mapElement : checkSol.bagContents.entrySet()) {
    		ArrayList<Character> holder = (ArrayList<Character>) mapElement.getValue();
    		for(Character c : holder) {
    			if(items.contains(c)) {
    				return false;
    			} else {
    				items.add(c);
    			}
    		}
    	}
    	
    	//check constraints.
    	for(l = 0; l < itemBag.constraints.size(); l++) {
    		checker = itemBag.constraints.get(l);
    		if(checker.constraints.size() > 0) {
    			ArrayList<Character> conArray = checker.constraints;
        		switch(checker.rule) {
    				case UnaryInc:
    					for(int n = 0; n < conArray.size(); n++) {
    						Character itemChar = conArray.get(n);
    						ArrayList<Character> validBags = new ArrayList<Character>();
    						for(int o = n + 1; o < conArray.size(); o++) {
    							if(Character.isUpperCase(conArray.get(o))) {
    								for(Map.Entry<Character, ArrayList<Character>> mapElement : checkSol.bagContents.entrySet()) {
    									if(!(validBags.contains(mapElement.getKey())) && mapElement.getValue().contains(itemChar)) {
    										return false;
    									}
    								}
    								n = o - 1;
    								break;
    							} else if(o == conArray.size() - 1) {
    								for(Map.Entry<Character, ArrayList<Character>> mapElement : checkSol.bagContents.entrySet()) {
    									if(!(validBags.contains(mapElement.getKey())) && mapElement.getValue().contains(itemChar)) {
    										return false;
    									}
    								}
    								n = o - 1;
    								break;
    							} else {
    								validBags.add(conArray.get(o));
    							}
    						}
    					}
    					break;

    				case UnaryExc:
    					for(int n = 0; n < conArray.size(); n++) {
    						Character itemChar = conArray.get(n);
    						ArrayList<Character> validBags = new ArrayList<Character>();
    						for(int o = n + 1; o < conArray.size(); o++) {
    							if(Character.isUpperCase(conArray.get(o))) {
    								for(Map.Entry<Character, ArrayList<Character>> mapElement : checkSol.bagContents.entrySet()) {
    									if(validBags.contains(mapElement.getKey()) && mapElement.getValue().contains(itemChar)) {
    										return false;
    									}
    								}
    								n = o - 1;
    								break;
    							} else if(o == conArray.size() - 1) {
    								for(Map.Entry<Character, ArrayList<Character>> mapElement : checkSol.bagContents.entrySet()) {
    									if(!(validBags.contains(mapElement.getKey())) && mapElement.getValue().contains(itemChar)) {
    										return false;
    									}
    								}
    								n = o - 1;
    								break;
    							} else {
    								validBags.add(conArray.get(o));
    							}
    						}
    					}
    					break;

    				case BinaryEq:
    					for(int n = 0; n < conArray.size(); n = n + 2) {
    						Character item1 = conArray.get(n);
    						Character item2 = conArray.get(n + 1);
    						for(Map.Entry<Character, ArrayList<Character>> mapElement : checkSol.bagContents.entrySet()) {
    							ArrayList<Character> holdingArray = mapElement.getValue();
    							if((holdingArray.contains(item1) && !holdingArray.contains(item2)) || (!holdingArray.contains(item1) && holdingArray.contains(item2))) {
    								return false;
    							}
    						}
    					}
    					break;

    				case BinaryNEq:
    					for(int n = 0; n < conArray.size(); n = n + 2) {
    						Character item1 = conArray.get(n);
    						Character item2 = conArray.get(n + 1);
    						for(Map.Entry<Character, ArrayList<Character>> mapElement : checkSol.bagContents.entrySet()) {
    							ArrayList<Character> holdingArray = mapElement.getValue();
    							if(holdingArray.contains(item1) && holdingArray.contains(item2)) {
    								return false;
    							}
    						}
    					}
    					break;

    				case MutualInc:
    					for(int n = 0; n < conArray.size(); n = n + 4) {
    						Character item1 = conArray.get(n);
    						Character item2 = conArray.get(n + 1);
    						Character bag1 = conArray.get(n+2);
    						Character bag2 = conArray.get(n+3);

    						if(checkSol.bagContents.get(bag1).contains(item1) && !checkSol.bagContents.get(bag2).contains(item2)) {
    							return false;
    						} else if(checkSol.bagContents.get(bag2).contains(item1) && !checkSol.bagContents.get(bag1).contains(item2)) {
    							return false;
    						} else if(checkSol.bagContents.get(bag1).contains(item2) && !checkSol.bagContents.get(bag2).contains(item1)) {
    							return false;
    						} else if(checkSol.bagContents.get(bag2).contains(item2) && !checkSol.bagContents.get(bag1).contains(item1)) {
    							return false;
    						}
    					}
    					break;
        		}
    		}
    	}
    	
    	//if all tests passed:
    	return true;
    }
    

}
