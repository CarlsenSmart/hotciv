package hotciv.standard.factories;

import hotciv.framework.variants.*;
import hotciv.standard.variants.AlphaCiv.*;
import hotciv.standard.variants.DeltaCiv.DeltaWorldStrategy;

/**
 * Created by Yeilloz on 07-03-2017.
 */
public class DeltaCivFactory implements GameFactory {
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new Age3000BCWinnerStrategy();
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
        return new DeltaWorldStrategy();
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
