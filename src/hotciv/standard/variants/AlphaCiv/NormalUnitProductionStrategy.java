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

        if(produceUnit(city)){
            game.getUnits().put(p, new UnitImpl(city.getProduction(), city.getOwner()));
        }
    }

    public boolean produceUnit(CityImpl city) {
        boolean enoughProduction = false;
        int cost = 0;

        switch (city.getProduction()){
            case "archer": cost = 10;
                break;
            case "legion": cost = 15;
                break;
            case "settler": cost = 30;
                break;
        }

        if(cost != 0 && cost <= city.getTreasury()) {
            enoughProduction = true;
            city.accumulateProduction(-cost);
        }

        return enoughProduction;
    }
}
