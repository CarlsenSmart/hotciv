package hotciv.standard.dummies;

import hotciv.framework.variants.AgeStrategy;

/**
 * Created by Yeilloz on 05-03-2017.
 */
public class OneAgeStrategy implements AgeStrategy {
    @Override
    public int calculateAgeing(int age) {
        return age+1;
    }
}
