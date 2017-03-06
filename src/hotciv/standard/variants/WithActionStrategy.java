package hotciv.standard.variants;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.framework.variants.ActionStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

/**
 * Created by Yeilloz on 06-03-2017.
 */
public class WithActionStrategy implements ActionStrategy {
    boolean fortify = false;
    @Override
    public void performAction(GameImpl game, Position p) {
        UnitImpl unit = (UnitImpl) game.getUnitAt(p);

        if(unit.getTypeString().equals(GameConstants.SETTLER)) {
            game.getUnits().remove(p);
            game.getCities().put(p, new CityImpl(game.getPlayerInTurn()));
        }

        if(unit.getTypeString().equals(GameConstants.ARCHER)){
            if(fortify)
                fortify = false;
            else
                fortify = true;
            game.fortify(unit, fortify);
        }
    }
}
