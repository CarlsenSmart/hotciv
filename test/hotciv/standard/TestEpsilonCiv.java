package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.variants.AttackOutcomeStrategy;
import hotciv.standard.variants.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
/**
 * Created by Yeilloz on 07-03-2017.
 */
public class TestEpsilonCiv {
    /* min egen test


    private GameImpl game;
    private BattleOutcomeStrategy aos;

    @Before
    public void setUp(){
        game = new GameImpl(new ThreeWinsWinnerStrategy(), new LinearAgeStrategy(),
                new NonActionStrategy(), new SimpleWorldStrategy(), new BattleOutcomeStrategy());
        aos = new BattleOutcomeStrategy();
    }

    @Test
    public void shouldGaveRedOneCountingWinAfterASuccessAttack(){
        game.moveUnit(new Position(2,0), new Position(2,1));

        game.endOfTurn();

        game.moveUnit(new Position(3,2), new Position(3,1));

        game.endOfTurn();

        game.moveUnit(new Position(2,1), new Position(3,1));

        assertThat(game.getUnitAt(new Position(3,1)).getOwner(), is(Player.RED));
        assertThat(game.getRedWins(), is(1));
    }

    @Test
    public void shouldGaveBlueTwoCountingWinAfterASuccessAttack(){
        game.moveUnit(new Position(2,0), new Position(3,1));

        game.endOfTurn();

        game.moveUnit(new Position(3,2), new Position(3,1));

        assertThat(game.getUnitAt(new Position(3,1)).getOwner(), is(Player.BLUE));
        assertThat(game.getBlueWins(), is(1));

        game.endOfTurn(); //red turn with 6 production
        game.endOfTurn();
        game.endOfTurn(); //red turn with 2 production, after created an archer

        //move archer down
        game.moveUnit(new Position(1,1), new Position(2,1));

        game.endOfTurn();

        //blue legion attack
        game.moveUnit(new Position(3,1), new Position(2,1));

        assertThat(game.getUnitAt(new Position(2,1)).getOwner(), is(Player.BLUE));
        assertThat(game.getBlueWins(), is(2));
    }

    @Test
    public void shouldBeRedWinAfterThreeSuccessAttack(){
        //move archer
        game.moveUnit(new Position(2,0), new Position(2,1));

        game.endOfTurn(); // blue's turn

        //move legion
        game.moveUnit(new Position(3,2), new Position(3,1));;

        game.endOfTurn(); //red's turn

        // attacking with archer
        game.moveUnit(new Position(2,1), new Position(3,1));
        //killing legion
        assertThat(game.getRedWins(), is(1));

        game.endOfTurn();
        game.endOfTurn(); // red's turn and blue have an archer in city


        // move archer left
        game.moveUnit(new Position(3,1), new Position(3,0));

        game.endOfTurn(); // blue's turn

        //archer moves up
        game.moveUnit(new Position(4,1), new Position(3,1));

        game.endOfTurn(); // red's turn

        game.moveUnit(new Position(3,0), new Position(3,1));
        //killing legion
        assertThat(game.getRedWins(), is(2));

        game.endOfTurn();
        game.endOfTurn(); // red's turn and blue have an archer above city

        assertThat(game.getWinner(), is(nullValue()));

        // attacking archer in city with archer
        game.moveUnit(new Position(3,1), new Position(4,1));
        assertThat(game.getRedWins(), is(3));
        assertThat(game.getWinner(), is(Player.RED));
    }

    //isolated unit test
    @Test
    public void shouldGiveATerrainFactorOf2InForrestOrHill(){
        HashMap<Position, CityImpl> cities = new HashMap<>();
        Position pos = new Position(4,1);

        int fac = aos.getTerrainFactor(new TileImpl(GameConstants.FOREST),pos, cities);
        assertThat(fac, is(2));

        fac = aos.getTerrainFactor(new TileImpl(GameConstants.HILLS), pos, cities);
        assertThat(fac, is(2));
    }

    @Test
    public void shouldSayTrueToACityIsAt4_1(){
        HashMap<Position, CityImpl> cities = new HashMap<>();
        cities.put(new Position(4,1), new CityImpl(Player.BLUE));

        assertThat(aos.isCityHere(new Position(4,1), cities), is(true));
    }
    @Test
    public void shouldSayFalseToACityIsAt3_1(){
        HashMap<Position, CityImpl> cities = new HashMap<>();
        cities.put(new Position(4,1), new CityImpl(Player.BLUE));

        assertThat(aos.isCityHere(new Position(1,1), cities), is(false));
    }

    @Test
    public void shouldGiveTerrainFactor3IfInCity(){
        HashMap<Position, CityImpl> cities = new HashMap<>();
        Position pos = new Position(4,1);
        cities.put(new Position(4,1), new CityImpl(Player.BLUE));

        int fac = aos.getTerrainFactor(new TileImpl(GameConstants.FOREST) , pos, cities);
        assertThat(fac, is(3));
    }

    @Test
    public void shouldGiveTerrainFactor1IfNotForrestOrHillOrCity(){
        HashMap<Position, CityImpl> cities = new HashMap<>();
        Position pos = new Position(4,1);

        int fac = aos.getTerrainFactor(new TileImpl(GameConstants.OCEANS) , pos, cities);
        assertThat(fac, is(1));
    }

    @Test
    public void shouldHave4AdjacentPoints(){

    }
 */


    //semi isolated test
    @Test
    public void shouldHaveACombinedAttackStrengthAt12(){
        center = new Position(2,2);
        iter = BattleOutcomeStrategy.get8NeighborhoodIterator(center);
        neighborhood = convertIteration2List( iter );

        assertThat(BattleOutcomeStrategy.combinedAttack(game, new Position(2,2), Player.RED), is((2+3)*2));
    }

    @Test
    public void shouldHaveACombinedDefenseStrengthAt(){
        center = new Position(2,2);
        iter = BattleOutcomeStrategy.get8NeighborhoodIterator(center);
        neighborhood = convertIteration2List( iter );

        assertThat(BattleOutcomeStrategy.combinedDefense(game, new Position(2,2), Player.RED), is((3+3)*2));
    }


    // ikke så isoleret
    @Test
    public void shouldBeRedArcherWithFriendWinOverBlueLegionWhenD1Is4D2Is3AndPlainPlain(){
        game = new GameImpl(new ThreeWinsWinnerStrategy(), new LinearAgeStrategy(),
                new NonActionStrategy(), new SimpleWorldStrategy(),
                new BattleOutcomeStrategy(new FixedDieStrategy(4, 3)));

        game.moveUnit(new Position(2,0), new Position(3,1));

        game.moveUnit(new Position(4,3), new Position(4,2));

        game.endOfTurn();
        game.endOfTurn();

        boolean win = game.moveUnit(new Position(3,1), new Position(3,2));
        assertThat(win , is(true));
    }
    @Test
    public void shouldBeRedArcherWithFriendLooseOverBlueLegionWhenD1Is4D2Is3AndPlainCity(){
        game = new GameImpl(new ThreeWinsWinnerStrategy(), new LinearAgeStrategy(),
                new NonActionStrategy(), new SimpleWorldStrategy(),
                new BattleOutcomeStrategy(new FixedDieStrategy(4,3)));

        game.moveUnit(new Position(2,0), new Position(3,1));

        game.moveUnit(new Position(4,3), new Position(4,2));

        game.endOfTurn();

        //legion goes to town
        game.moveUnit(new Position(3,2), new Position(4,1));

        game.endOfTurn();

        boolean win = game.moveUnit(new Position(3,1), new Position(4,1));
        assertThat(win , is(false));
    }

    @Test
    public void shouldBeRemovedWhenFailsTheAttack(){
        game = new GameImpl(new ThreeWinsWinnerStrategy(), new LinearAgeStrategy(),
                new NonActionStrategy(), new SimpleWorldStrategy(),
                new BattleOutcomeStrategy(new FixedDieStrategy(1,2)));

        //archer with def 3, att 2
        game.moveUnit(new Position(2,0), new Position(3,1));

        game.endOfTurn();

        //legion with def 2, att 4
        boolean win = game.moveUnit(new Position(3,2), new Position(3,1));

        //win false because (4+0)*1*2 < (3+0)*1*1
        assertFalse(win);

        assertNull(game.getUnitAt(new Position(3,2)));
    }









    // HENRIKS KODE

    private Iterator<Position> iter;
    private ArrayList<Position> neighborhood;
    private Position center, p;

    Game game;

    @Before public void setUp() {
        game = new GameStubForBattleTesting();
    }

    /** helper method to insert elements in an iterator into a list. */
    private ArrayList<Position> convertIteration2List(Iterator<Position> iter) {
        neighborhood = new ArrayList<Position>();
        while ( iter.hasNext() ) {
            p = iter.next();
            neighborhood.add(p);
        }
        return neighborhood;
    }

    @Test public void shouldGive8PositionsForP8_8() {
        center = new Position(8,8);
        iter = BattleOutcomeStrategy.get8NeighborhoodIterator(center);
        neighborhood = convertIteration2List( iter );

        assertTrue( "Must contain (7,7)",
                neighborhood.contains( new Position(7,7)));
        assertTrue( "Must contain (9,9)",
                neighborhood.contains( new Position(9,9)));
        assertTrue( "Must contain (7,9)",
                neighborhood.contains( new Position(7,9)));
        assertTrue( "Must contain (8,7)",
                neighborhood.contains( new Position(8,7)));

        assertFalse( "Must not contain center position",
                neighborhood.contains( center ));

        assertFalse( "Must not contain (5,5) position",
                neighborhood.contains( new Position(5,5) ));

        assertEquals( "Should be 8 positions in the iterator",
                8, neighborhood.size());
    }

    @Test public void shouldGive3PositionsForP0_0() {
        center = new Position(0,0);
        iter = BattleOutcomeStrategy.get8NeighborhoodIterator(center);
        neighborhood = convertIteration2List( iter );

        assertTrue( "Must contain (1,0)",
                neighborhood.contains( new Position(1,0)));
        assertTrue( "Must contain (1,1)",
                neighborhood.contains( new Position(1,1)));
        assertTrue( "Must contain (0,1)",
                neighborhood.contains( new Position(0,1)));

        assertEquals( "Should be 3 positions in the iterator",
                3, neighborhood.size());

    }
    @Test public void shouldGive3PositionsForP15_15() {
        center = new Position(15,15);
        iter = BattleOutcomeStrategy.get8NeighborhoodIterator(center);
        neighborhood = convertIteration2List( iter );

        assertTrue( "Must contain (14,15)",
                neighborhood.contains( new Position(14,15)));
        assertTrue( "Must contain (14,14)",
                neighborhood.contains( new Position(14,14)));
        assertTrue( "Must contain (15,14)",
                neighborhood.contains( new Position(15,14)));

        assertEquals( "Should be 3 positions in the iterator",
                3, neighborhood.size());

    }

    @Test public void shouldGiveCorrectTerrainFactors() {
        // plains have multiplier 1
        assertEquals( 1, BattleOutcomeStrategy.getTerrainFactor( game, new Position(0,1)));
        // hills have multiplier 2
        assertEquals( 2, BattleOutcomeStrategy.getTerrainFactor( game, new Position(1,0)));
        // forest have multiplier 2
        assertEquals( 2, BattleOutcomeStrategy.getTerrainFactor( game, new Position(0,0)));
        // cities have multiplier 3
        assertEquals( 3, BattleOutcomeStrategy.getTerrainFactor( game, new Position(1,1)));
    }

    @Test public void shouldGiveSum1ForBlueAtP5_5() {
        assertEquals("Blue unit at (5,5) should get +1 support",
                +1, BattleOutcomeStrategy.getFriendlySupport( game, new Position(5,5), Player.BLUE));
    }

    @Test public void shouldGiveSum0ForBlueAtP2_4() {
        assertEquals("Blue unit at (2,4) should get +0 support",
                +0, BattleOutcomeStrategy.getFriendlySupport( game, new Position(2,4), Player.BLUE));
    }
    @Test public void shouldGiveSum2ForRedAtP2_4() {
        assertEquals("Red unit at (2,4) should get +2 support",
                +2, BattleOutcomeStrategy.getFriendlySupport( game, new Position(2,4), Player.RED));
    }
    @Test public void shouldGiveSum3ForRedAtP2_2() {
        assertEquals("Red unit at (2,2) should get +3 support",
                +3, BattleOutcomeStrategy.getFriendlySupport( game, new Position(2,2), Player.RED));
    }

}

// ================================== TEST STUBS ===
class StubTile implements Tile {
    private String type;
    public StubTile(String type) { this.type = type; }
    public String getTypeString() { return type; }
}



/** A test stub for testing the battle calculation methods in
 * Utility. The terrains are
 * 0,0 - forest;
 * 1,0 - hill;
 * 0,1 - plain;
 * 1,1 - city.
 *
 * Jeg udvider hans kode
 *
 * 2,2 - hill
 *
 * Red has units on 2,3; 3,2; 3,3; blue one on 4,4
 */
class GameStubForBattleTesting implements Game {
    public Tile getTileAt(Position p) {
        if ( p.getRow() == 0 && p.getColumn() == 0 ) {
            return new StubTile(GameConstants.FOREST);
        }
        if ( p.getRow() == 1 && p.getColumn() == 0 ) {
            return new StubTile(GameConstants.HILLS);
        }
        if ( p.getRow() == 2 && p.getColumn() == 2 ) {
            return new StubTile(GameConstants.HILLS);
        }
        return new StubTile(GameConstants.PLAINS);
    }
    public Unit getUnitAt(Position p) {
        if ( p.getRow() == 2 && p.getColumn() == 3 ||
                p.getRow() == 3 && p.getColumn() == 2 ||
                p.getRow() == 2 && p.getColumn() == 2 ||
                p.getRow() == 3 && p.getColumn() == 3 ) {
            return new UnitImpl(GameConstants.ARCHER, Player.RED);
        }
        if ( p.getRow() == 4 && p.getColumn() == 4 ) {
            return new UnitImpl(GameConstants.ARCHER, Player.BLUE);
        }
        return null;
    }
    public City getCityAt(Position p) {
        if ( p.getRow() == 1 && p.getColumn() == 1 ) {
            return new City() {
                public Player getOwner() { return Player.RED; }
                public int getSize() { return 1; }
                public String getProduction() {
                    return null;
                }
                public String getWorkforceFocus() {
                    return null;
                }
            };
        }
        return null;
    }

    // the rest is unused test stub methods...
    public void changeProductionInCityAt(Position p, String unitType) {}
    public void changeWorkForceFocusInCityAt(Position p, String balance) {}
    public void endOfTurn() {}
    public Player getPlayerInTurn() {return null;}
    public Player getWinner() {return null;}
    public int getAge() { return 0; }
    public boolean moveUnit(Position from, Position to) {return false;}
    public void performUnitActionAt( Position p ) {}
}
