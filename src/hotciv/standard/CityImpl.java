package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

/**
 * Created by Yeilloz on 03-03-2017.
 */
public class CityImpl implements City {
    private Player owner;
    private String unitProducing;
    private String workFocus;
    private int treasury;

    public CityImpl(Player p){
        owner = p;
        unitProducing = GameConstants.ARCHER;
        workFocus = GameConstants.productionFocus;
        treasury = 0;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getProduction() {
        return unitProducing;
    }

    @Override
    public String getWorkforceFocus() {
        return workFocus;
    }

    public int getTreasury(){
        return treasury;
    }

    public void accumulateProduction(int addProd){
        treasury += addProd;
    }

    public boolean produceUnit() {
        boolean enoughProduction = false;
        int cost = 0;

        switch (getProduction()){
            case "archer": cost = 10;
                break;
            case "legion": cost = 15;
                break;
            case "settler": cost = 30;
                break;
        }

        if(cost <= getTreasury()) {
            enoughProduction = true;
            accumulateProduction(-cost);
        }

        return enoughProduction;
    }

    public void setProduction(String s) {
        unitProducing = s;
    }

    public void setOwner(Player player){
        owner = player;
    }
}
