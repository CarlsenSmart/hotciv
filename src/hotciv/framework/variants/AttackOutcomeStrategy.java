package hotciv.framework.variants;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

/**
 * Created by Yeilloz on 07-03-2017.
 */
public interface AttackOutcomeStrategy {

    public boolean attack(GameImpl game, Position from, Position to);
}
