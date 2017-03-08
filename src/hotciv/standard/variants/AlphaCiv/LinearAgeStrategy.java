package hotciv.standard.variants.AlphaCiv;

import hotciv.framework.variants.AgeStrategy;

/**
 * Created by Yeilloz on 04-03-2017.
 */
public class LinearAgeStrategy implements AgeStrategy {

    public int calculateAgeing(int worldAge){
        return worldAge += 100;
    }
}
