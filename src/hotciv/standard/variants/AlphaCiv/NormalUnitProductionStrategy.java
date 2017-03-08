package hotciv.standard.variants.AlphaCiv;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.variants.ProduceUnitStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

/**
 * Created by Yeilloz on 08-03-2017.
 */
public class NormalUnitProductionStrategy implements ProduceUnitStrategy{
    @Override
    public void produceUnit(GameImpl game, CityImpl city, Position p) {

        if(city.produceUnit()){
            game.getUnits().put(p, new UnitImpl(city.getProduction(), city.getOwner()));
        }
    }
}
