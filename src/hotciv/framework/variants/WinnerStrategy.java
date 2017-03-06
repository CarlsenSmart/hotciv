package hotciv.framework.variants;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

/**
 * Created by Yeilloz on 04-03-2017.
 */
public interface WinnerStrategy {

    public Player calculateWinner(GameImpl game);
}
