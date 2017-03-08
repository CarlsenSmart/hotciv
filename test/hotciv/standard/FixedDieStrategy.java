package hotciv.standard;

import hotciv.framework.variants.DieDecisionStrategy;

/**
 * Created by Yeilloz on 07-03-2017.
 */
public class FixedDieStrategy implements DieDecisionStrategy {
    private int d1;
    private int d2;
    private boolean hasJustedD1 = false;

    public FixedDieStrategy(int d1, int d2){
        this.d1 = d1;
        this.d2 = d2;
    }

    @Override
    public int rollDie() {
        if(!hasJustedD1) {
            hasJustedD1 = true;
            return d1;
        }else {
            hasJustedD1 = false;
            return d2;
        }
    }
}
