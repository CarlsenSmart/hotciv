package hotciv.standard.factories;

import hotciv.framework.variants.*;
import hotciv.standard.variants.*;

/**
 * Created by Yeilloz on 07-03-2017.
 */
public class EpsilonCivFactory implements GameFactory {
    private DieDecisionStrategy dieDecisionStrategy;

    public EpsilonCivFactory(){
        dieDecisionStrategy = new RandomDieStrategy();
    }

    public EpsilonCivFactory(DieDecisionStrategy dieDecisionStrategy){
        this.dieDecisionStrategy = dieDecisionStrategy;
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new ThreeWinsWinnerStrategy();
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
        return new BattleOutcomeStrategy(dieDecisionStrategy);
    }

    @Override
    public ChangeUnitInProductionStrategy createChangeUnitStrategy() {
        return new StandardSelectionOfUnitsStrategy();
    }

    @Override
    public ProduceUnitStrategy createProduceUnitStrategy() {
        return null;
    }
}
