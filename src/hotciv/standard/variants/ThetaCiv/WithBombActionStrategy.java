package hotciv.standard.variants.ThetaCiv;

import hotciv.framework.GameConstants;
import hotciv.framework.GameConstantsMod;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.framework.variants.ActionStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.standard.variants.GammaCiv.WithActionStrategy;

/**
 * Created by Yeilloz on 08-03-2017.
 */
public class WithBombActionStrategy implements ActionStrategy {
    ActionStrategy actionStrategy = new WithActionStrategy();

    @Override
    public void performAction(GameImpl game, Position p) {
        Unit unit = game.getUnitAt(p);

        if(unit.getTypeString().equals(GameConstantsMod.BOMB)){
            destroy(game, p);
        }else
            actionStrategy.performAction(game, p);
    }

    public void destroy(GameImpl game, Position p) {
        //bomb's location
        int row = p.getRow();
        int col = p.getColumn();

        for(int i = 0; i < 9; i++){
            switch(i) {
                case 0:
                    break;
                case 1: row--;
                    break;
                case 2: col++;
                    break;
                case 3: row++;
                    break;
                case 4: row++;
                    break;
                case 5: col--;
                    break;
                case 6: col--;
                    break;
                case 7: row--;
                    break;
                case 8: row--;
                    break;
            }

            Position newPos = new Position(row, col);
            game.getUnits().remove(newPos);

        }
    }
}
