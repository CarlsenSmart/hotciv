package hotciv.standard.variants;

import hotciv.framework.GameConstants;
import hotciv.framework.variants.ChangeUnitInProductionStrategy;

import java.util.HashMap;

/**
 * Created by Yeilloz on 08-03-2017.
 */
public class StandardSelectionOfUnitsStrategy implements ChangeUnitInProductionStrategy {


    @Override
    public boolean changeUnitInProd(String s) {
        switch (s){
            case "archer": return true;
            case "legion": return true;
            case "settler": return true;
        }
        return false;
    }
}
