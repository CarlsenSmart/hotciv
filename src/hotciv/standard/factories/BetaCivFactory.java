package hotciv.standard.factories;

import hotciv.framework.variants.*;
import hotciv.standard.variants.AlphaCiv.*;
import hotciv.standard.variants.BetaCiv.BetaSpecialAgeStrategy;
import hotciv.standard.variants.BetaCiv.ConquerAllCitiesWinnerStrategy;

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

    @Override
    public ChangeUnitInProductionStrategy createChangeUnitStrategy() {
        return new StandardSelectionOfUnitsStrategy();
    }

    @Override
    public ProduceUnitStrategy createProduceUnitStrategy() {
        return new NormalUnitProductionStrategy();
    }
}
