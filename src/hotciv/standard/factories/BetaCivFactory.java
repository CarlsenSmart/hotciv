package hotciv.standard.factories;

import hotciv.framework.variants.*;
import hotciv.standard.variants.*;

/**
 * Created by Yeilloz on 07-03-2017.
 */
public class BetaCivFactory implements GameFactory {
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new ConquerAllCitiesWinnerStrategy();
    }

    @Override
    public AgeStrategy createAgeStrategy() {
        return new BetaSpecialAgeStrategy();
    }

    @Override
    public ActionStrategy createActionStrategy() {
        return new NonActionStrategy();
    }

    @Override
    public WorldStrategy createWorldStrategy() {
        return new SimpleWorldStrategy();
    }

    @Override
    public AttackOutcomeStrategy createAttackStrategy() {
        return new MoverWinsAttackOutcomeStrategy();
    }
}
