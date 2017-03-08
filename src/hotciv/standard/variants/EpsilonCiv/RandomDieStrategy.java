package hotciv.standard.variants.EpsilonCiv;

import hotciv.framework.variants.DieDecisionStrategy;

import java.util.Random;

/**
 * Created by Yeilloz on 07-03-2017.
 */
public class RandomDieStrategy implements DieDecisionStrategy {
    @Override
    public int rollDie() {
        return new Random().nextInt(6) + 1;
    }
}
