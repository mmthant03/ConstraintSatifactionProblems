package solvers;
import java.lang.reflect.Array;
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

	public void addContent(Character key, Character newVal) {
		ArrayList<Character> itemList = this.bagContents.get(key);
		if(itemList == null) {
			itemList = new ArrayList<>();
			itemList.add(newVal);
			this.bagContents.put(key, itemList);
		} else {
			// add if item is not already in list
			if(!itemList.contains(newVal)) itemList.add(newVal);
		}
	}

	public void removeContent(Character key, Character item) {
		if(this.containItem(key, item)) {
			this.bagContents.get(key).remove((item));
		}
	}

	public boolean containItem(Character key, Character item) {
		if (this.bagContents.get(key) != null && this.bagContents.get(key).contains(item)) {
			return true;
		}
		return false;
	}
	
	public int numInBag(Object key) {
		return bagContents.get(key).size();
	}
	
	

}
