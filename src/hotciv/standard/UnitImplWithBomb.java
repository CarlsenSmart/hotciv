package hotciv.standard;

import hotciv.framework.Player;

/**
 * Created by Yeilloz on 08-03-2017.
 */
public class UnitImplWithBomb extends UnitImpl {
    public UnitImplWithBomb(String type, Player player) {
        super(type, player);
    }

    @Override
    public int getDefensiveStrength() {
        int def = 0;
        switch(super.getTypeString()){
            case "archer": def = 3 + super.getFortityDef();
                break;
            case "legion": def = 2;
                break;
            case "settler": def = 3;
                break;
            case "bomb": def = 1;
        }
        return def;
    }

    @Override
    public int getAttackingStrength() {
        int att = 0;
        switch(super.getTypeString()){
            case "archer": att = 2;
                break;
            case "legion": att = 4;
                break;
            case "settler": att = 0;
                break;
            case "bomb": att = 0;
        }
        return att;
    }
}
