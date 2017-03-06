package hotciv.standard.variants;

import hotciv.framework.Player;
import hotciv.framework.variants.WinnerStrategy;
import hotciv.standard.GameImpl;

/**
 * Created by Yeilloz on 04-03-2017.
 */
public class Age3000BCWinnerStrategy implements WinnerStrategy {
    Player winner = null;
    public Player calculateWinner(GameImpl g){
        if(g.getAge() == -3000)
            winner = Player.RED;
        return winner;
    }
}
