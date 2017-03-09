package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.factories.DeltaCivFactory;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Yeilloz on 06-03-2017.
 */
public class TestDeltaCiv {
    GameImpl game;

    @Before
    public void setUp(){
        game = new GameImpl(new DeltaCivFactory());
    }

    @Test
    public void shouldBeTilesAtThesePositions(){
        assertThat(game.getTileAt(new Position(1,2)).getTypeString(), is(GameConstants.PLAINS));
        assertThat(game.getTileAt(new Position(1,3)).getTypeString(), is(GameConstants.HILLS));
        assertThat(game.getTileAt(new Position(4,2)).getTypeString(), is(GameConstants.OCEANS));
        assertThat(game.getTileAt(new Position(0,5)).getTypeString(), is(GameConstants.MOUNTAINS));
        assertThat(game.getTileAt(new Position(9,1)).getTypeString(), is(GameConstants.FOREST));
        assertThat(game.getTileAt(new Position(3,4)).getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void shouldBeRedCityAt8_12(){
        assertThat(game.getCityAt(new Position(8,12)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldBeBlueCityAt4_5(){
        assertThat(game.getCityAt(new Position(4,5)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void shouldBeBlueLegionAt4_4(){
        assertThat(game.getUnitAt(new Position(5,4)).getOwner(), is(Player.BLUE));
        assertThat(game.getUnitAt(new Position(5,4)).getTypeString(), is(GameConstants.LEGION));
    }

    @Test
    public void shouldBeBlueSettlerAt5_5(){
        assertThat(game.getUnitAt(new Position(6,5)).getOwner(), is(Player.BLUE));
        assertThat(game.getUnitAt(new Position(6,5)).getTypeString(), is(GameConstants.SETTLER));
    }

    @Test
    public void shouldBeBlueArcherAt3_8(){
        assertThat(game.getUnitAt(new Position(4,8)).getOwner(), is(Player.BLUE));
        assertThat(game.getUnitAt(new Position(4,8)).getTypeString(), is(GameConstants.ARCHER));
    }
}
