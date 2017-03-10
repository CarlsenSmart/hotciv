package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.variants.WorldStrategy;
import thirdparty.ThirdPartyFractalGenerator;

import java.util.HashMap;

/**
 * Created by Yeilloz on 09-03-2017.
 */
public class FractalGeneratorAdaptor implements WorldStrategy {

    @Override
    public void makeWorld(GameImpl game) {

        HashMap<Position, TileImpl> tiles = game.getTiles();
        ThirdPartyFractalGenerator generator =
                new ThirdPartyFractalGenerator();
        String line = "";
        System.out.println("Demonstration of the fractal landscape generator");
        for ( int r = 0; r < 16; r++ ) {
            for ( int c = 0; c < 16; c++ ) {
                line = line + generator.getLandscapeAt(r,c);
            }
            System.out.println( line );
        }
        for (int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
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

    }
}
