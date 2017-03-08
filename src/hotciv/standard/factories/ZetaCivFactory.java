package hotciv.standard.factories;

import hotciv.framework.variants.*;
import hotciv.standard.variants.AlphaCiv.*;
import hotciv.standard.variants.ZetaCiv.ConquerAllSwitchToThreeWinsWinnerStrategy;

/**
 * Created by Yeilloz on 08-03-2017.
 */
public class ZetaCivFactory implements GameFactory {
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new ConquerAllSwitchToThreeWinsWinnerStrategy();
    }

    @Override
    public AgeStrategy createAgeStrategy() {
        return new LinearAgeStrategy();
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
