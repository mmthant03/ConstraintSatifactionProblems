package solvers;
import java.util.ArrayList;
import java.util.HashMap;

import ItemBag.Constraint;

//Robert Dutile & Myo Min Thant
public class Solution {
	
	public HashMap<Character, ArrayList<Character>> bagContents;
	public boolean isFailure;
	
	//constructor
	public Solution() {
		this.bagContents = new HashMap<>();
		isFailure = false;
	}
	
	public HashMap<Character, ArrayList<Character>> getMap() {
		return bagContents;
	}
	
	public void setFailure() {
		isFailure = true;
	}
	
	public int numInBag(Object key) {
		return bagContents.get(key).size();
	}
	
	

}
