package hotciv.standard.variants;

import hotciv.framework.Position;
import hotciv.framework.variants.AttackOutcomeStrategy;
import hotciv.standard.GameImpl;

/**
 * Created by Yeilloz on 07-03-2017.
 */
public class MoverWinsAttackOutcomeStrategy implements AttackOutcomeStrategy {

    public boolean attack(GameImpl game, Position from, Position to){
        return true;
    }
}
