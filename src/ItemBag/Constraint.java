package ItemBag;

import java.util.ArrayList;

public class Constraint {
    public Rule rule;
    public ArrayList<Character> constraints;

    public Constraint(Rule rule, ArrayList<Character> constraints) {
        this.rule = rule;
        this.constraints = new ArrayList<>();

        for (char c : constraints) {
            this.constraints.add(c);
        }
    }


}
