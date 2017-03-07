package hotciv.standard.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.variants.WinnerStrategy;
import hotciv.standard.GameImpl;

/**
 * Created by Yeilloz on 07-03-2017.
 */
public class ThreeWinsWinnerStrategy implements WinnerStrategy {
    Player winner = null;
    public Player calculateWinner(GameImpl game){
        if(game.getRedWins() >= 3 && winner == null)
            winner = Player.RED;
        else if(game.getBlueWins() >= 3 && winner == null)
            winner = Player.BLUE;
        return winner;
    }
}
