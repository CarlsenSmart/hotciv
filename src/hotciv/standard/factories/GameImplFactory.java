package hotciv.standard.factories;

import hotciv.framework.variants.*;
import hotciv.standard.dummies.OneAgeStrategy;
import hotciv.standard.dummies.RedWinsWinnerStrategy;
import hotciv.standard.variants.MoverWinsAttackOutcomeStrategy;
import hotciv.standard.variants.NonActionStrategy;
import hotciv.standard.variants.SimpleWorldStrategy;

/**
 * Created by Yeilloz on 07-03-2017.
 */
public class GameImplFactory implements GameFactory {
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new RedWinsWinnerStrategy();
    }

    @Override
    public AgeStrategy createAgeStrategy() {
        return new OneAgeStrategy();
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
