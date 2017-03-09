package hotciv.standard.variants.DeltaCiv;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.variants.WorldStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yeilloz on 06-03-2017.
 */
public class DeltaWorldStrategy implements WorldStrategy {
    @Override
    public void makeWorld(GameImpl game) {
        HashMap<Position, TileImpl> tiles = game.getTiles();
        HashMap<Position, CityImpl> cities = game.getCities();
        HashMap<Position, UnitImpl> units = game.getUnits();

        String[] layout =
                new String[] {
                        "...ooMooooo.....",
                        "..ohhoooofffoo..",
                        ".oooooMooo...oo.",
                        ".ooMMMoooo..oooo",
                        "...ofooohhoooo..",
                        ".ofoofooooohhoo.",
                        "...ooo..........",
                        ".ooooo.ooohooM..",
                        ".ooooo.oohooof..",
                        "offfoooo.offoooo",
                        "oooooooo...ooooo",
                        ".ooMMMoooo......",
                        "..ooooooffoooo..",
                        "....ooooooooo...",
                        "..ooohhoo.......",
                        ".....ooooooooo..",
                };
        // Conversion...
        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                Position p = new Position(r,c);
                tiles.put( p, new TileImpl(type));
            }
        }

        cities.put(new Position(8,12), new CityImpl(Player.RED));
        cities.put(new Position(4,5), new CityImpl(Player.BLUE));

        units.put(new Position(5,4), new UnitImpl(GameConstants.LEGION,Player.BLUE));
        units.put(new Position(6,5), new UnitImpl(GameConstants.SETTLER,Player.BLUE));
        units.put(new Position(4,8), new UnitImpl(GameConstants.ARCHER,Player.BLUE));
    }
}
