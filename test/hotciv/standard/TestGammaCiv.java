package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.variants.Age3000BCWinnerStrategy;
import hotciv.standard.variants.LinearAgeStrategy;
import hotciv.standard.variants.SimpleWorldStrategy;
import hotciv.standard.variants.WithActionStrategy;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Yeilloz on 05-03-2017.
 */
public class TestGammaCiv {
    private GameImpl game;

    @Before
    public void setUp(){
        game = new GameImpl(new Age3000BCWinnerStrategy(), new LinearAgeStrategy(), new WithActionStrategy(), new SimpleWorldStrategy());
    }

    @Test
    public void shouldRemoveSettlerAfterPerformedAction(){
        assertThat(game.getUnitAt(new Position(4,3)).getTypeString(), is(GameConstants.SETTLER));

        game.performUnitActionAt(new Position(4,3));

        assertThat(game.getUnitAt(new Position(4,3)), is(nullValue()));
    }

    @Test
    public void shouldBuildACityAfterSettlerPerformedAction(){
        UnitImpl settler = (UnitImpl) game.getUnitAt(new Position(4,3));
        assertThat(settler.getTypeString(), is(GameConstants.SETTLER));
        assertThat(settler.getOwner(), is(Player.RED));

        assertThat(game.getCityAt(new Position(4,3)), is(nullValue()));

        game.performUnitActionAt(new Position(4,3));

        assertThat(game.getCityAt(new Position(4,3)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldDoubleDefForArcherWhenPerformActionFirstTime(){
        UnitImpl archer = (UnitImpl) game.getUnitAt(new Position(2,0));
        assertThat(archer.getTypeString(), is(GameConstants.ARCHER));
        assertThat(archer.getOwner(), is(Player.RED));

        game.performUnitActionAt(new Position(2,0));

        assertThat(game.getUnitAt(new Position(2,0)).getDefensiveStrength(), is(6));
    }

    @Test
    public void shouldHaveArcherNotToMoveWhenFortifyFirstTime(){
        game.performUnitActionAt(new Position(2,0));

        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(), is(0));

        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(), is(0));
    }

    @Test
    public void shouldHaveNormalDefAndMovementAfterSecondTimeFortify(){
        game.performUnitActionAt(new Position(2,0));
        game.performUnitActionAt(new Position(2,0));

        assertThat(game.getUnitAt(new Position(2,0)).getDefensiveStrength(), is(3));

        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(), is(1));

        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(), is(1));
    }

    @Test
    public void shouldOnlyPerformActionOnOwnUnits(){
        game.endOfTurn();

        game.performUnitActionAt(new Position(2,0));

        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(), is(1));
    }
}
