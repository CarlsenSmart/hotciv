package hotciv.standard.factories;

import hotciv.framework.variants.*;
import hotciv.standard.dummies.OneAgeStrategy;
import hotciv.standard.dummies.RedWinsWinnerStrategy;
import hotciv.standard.variants.AlphaCiv.*;

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

    @Override
    public ChangeUnitInProductionStrategy createChangeUnitStrategy() {
        return new StandardSelectionOfUnitsStrategy();
    }

    @Override
    public ProduceUnitStrategy createProduceUnitStrategy() {
        return new NormalUnitProductionStrategy();
    }
}
