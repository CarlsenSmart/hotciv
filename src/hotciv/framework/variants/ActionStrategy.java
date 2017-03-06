package hotciv.framework.variants;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

/**
 * Created by Yeilloz on 05-03-2017.
 */
public interface ActionStrategy {

    public void performAction(GameImpl game, Position p);
}
