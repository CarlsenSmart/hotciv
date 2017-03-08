package hotciv.framework.variants;

import hotciv.standard.variants.BattleOutcomeStrategy;

/**
 * Created by Yeilloz on 07-03-2017.
 */
public interface GameFactory {

    public WinnerStrategy createWinnerStrategy();
    public AgeStrategy createAgeStrategy();
    public ActionStrategy createActionStrategy();
    public WorldStrategy createWorldStrategy();
    public AttackOutcomeStrategy createAttackStrategy();
    public ChangeUnitInProductionStrategy createChangeUnitStrategy();
    public ProduceUnitStrategy createProduceUnitStrategy();

}
