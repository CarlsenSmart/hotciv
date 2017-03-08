package hotciv.standard.factories;

import hotciv.framework.variants.*;
import hotciv.standard.variants.SelectionWithBombStrategy;
import hotciv.standard.variants.*;

/**
 * Created by Yeilloz on 08-03-2017.
 */
public class ThetaCivFactory implements GameFactory {
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
        return new WithActionStrategy();
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
        return new SelectionWithBombStrategy();
    }

    @Override
    public ProduceUnitStrategy createProduceUnitStrategy() {
        return null;
    }


}
