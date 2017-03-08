package hotciv.standard.variants;

import hotciv.framework.variants.ChangeUnitInProductionStrategy;

/**
 * Created by Yeilloz on 08-03-2017.
 */
public class SelectionWithBombStrategy implements ChangeUnitInProductionStrategy {
    @Override
    public boolean changeUnitInProd(String s) {
        switch (s){
            case "archer": return true;
            case "legion": return true;
            case "settler": return true;
            case "bomb": return true;
        }
        return false;
    }
}
