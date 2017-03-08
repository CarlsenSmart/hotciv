package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created by Yeilloz on 03-03-2017.
 */
public class UnitImpl implements Unit {
    private String unitType;
    private Player owner;
    private int moveCount;
    private int fortityDef = 0;

    public UnitImpl(String type, Player player) {
        unitType = type;
        owner = player;
        moveCount = 1;

    }

    @Override
    public String getTypeString() {
        return unitType;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }

    @Override
    public int getDefensiveStrength() {
        int def = 0;
        switch(unitType){
            case "archer": def = 3 + fortityDef;
                break;
            case "legion": def = 2;
                break;
            case "settler": def = 3;
                break;
        }
        return def;
    }

    @Override
    public int getAttackingStrength() {
        int att = 0;
        switch(unitType){
            case "archer": att = 2;
                break;
            case "legion": att = 4;
                break;
            case "settler": att = 0;
        }
        return att;
    }


    public void moved(){
        moveCount--;
    }


    public void restoreMoveCount() {
        if(fortityDef == 3)
            moveCount = 0;
        else
            moveCount = 1;
    }

    public void fortify(boolean forti){
        if(forti){
            fortityDef = 3;
            moveCount = 0;
        } else {
            fortityDef = 0;
            moveCount = 1;
        }
    }

    public int getFortityDef(){
        return fortityDef;
    }
}
