package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.variants.ActionStrategy;
import hotciv.framework.variants.AgeStrategy;
import hotciv.framework.variants.WinnerStrategy;
import hotciv.framework.variants.WorldStrategy;

import java.util.ArrayList;
import java.util.HashMap;

/** Skeleton implementation of HotCiv.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

public class GameImpl implements Game {
    private Player player;
    private HashMap<Position,CityImpl> cities = new HashMap<>();
    private HashMap<Position, TileImpl> tiles = new HashMap<>();
    private HashMap<Position, UnitImpl> units = new HashMap<>();
    private int worldAge;
    private WinnerStrategy winnerStrategy;
    private AgeStrategy ageStrategy;
    private ActionStrategy actionStrategy;
    private WorldStrategy worldStrategy;

    public GameImpl(WinnerStrategy ws, AgeStrategy as, ActionStrategy acs, WorldStrategy worldstr){
        winnerStrategy = ws;
        ageStrategy = as;
        actionStrategy = acs;
        worldStrategy = worldstr;

        player = Player.RED;
        worldAge = -4000;

        worldStrategy.makeWorld(this);

    }

    public Tile getTileAt( Position p ) {
        Tile tile = tiles.get(p);
        return tile;
    }
    public Unit getUnitAt( Position p ) {
        Unit unit = units.get(p);
        return unit;
    }

    public City getCityAt( Position p ) {
        City city = cities.get(p);
        return city;
    }
    public Player getPlayerInTurn() {

        return player;
    }
    public Player getWinner() {
        Player winner = winnerStrategy.calculateWinner(this);
        return winner;
    }

    public int getAge() {
        return worldAge;
    }

    public boolean moveUnit( Position from, Position to ) {
        boolean moveAllowed = false;
        UnitImpl unit = units.get(from);
        Tile tile = tiles.get(to);

        if(unit.getMoveCount() == 0 || !(unit.getOwner().equals(getPlayerInTurn())))
            return false;

        int absDiffInRow = Math.abs(from.getRow()-to.getRow());
        int absDiffInCol = Math.abs(from.getColumn()-to.getColumn());

        if(absDiffInRow > 1 || absDiffInCol > 1) return false;
        if(!legalTile(tile)) return false;

        if(units.get(to) != null && !ownUnit(to, player)){
            units.remove(to);
        }

        if(units.get(to) == null){
            moveAllowed = true;

            units.remove(from);
            units.put(to, unit);

            unit.moved();

            if(getCityAt(to) != null && !ownCity(to , player)){
                CityImpl city = (CityImpl) cities.get(to);
                city.setOwner(player);
            }
        }
        return moveAllowed;
    }



    private boolean ownUnit(Position pos, Player player) {
        return units.get(pos).getOwner().equals(player);
    }
    private boolean ownCity(Position pos, Player player) {
        return cities.get(pos).getOwner().equals(player);
    }

    public void endOfTurn() {
        if(getPlayerInTurn().equals(Player.RED))
            player = Player.BLUE;
        else {
            player = Player.RED;
            endOfRound();
        }
    }


    public void endOfRound() {
        for(Position p : units.keySet()){
            units.get(p).restoreMoveCount();
        }

        for(Position p : cities.keySet()){
            CityImpl city = (CityImpl) cities.get(p);
            city.accumulateProduction(6);
            cityProduceUnit(p, city);
        }

        worldAge = ageStrategy.calculateAgeing(worldAge);
    }

    public void cityProduceUnit(Position p ,City c) {
        int row = p.getRow();
        int col = p.getColumn();
        Position newPos;

        for(int i = 0; i < 9; i++){
            switch(i) {
                case 0:
                    break;
                case 1: row--;
                    break;
                case 2: col++;
                    break;
                case 3: row++;
                    break;
                case 4: row++;
                    break;
                case 5: col--;
                    break;
                case 6: col--;
                    break;
                case 7: row--;
                    break;
                case 8: row--;
                    break;
            }

            newPos = new Position(row, col);
            if(getUnitAt(newPos) == null && legalTile(getTileAt(newPos))){
                if(c.produceUnit()){
                    units.put(newPos, new UnitImpl(c.getProduction(), player));
                }
            }
        }
    }

    private boolean legalTile(Tile tile) {
        if(tile.getTypeString().equals(GameConstants.OCEANS) || tile.getTypeString().equals(GameConstants.MOUNTAINS)){
            return false;
        }
        return true;
    }

    public void changeWorkForceFocusInCityAt( Position p, String balance ) {}

    public void changeProductionInCityAt( Position p, String unitType ) {
        CityImpl city = (CityImpl) cities.get(p);
        if(city.getOwner() == player)
            city.setProduction(unitType);
    }
    public void performUnitActionAt( Position p ) {
        actionStrategy.performAction(this, p);
    }

    public ArrayList<Position> getCitiesPosition() {
        ArrayList<Position> citiesPosition = new ArrayList<>();
        for(Position p : cities.keySet()){
            citiesPosition.add(p);
        }
        return citiesPosition;
    }

    public HashMap<Position, UnitImpl> getUnits() {
        return units;
    }

    public HashMap<Position, CityImpl> getCities(){
        return cities;
    }

    public HashMap<Position, TileImpl> getTiles(){
        return tiles;
    }

    public void fortify(UnitImpl unit, boolean fortify){
        if(unit.getOwner().equals(getPlayerInTurn()))
            unit.fortify(fortify);
    }
}
