package game;

import java.util.Random;
import java.util.ArrayList;

public class Risk {
    Random rand = new Random();
    ArrayList<Integer> risks = new ArrayList<Integer>();
    public boolean risk() {
        int risk = rand.nextInt(20);
        risks.add(risk);
        if(risk < 10) {
            return true;
        }
        return false;
    }


}
