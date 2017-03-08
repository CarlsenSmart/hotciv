package hotciv.standard;
import hotciv.framework.*;
import hotciv.standard.factories.ThetaCivFactory;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Yeilloz on 08-03-2017.
 */
public class TestThetaCiv {
    GameImpl game;

    @Before
    public void setUp(){
        game = new GameImpl(new ThetaCivFactory());
    }

    @Test
    public void shouldSelectBombUnit(){
        City city = game.getCityAt(new Position(1,1));
        game.changeProductionInCityAt(new Position(1,1), "bomb");
        assertThat(city.getProduction(), is("bomb"));
    }


    @Test
    public void shouldProduceBombAfter10RoundsAndShouldHave0InCityTreasury(){
        game.changeProductionInCityAt(new Position(1,1),"bomb");
        goToRound(10);
        assertThat(game.getRoundCount(), is(11));
        assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is("bomb"));
        CityImpl city = (CityImpl) game.getCityAt(new Position(1,1));
        assertThat(city.getTreasury(), is(0));
    }

    @Test
    public void shouldHaveBombDef1AndAtt0(){
        Unit unit = new UnitImplWithBomb(GameConstantsMod.BOMB, Player.RED);

        assertThat(unit.getDefensiveStrength(), is(1));
        assertThat(unit.getAttackingStrength(), is(0));
    }

    @Test
    public void shouldBeSameDefAndAttIntegrated(){
        game.changeProductionInCityAt(new Position(1,1),"bomb");
        goToRound(10);
        Unit unit = game.getUnitAt(new Position(1,1));
        assertThat(unit.getDefensiveStrength(), is(1));
        assertThat(unit.getAttackingStrength(), is(0));
    }

    @Test
    public void shouldDestroyAllUnitsRoundAndItselfWhenBombUseAction(){
        game.changeProductionInCityAt(new Position(1,1), GameConstantsMod.BOMB);
        goToRound(10);

        assertNull(game.getUnitAt(new Position(2,1)));
        game.moveUnit(new Position(1,1), new Position(2,1));

        //units around
        assertThat(game.getUnitAt(new Position(3,1)).getOwner(), is(Player.BLUE));
        assertThat(game.getUnitAt(new Position(3,2)).getOwner(), is(Player.BLUE));
        assertThat(game.getUnitAt(new Position(2,0)).getOwner(), is(Player.RED));

        game.performUnitActionAt(new Position(2,1));
        //no units around
        assertNull(game.getUnitAt(new Position(3,1)));
        assertNull(game.getUnitAt(new Position(3,2)));
        assertNull(game.getUnitAt(new Position(2,0)));
        //and itself are gone
        assertNull(game.getUnitAt(new Position(2,1)));

    }

    public void goToRound(int r){
        for(int i = 0; i < r; i++){
            game.endOfTurn();
            game.endOfTurn();
        }
    }

}
