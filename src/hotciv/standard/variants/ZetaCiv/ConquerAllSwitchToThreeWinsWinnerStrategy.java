package hotciv.standard.variants.ZetaCiv;

import hotciv.framework.Player;
import hotciv.framework.variants.WinnerStrategy;
import hotciv.standard.GameImpl;
import hotciv.standard.variants.BetaCiv.ConquerAllCitiesWinnerStrategy;
import hotciv.standard.variants.EpsilonCiv.ThreeWinsWinnerStrategy;

/**
 * Created by Yeilloz on 07-03-2017.
 */
public class ConquerAllSwitchToThreeWinsWinnerStrategy implements WinnerStrategy {

    private WinnerStrategy conquerAllCitiesStrategy;
    private WinnerStrategy threeWinsStrategy;

    public ConquerAllSwitchToThreeWinsWinnerStrategy(){
        conquerAllCitiesStrategy = new ConquerAllCitiesWinnerStrategy();
        threeWinsStrategy = new ThreeWinsWinnerStrategy();
    }

    @Override
    public Player calculateWinner(GameImpl game) {
        if(game.getRoundCount() > 20){
            return threeWinsStrategy.calculateWinner(game);
        }
        return conquerAllCitiesStrategy.calculateWinner(game);
    }
}
