package hotciv.standard.variants;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.variants.WorldStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

/**
 * Created by Yeilloz on 06-03-2017.
 */
public class SimpleWorldStrategy implements WorldStrategy {
    @Override
    public void makeWorld(GameImpl game) {
        HashMap<Position, TileImpl> tiles = game.getTiles();
        HashMap<Position, CityImpl> cities = game.getCities();
        HashMap<Position, UnitImpl> units = game.getUnits();

        for(int i = 0; i < GameConstants.WORLDSIZE; i++){
            for(int j = 0; j < GameConstants.WORLDSIZE; j++){
                tiles.put(new Position(i,j), new TileImpl(GameConstants.PLAINS));
            }
        }

        cities.put(new Position(1,1), new CityImpl(Player.RED));
        cities.put(new Position(4,1), new CityImpl(Player.BLUE));

        tiles.put(new Position(1,0), new TileImpl(GameConstants.OCEANS));
        tiles.put(new Position(0,1), new TileImpl(GameConstants.HILLS));
        tiles.put(new Position(2,2), new TileImpl(GameConstants.MOUNTAINS));

        units.put(new Position(2,0), new UnitImpl(GameConstants.ARCHER, Player.RED));
        units.put(new Position(3,2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
        units.put(new Position(4,3), new UnitImpl(GameConstants.SETTLER, Player.RED));
    }
}
