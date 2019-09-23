package ItemBag;

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
    }

    public void addWeight(char item, int weight) {
        if(this.itemWeight.get(item) == null) this.itemWeight.put(item, weight);
    }

    public void addValue(char item, int value) {
        if(this.bagValue.get(item) == null) this.bagValue.put(item, value);
    }

    public void addConstraints(Rule rule, ArrayList<Character> consts) {
        this.constraints.add(new Constraint(rule, consts));
    }



}
