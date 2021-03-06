package hotciv.standard;

import hotciv.framework.variants.ChangeUnitInProductionStrategy;
import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

import java.util.HashMap;

/**
 * Created by Yeilloz on 03-03-2017.
 */
public class CityImpl implements City {
    private Player owner;
    private String unitProducing;
    private String workFocus;
    private int treasury;
    private HashMap<String, Integer> units = new HashMap<>();


    public CityImpl(Player p){
        owner = p;
        unitProducing = GameConstants.ARCHER;
        workFocus = GameConstants.productionFocus;
        treasury = 0;
    }

    public CityImpl(Player player, ChangeUnitInProductionStrategy changeUnitInProductionStrategy){
        this(player);
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



    public void setProduction(String s) {
            unitProducing = s;
    }

    public void setOwner(Player player){
        owner = player;
    }
}
