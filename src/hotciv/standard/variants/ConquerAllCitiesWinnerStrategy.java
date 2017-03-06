package hotciv.standard.variants;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.variants.WinnerStrategy;
import hotciv.standard.GameImpl;

/**
 * Created by Yeilloz on 05-03-2017.
 */
public class ConquerAllCitiesWinnerStrategy implements WinnerStrategy{
    @Override
    public Player calculateWinner(GameImpl game) {
        Player ownerOfFirstCity = game.getCityAt(game.getCitiesPosition().get(0)).getOwner();

        for(Position p : game.getCitiesPosition()){
            if(!game.getCityAt(p).getOwner().equals(ownerOfFirstCity) ) {
                return null;
            }
        }
        Player winner = ownerOfFirstCity;
        return winner;
    }
}
