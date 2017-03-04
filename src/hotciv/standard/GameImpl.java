package hotciv.standard;

import hotciv.framework.*;
import javafx.geometry.Pos;

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
    Player player;
    HashMap<Position,City> cities = new HashMap<>();
    HashMap<Position, TileImpl> tiles = new HashMap<>();
    HashMap<Position, UnitImpl> units = new HashMap<>();
    int wolrdAge;

    public GameImpl(){
        player = Player.RED;

        for(int i = 0; i < GameConstants.WORLDSIZE; i++){
            for(int j = 0; j < GameConstants.WORLDSIZE; j++){
                tiles.put(new Position(i,j), new TileImpl(GameConstants.PLAINS));
            }
        }

        cities.put(new Position(1,1), new CityImpl(Player.RED));
        cities.put(new Position(4,1), new CityImpl(Player.BLUE));

        tiles.put(new Position(1,0), new TileImpl(GameConstants.OCEANS));
        tiles.put(new Position(0,1), new TileImpl(GameConstants.HILLS));
        tiles.put(new Position(2,2), new TileImpl(GameConstants.MOUNTAINS));

        units.put(new Position(2,0), new UnitImpl(GameConstants.ARCHER, Player.RED));
        units.put(new Position(3,2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
        units.put(new Position(4,3), new UnitImpl(GameConstants.SETTLER, Player.RED));

        wolrdAge = -4000;

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
        if(wolrdAge == -3000)
            return Player.RED;
        return null;
    }

    public int getAge() {
        return wolrdAge;
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

        wolrdAge+= 100;
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
    public void performUnitActionAt( Position p ) {}
}
