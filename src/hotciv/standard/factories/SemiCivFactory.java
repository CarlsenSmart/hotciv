package hotciv.standard.factories;

import hotciv.framework.variants.*;
import hotciv.standard.variants.AlphaCiv.NormalUnitProductionStrategy;
import hotciv.standard.variants.AlphaCiv.StandardSelectionOfUnitsStrategy;
import hotciv.standard.variants.BetaCiv.BetaSpecialAgeStrategy;
import hotciv.standard.variants.BetaCiv.ConquerAllCitiesWinnerStrategy;
import hotciv.standard.variants.DeltaCiv.DeltaWorldStrategy;
import hotciv.standard.variants.EpsilonCiv.BattleOutcomeStrategy;
import hotciv.standard.variants.EpsilonCiv.RandomDieStrategy;
import hotciv.standard.variants.EpsilonCiv.ThreeWinsWinnerStrategy;
import hotciv.standard.variants.GammaCiv.WithActionStrategy;
import hotciv.standard.variants.ThetaCiv.SelectionWithBombStrategy;

/**
 * Created by Yeilloz on 09-03-2017.
 */
public class SemiCivFactory implements GameFactory {
    private DieDecisionStrategy dieDecisionStrategy;

    public SemiCivFactory(){
        dieDecisionStrategy = new RandomDieStrategy();
    }

    public SemiCivFactory(DieDecisionStrategy dieDecisionStrategy){
        this.dieDecisionStrategy = dieDecisionStrategy;
    }
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new ThreeWinsWinnerStrategy();
    }

    @Override
    public AgeStrategy createAgeStrategy() {
        return new BetaSpecialAgeStrategy();
    }

    @Override
    public ActionStrategy createActionStrategy() {
        return new WithActionStrategy();
    }

    @Override
    public WorldStrategy createWorldStrategy() {
        return new DeltaWorldStrategy();
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
        return new NormalUnitProductionStrategy();
    }
}
