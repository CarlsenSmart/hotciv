package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.variants.AgeStrategy;
import hotciv.standard.factories.EpsilonCivFactory;
import hotciv.standard.factories.SemiCivFactory;
import hotciv.standard.variants.BetaCiv.BetaSpecialAgeStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yeilloz on 08-03-2017.
 */
public class TestSemiCiv {
    private GameImpl game;
    @Before
    public void setUp(){
        game = new GameImpl(new SemiCivFactory(new FixedDieStrategy(6,1)));
    }

    @Test
    public void shouldAgeFrom100BCTo1BCTo1ADTo50AD(){
        goToRound(39);
        assertThat(game.getAge(), is(-100));
        goToRound(1);
        assertThat(game.getAge(), is(-1));
        goToRound(1);
        assertThat(game.getAge(), is(1));
        goToRound(1);
        assertThat(game.getAge(), is(50));
    }

    @Test
    public void shouldRemoveSettlerAfterPerformedAction(){
        assertThat(game.getUnitAt(new Position(6,5)).getTypeString(), is(GameConstants.SETTLER));

        game.performUnitActionAt(new Position(6,5));

        assertThat(game.getUnitAt(new Position(6,5)), is(nullValue()));
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




    public void goToRound(int r){
        for(int i = 0; i < r; i++){
            game.endOfTurn();
            game.endOfTurn();
        }
    }
}
