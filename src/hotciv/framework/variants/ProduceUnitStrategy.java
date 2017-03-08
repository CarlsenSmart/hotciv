package hotciv.framework.variants;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;

/**
 * Created by Yeilloz on 08-03-2017.
 */
public interface ProduceUnitStrategy {
    public void produceUnit(GameImpl game, CityImpl city, Position p);
}
