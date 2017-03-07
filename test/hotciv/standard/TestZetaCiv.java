package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.factories.ZetaCivFactory;
import hotciv.standard.variants.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Yeilloz on 07-03-2017.
 */
public class TestZetaCiv {
    private Game game;

    @Before
    public void setUp(){
        game = new GameImpl(new ZetaCivFactory());
    }

    @Test
    public void shouldIntegrateBetaCivsWinnerStrategyThroughGamma(){
        //no winner yet
        assertThat(game.getWinner(), is(nullValue()));
        //move red archer to 3_0
        game.moveUnit(new Position(2,0), new Position(3,0));

        game.endOfTurn();
        game.endOfTurn();

        //still no winner
        assertThat(game.getWinner(), is(nullValue()));

        //archer attacks and win city
        game.moveUnit(new Position(3,0), new Position(4,1));

        //We now have red as winner
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldHaveRoundCountInitialToBe1(){
        GameImpl newGame = (GameImpl) game;
        assertThat( newGame.getRoundCount(), is(1));
    }

    @Test
    public void shouldHaveRoundCountIncreaseAfterTwoEndOfTurns(){
        GameImpl newGame = (GameImpl) game;
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        assertThat(newGame.getRoundCount(), is(2));
        game.endOfTurn();
        assertThat(newGame.getRoundCount(), is(3));
    }

    @Test
    public void shouldResetAttacksWonForBothRedAndBlueAfterRound20(){

        shipRounds(19);
        //move red archer to 3_0
        game.moveUnit(new Position(2,0), new Position(3,0));

        GameImpl newGame = (GameImpl) game;
        assertThat(newGame.getRedWins(), is(1));
        assertThat(newGame.getRoundCount(), is(20));

        newGame.endOfTurn();
        newGame.endOfTurn();
        assertThat(newGame.getRoundCount(), is(21));
        assertThat(newGame.getRedWins(), is(0));
    }

    @Test
    public void shouldBeThreeWinsWinnerStrategyAfterRound20(){

        //round 1
        shipRounds(17); // blue's city is full for archers
        // round 18
        GameImpl g = (GameImpl) game;
        GameImpl game = g;

        game.moveUnit(new Position(2,0), new Position(3,0));
        assertThat(game.getRedWins(), is(1));

        game.endOfTurn();
        game.endOfTurn(); //round 19

        game.moveUnit(new Position(3,0), new Position(4,0));
        assertThat(game.getRedWins(), is(2));

        game.endOfTurn();
        game.endOfTurn(); // round 20

        //red's third kill
        game.moveUnit(new Position(4,0), new Position(5,0));
        assertThat(game.getRedWins(), is(3));
        assertNull(game.getWinner());

        game.endOfTurn();
        game.endOfTurn(); // round 21

        assertThat(game.getRedWins(), is(0));

        // red has all cities
        game.moveUnit(new Position(5,0),new Position(4,1));
        assertNull(game.getWinner());

        // red has kill kills again and wins
        game.moveUnit(new Position(2,0),new Position(3,0));
        game.moveUnit(new Position(2,1),new Position(3,1));

        assertThat(game.getRedWins(), is(3));
        assertThat(game.getWinner(), is(Player.RED));



    }

    public void shipRounds(int r){
        for(int i = 0; i < r; i++){
            game.endOfTurn();
            game.endOfTurn();
        }
    }

}
