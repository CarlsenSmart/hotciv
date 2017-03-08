package hotciv.standard.variants.ThetaCiv;

import hotciv.framework.Position;
import hotciv.framework.variants.ProduceUnitStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.standard.UnitImplWithBomb;

/**
 * Created by Yeilloz on 08-03-2017.
 */
public class BombInProductionStrategy implements ProduceUnitStrategy {
    @Override
    public void produceUnit(GameImpl game, CityImpl city, Position p) {
        if(produceUnit(city)){
            if(city.getProduction().equals("bomb")){
                game.getUnits().put(p, new UnitImplWithBomb(city.getProduction(), city.getOwner()));
            }else
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
            case "bomb": cost = 60;
        }

        if(cost != 0 && cost <= city.getTreasury()) {
            enoughProduction = true;
            city.accumulateProduction(-cost);
        }

        return enoughProduction;
    }
}
