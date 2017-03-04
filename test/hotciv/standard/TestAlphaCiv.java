package hotciv.standard;

import com.sun.istack.internal.NotNull;
import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for AlphaCiv test cases

 Updated Oct 2015 for using Hamcrest matchers

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
public class TestAlphaCiv {
    private Game game;

    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl();
    }

    @Test
    public void shouldBeRedAsStartingPlayer() {
        assertThat(game.getPlayerInTurn(), is(Player.RED));
    }

    @Test
    public void shouldBeBlueAfterRedEndOfTurn(){
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    }

    @Test
    public void shouldBeRedAfterBlueEndOfTurn(){
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.RED));
    }

    @Test
    public void shouldBeRedCityAt1_1(){
        City city = game.getCityAt(new Position(1,1));
        assertThat(city.getOwner(), is(Player.RED));
    }

    @Test
    public void shouldHaveCityStoreAPlayer(){
        City city = new CityImpl(Player.RED);
        assertThat(city.getOwner(), is(Player.RED));

        city = new CityImpl(Player.BLUE);
        assertThat(city.getOwner(), is(Player.BLUE));
    }

    @Test
    public void shouldBeBlueCityAt4_1(){
        City city = game.getCityAt(new Position(4,1));
        assertThat(city.getOwner(), is(Player.BLUE));
    }

    @Test
    public void shouldBeOceanAt1_0(){
        Position p = new Position(1,0);
        assertThat(game.getTileAt(p).getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void shouldStoreTileStringInTile(){
        Tile t = new TileImpl(GameConstants.OCEANS);
        assertThat(t.getTypeString(), is(GameConstants.OCEANS));

        t = new TileImpl(GameConstants.PLAINS);
        assertThat(t.getTypeString(), is(GameConstants.PLAINS));

        t = new TileImpl(GameConstants.MOUNTAINS);
        assertThat(t.getTypeString(), is(GameConstants.MOUNTAINS));

        t = new TileImpl(GameConstants.HILLS);
        assertThat(t.getTypeString(), is(GameConstants.HILLS));

        t = new TileImpl(GameConstants.FOREST);
        assertThat(t.getTypeString(), is(GameConstants.FOREST));
    }

    @Test
    public void shouldBeHillAt0_1(){
        Position p = new Position(0,1);
        assertThat(game.getTileAt(p).getTypeString(), is(GameConstants.HILLS));
    }

    @Test
    public void shouldBeMountainAt2_2(){
        Position p = new Position(2,2);
        assertThat(game.getTileAt(p).getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void shouldBePlainTileAtSomePlacesOtherThanGiven(){
        Position p = new Position(15,15);
        assertThat(game.getTileAt(p).getTypeString(), is(GameConstants.PLAINS));

        p = new Position(7,3);
        assertThat(game.getTileAt(p).getTypeString(), is(GameConstants.PLAINS));

        p = new Position(8,0);
        assertThat(game.getTileAt(p).getTypeString(), is(GameConstants.PLAINS));

        p = new Position(4,10);
        assertThat(game.getTileAt(p).getTypeString(), is(GameConstants.PLAINS));
    }

    @Test
    public void shouldBeARedArcherAt2_0(){
        Unit unit = game.getUnitAt(new Position(2,0));
        assertThat(unit.getTypeString(), is(GameConstants.ARCHER));
        assertThat(unit.getOwner(), is(Player.RED));
    }

    @Test
    public void shouldBeAOwnerToUnits(){
        Unit unit = new UnitImpl(GameConstants.ARCHER, Player.RED);
        assertThat(unit.getOwner(),is(Player.RED));

        unit = new UnitImpl(GameConstants.ARCHER, Player.BLUE);
        assertThat(unit.getOwner(),is(Player.BLUE));
    }


    @Test
    public void shouldBeABlueLegion3_2(){
        Unit unit = game.getUnitAt(new Position(3,2));
        assertThat(unit.getTypeString(), is(GameConstants.LEGION));
        assertThat(unit.getOwner(), is(Player.BLUE));
    }

    @Test
    public void shouldBeARedSettlerA4_3(){
        Unit unit = game.getUnitAt(new Position(4,3));
        assertThat(unit.getTypeString(), is(GameConstants.SETTLER));
        assertThat(unit.getOwner(), is(Player.RED));
    }

    @Test
    public void shouldHaveAllCityPopAtOne(){
        assertThat(game.getCityAt(new Position(1,1)).getSize(), is(1));
        assertThat(game.getCityAt(new Position(4,1)).getSize(), is(1));
    }

    @Test
    public void shouldHaveAllUnitsMaxMovementAtOneFromStart(){
        Unit unit = new UnitImpl(GameConstants.ARCHER, Player.RED);
        assertThat(unit.getMoveCount(), is(1));

        unit = new UnitImpl(GameConstants.LEGION, Player.RED);
        assertThat(unit.getMoveCount(), is(1));

        unit = new UnitImpl(GameConstants.SETTLER, Player.RED);
        assertThat(unit.getMoveCount(), is(1));
    }

    @Test
    public void shouldHaveArcherDefAt3(){
        Unit unit = new UnitImpl(GameConstants.ARCHER, Player.RED);
        assertThat(unit.getDefensiveStrength(), is(3));
    }

    @Test
    public void shouldHaveLegionDefAt2(){
        Unit unit = new UnitImpl(GameConstants.LEGION, Player.RED);
        assertThat(unit.getDefensiveStrength(), is(2));
    }

    @Test
    public void shouldHaveSettlerDefAt3(){
        Unit unit = new UnitImpl(GameConstants.SETTLER, Player.RED);
        assertThat(unit.getDefensiveStrength(), is(3));
    }

    @Test
    public void shouldHaveArcherAttAt2(){
        Unit unit = new UnitImpl(GameConstants.ARCHER, Player.RED);
        assertThat(unit.getAttackingStrength(), is(2));
    }

    @Test
    public void shouldHaveLegionAttAt4(){
        Unit unit = new UnitImpl(GameConstants.LEGION, Player.RED);
        assertThat(unit.getAttackingStrength(), is(4));
    }

    @Test
    public void shouldHaveSettlerAttAt0(){
        Unit unit = new UnitImpl(GameConstants.SETTLER, Player.RED);
        assertThat(unit.getAttackingStrength(), is(0));
    }

    @Test
    public void shouldCitiesProduce6ProdPerRound(){
        City cityRed = game.getCityAt(new Position(1,1));
        assertThat(cityRed.getTreasury(), is(0));

        endOfRound();

        assertThat(cityRed.getTreasury(), is(6));

        endOfRound();

        assertThat(cityRed.getTreasury(), is(2));

        endOfRound();

        assertThat(cityRed.getTreasury(), is(8));
    }

    @Test
    public void shouldMakeUnitMoveDown(){
        Position from = new Position(2,0);
        Position to = new Position(3,0);

        assertThat(game.moveUnit(from,to), is(true));

        assertThat(game.getUnitAt(from), is(nullValue()));

        assertThat(game.getUnitAt(to).getTypeString(), is(GameConstants.ARCHER));
        assertThat(game.getUnitAt(to).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldLooseOneMoveCountAfterMove(){
        Position from = new Position(2,0);
        Position to = new Position(3,0);

        game.moveUnit(from,to);
        assertThat(game.getUnitAt(to).getMoveCount(), is(0));
    }

    @Test
    public void shouldNotBeAbleToMoveUnitWithZeroMoveCount(){
        Position from = new Position(2,0);
        Position to = new Position(3,0);

        game.moveUnit(from,to);

        Position newTo = new Position(4, 0);

        assertThat(game.moveUnit(to, newTo),is(false));
    }

    @Test
    public void shouldRestoreAllUnitsMoveCountAfterARound(){
        Position from = new Position(2,0);
        Position to = new Position(3,0);

        game.moveUnit(from,to);

        endOfRound();

        assertThat(game.getUnitAt(to).getMoveCount(), is(1));

    }

    @Test
    public void shouldNotMoveOnOcean(){
        Position from = new Position(2,0);
        Position to = new Position(1,0);

        assertThat(game.moveUnit(from,to), is(false));
    }

    @Test
    public void shouldNotMoveBlueUnitsWhenRed(){
        assertThat(game.getPlayerInTurn(), is(Player.RED));

        Position from = new Position(3,2);
        Position to = new Position(3,3);

        assertThat(game.moveUnit(from,to), is(false));

        game.endOfTurn();

        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
        assertThat(game.moveUnit(from,to), is(true));

    }

    @Test
    public void shouldNotMoveRedUnitesWhenBlue(){
        game.endOfTurn();

        Position from = new Position(2,0);
        Position to = new Position(3,0);

        assertThat(game.moveUnit(from,to), is(false));

        game.endOfTurn();

        assertThat(game.moveUnit(from,to), is(true));
    }

    @Test
    public void shouldNotBeAbleToMoveOverMountains(){
        game.endOfTurn();

        Position from = new Position(3,2);
        Position to = new Position(2,2);

        assertThat(game.moveUnit(from,to), is(false));
    }

    @Test
    public void shouldNotMoveMoreThanOneDistanceAway(){
        Position from = new Position(2,0);
        Position to = new Position(4,0);
        assertThat(game.moveUnit(from,to),is(false));

        to = new Position(10,10);
        assertThat(game.moveUnit(from,to),is(false));
    }

    @Test
    public void shouldProduceArcherAsDefault(){
        City city = game.getCityAt(new Position(1,1));
        assertThat(city.getProduction(), is(GameConstants.ARCHER));
    }

    @Test
    public void shouldCost10ProdForArcher(){
        City city = game.getCityAt(new Position(1,1));

        assertThat(city.produceUnit(), is(false));

        endOfRound();
        endOfRound();

        assertThat(city.getTreasury(), is((2*6)-10));
        assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is(GameConstants.ARCHER));
        assertThat(game.getUnitAt(new Position(1,1)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldSelectAnotherUnitToBeUnderProd(){
        CityImpl city = (CityImpl) game.getCityAt(new Position(1,1));
        city.setProduction(GameConstants.LEGION);
        assertThat(city.getProduction(), is(GameConstants.LEGION));

        city.setProduction(GameConstants.SETTLER);
        assertThat(city.getProduction(), is(GameConstants.SETTLER));

        city.setProduction(GameConstants.ARCHER);
        assertThat(city.getProduction(), is(GameConstants.ARCHER));
    }


    @Test
    public void shouldCost15ToProduceLegion(){
        CityImpl city = (CityImpl) game.getCityAt(new Position(1,1));

        city.setProduction(GameConstants.LEGION);

        endOfRound();
        endOfRound();
        endOfRound(); //now have 18 production and produced a legion. So have 3 production.

        assertThat(city.getTreasury(), is((3*6)-15));
        assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is(GameConstants.LEGION));
        assertThat(game.getUnitAt(new Position(1,1)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldCost30ToProduceSettler(){
        CityImpl city = (CityImpl) game.getCityAt(new Position(1,1));

        city.setProduction(GameConstants.SETTLER);

        endOfRound();
        endOfRound();
        endOfRound();
        endOfRound();
        endOfRound(); // 30 production and 0 production after settler build

        assertThat(city.getTreasury(), is((5*6)-30));
        assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is(GameConstants.SETTLER));
        assertThat(game.getUnitAt(new Position(1,1)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldSelectProductionThroughGame(){
        Position cityPos = new Position(1,1);
        City city = game.getCityAt(cityPos);

        game.changeProductionInCityAt(cityPos,GameConstants.LEGION);
        assertThat(city.getProduction(), is(GameConstants.LEGION));

        game.changeProductionInCityAt(cityPos,GameConstants.SETTLER);
        assertThat(city.getProduction(), is(GameConstants.SETTLER));

        game.changeProductionInCityAt(cityPos,GameConstants.ARCHER);
        assertThat(city.getProduction(), is(GameConstants.ARCHER));
    }


    @Test
    public void shouldOnlySelectProductionOnOwnCity(){
        Position blueCity = new Position(4,1);
        City city = game.getCityAt(blueCity);

        game.changeProductionInCityAt(blueCity, GameConstants.LEGION);
        assertThat( city.getProduction(), is(GameConstants.ARCHER));

        game.endOfTurn();

        game.changeProductionInCityAt(blueCity, GameConstants.LEGION);
        assertThat( city.getProduction(), is(GameConstants.LEGION));
    }

    @Test
    public void shouldFarmUnitsAfterProductionStartingNorthAndMovingClockwise(){
        assertThat(game.getUnitAt(new Position(1,1)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(0,1)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(0,2)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(1,2)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(2,2)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(2,1)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(2,0)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(1,0)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(0,0)), is(nullValue()));

        endOfRound();
        endOfRound(); // 2*6-10 = 2

        assertThat(game.getUnitAt(new Position(1,1)), is(notNullValue()));

        endOfRound();
        endOfRound(); // 2*6+2-10 = 4

        assertThat(game.getUnitAt(new Position(0,1)), is(notNullValue()));

        endOfRound();
        endOfRound(); // 6. After one unit prod

        assertThat(game.getUnitAt(new Position(0,2)), is(notNullValue()));

        endOfRound(); // 12
        endOfRound(); // 18. => 8

        assertThat(game.getUnitAt(new Position(1,2)), is(notNullValue()));

        endOfRound(); // 14 => 4

        assertThat(game.getUnitAt(new Position(2,1)), is(notNullValue()));

        endOfRound(); // 10 => 0

        assertThat(game.getUnitAt(new Position(0,0)), is(notNullValue()));
    }

    @Test
    public void shouldLetAttackingUnitAlwaysWin(){
        boolean firstMove = game.moveUnit(new Position(2,0), new Position(2,1));

        assertThat(firstMove, is(true));

        assertThat(game.getUnitAt(new Position(3,2)).getOwner(), is(Player.BLUE));

        endOfRound(); //reset movecount

        boolean secondMove = game.moveUnit(new Position(2,1), new Position(3,2));

        assertThat(secondMove, is(true));

        assertThat(game.getUnitAt(new Position(3,2)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldChangeOwnerOfTownWhenAttackingUnitWinsOrStandInTown(){
        boolean firstMove = game.moveUnit(new Position(2,0), new Position(3,0));

        assertThat(firstMove, is(true));

        endOfRound();

        assertThat(game.getCityAt(new Position(4,1)).getOwner(), is(Player.BLUE));

        boolean secondMove = game.moveUnit(new Position(3,0), new Position(4,1));

        assertThat(secondMove, is(true));

        assertThat(game.getCityAt(new Position(4,1)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldBe4000BCWhenGameStart(){
        assertThat(game.getAge(), is(-4000));
    }

    @Test
    public void shouldAge100YearsPerRound(){
        endOfRound();
        assertThat(game.getAge(), is(-3900));
        endOfRound();
        assertThat(game.getAge(), is(-3800));
        endOfRound();
        assertThat(game.getAge(), is(-3700));
        endOfRound();
        assertThat(game.getAge(), is(-3600));
    }

    @Test
    public void shouldBeRedWinnerWhenAt3000BC(){
        goTo3000BC();
        assertThat(game.getWinner(), is(Player.RED));
    }





    public void endOfRound(){
        game.endOfTurn();
        game.endOfTurn();
    }

    public void goTo3000BC(){
        endOfRound();
        endOfRound();
        endOfRound();
        endOfRound();
        endOfRound(); // 3500
        endOfRound();
        endOfRound();
        endOfRound();
        endOfRound();
        endOfRound(); // 3000
    }





    /** REMOVE ME.  Not a test of HotCiv, just an example of what
     matchers the hamcrest library has... */
    @Test
    public void shouldDefinetelyBeRemoved() {
        assertThat("This is a dummy test", containsString("dummy"));
        String s = null;
        assertThat(s, is(nullValue()));
        s = "Ok";
        assertThat(s, is("Ok"));
        List<String> l = new ArrayList<String>();
        l.add("Bimse");
        l.add("Bumse");
        assertThat(l, hasItems(new String[] {"Bimse","Bumse"}));
    }
}
