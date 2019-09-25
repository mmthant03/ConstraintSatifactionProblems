package ItemBag;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ItemBag {

    public HashMap<Character, Integer> itemWeight;
    public HashMap<Character, Integer> bagValue;
    public int lowerLimit;
    public int upperLimit;
    public ArrayList<Constraint> constraints;

    public ItemBag(int lowerLimit, int upperLimit) {
        itemWeight = new HashMap<>();
        bagValue = new HashMap<>();
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.constraints = new ArrayList<Constraint>();
    }

    public void addWeight(char item, int weight) {
        if(this.itemWeight.get(item) == null) this.itemWeight.put(item, weight);
    }

    public ArrayList<Character> getBags() {
        ArrayList<Character> allBags = new ArrayList<>();
        for (char bag : bagValue.keySet()) {
            allBags.add(bag);
        }
        return allBags;
    }

    public void addValue(char item, int value) {
        if(this.bagValue.get(item) == null) this.bagValue.put(item, value);
    }

    public ArrayList<Character> getItems() {
        ArrayList<Character> allItems = new ArrayList<>();
        for (char item : itemWeight.keySet()) {
            allItems.add(item);
        }
        return allItems;
    }

    public void addConstraints(Rule rule, ArrayList<Character> consts) {
        Constraint newCon = new Constraint(rule, consts);
        this.constraints.add(newCon);
    }

    public void display() {
        System.out.println("*** Items and their weights ***");
        for (char item : itemWeight.keySet()) {
            System.out.println("Item : " + item + ", Weight : " + itemWeight.get(item));
        }
        System.out.println();

        System.out.println("*** Bags and their capacities ***");
        for (char bag : bagValue.keySet()) {
            System.out.println("Bag : " + bag + ", Capacity : " + bagValue.get(bag));
        }
        System.out.println();

        System.out.println("Lower limit : " + this.lowerLimit + "\n");
        System.out.println("Upper Limit : " + this.upperLimit + "\n");

        if(this.constraints != null) {
            for (Constraint con : this.constraints) {
                System.out.println(con.rule);
                for (char c : con.constraints) {
                    System.out.print(c + "\t");
                }
                System.out.println();
            }
        }
    }

    public boolean hasConstraint(Character c) {
        if (this.constraints != null) {
            for (Constraint con : this.constraints) {
                if (con.constraints.contains(c)) return true;
            }
        }
        return false;
    }



}
