package hotciv.standard.dummies;

import hotciv.framework.Player;
import hotciv.framework.variants.WinnerStrategy;
import hotciv.standard.GameImpl;

/**
 * Created by Yeilloz on 05-03-2017.
 */
public class RedWinsWinnerStrategy implements WinnerStrategy {
    @Override
    public Player calculateWinner(GameImpl game) {
        return Player.RED;
    }
}
