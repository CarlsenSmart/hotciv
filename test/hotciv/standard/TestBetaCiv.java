package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.variants.AgeStrategy;
import hotciv.framework.variants.WinnerStrategy;
import hotciv.standard.variants.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Yeilloz on 05-03-2017.
 */
public class TestBetaCiv {
    private AgeStrategy as;
    private WinnerStrategy ws;
    private Game game;

    @Before
    public void setUp(){
        as = new BetaSpecialAgeStrategy();
        ws = new ConquerAllCitiesWinnerStrategy();
        game = new GameImpl(new ConquerAllCitiesWinnerStrategy(), new BetaSpecialAgeStrategy(),
        new NonActionStrategy(), new SimpleWorldStrategy());
    }

    @Test
    public void shouldAge100YearsFrom4000BCTo(){
        assertThat(as.calculateAgeing(-3100), is(-3000));
        assertThat(as.calculateAgeing(-2100), is(-2000));
        assertThat(as.calculateAgeing(-1100), is(-1000));
    }

    @Test
    public void shouldAgeFrom100BCTo1BCTo1ADTo50AD(){
        assertThat(as.calculateAgeing(-200), is(-100));
        assertThat(as.calculateAgeing(-100), is(-1));
        assertThat(as.calculateAgeing(-1), is(1));
        assertThat(as.calculateAgeing(1), is(50));
    }

    @Test
    public void shouldAge50YearsPerRoundFrom50ADTo1750AD(){
        assertThat(as.calculateAgeing(50), is(100));
        assertThat(as.calculateAgeing(100), is(150));
        assertThat(as.calculateAgeing(650), is(700));
        assertThat(as.calculateAgeing(1700), is(1750));
    }

    @Test
    public void shouldAgeBy25From1750ADTO1900AD(){
        assertThat(as.calculateAgeing(1750), is(1775));
        assertThat(as.calculateAgeing(1775), is(1800));
        assertThat(as.calculateAgeing(1875), is(1900));
    }

    @Test
    public void shouldAgeBy5From1900To1970(){
        assertThat(as.calculateAgeing(1900), is(1905));
        assertThat(as.calculateAgeing(1905), is(1910));
        assertThat(as.calculateAgeing(1910), is(1915));
        assertThat(as.calculateAgeing(1915), is(1920));
        assertThat(as.calculateAgeing(1965), is(1970));
    }

    @Test
    public void shouldAgeBy1After1970(){
        assertThat(as.calculateAgeing(1970), is(1971));
        assertThat(as.calculateAgeing(1980), is(1981));
    }

    @Test
    public void shouldIntegrateBetaSpecialAgeCorrectly(){
        goTo100BC();

        assertThat(game.getAge(), is(-100));

        endOfRound();
        assertThat(game.getAge(), is(-1));

        endOfRound();
        assertThat(game.getAge(), is(1));

        endOfRound();
        assertThat(game.getAge(), is(50));
    }

    @Test
    public void shouldBeRedWinConqueredBluesCities(){

        assertThat(game.getWinner(), is(nullValue()));
        game.moveUnit(new Position(2,0), new Position(3,0));

        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getWinner(), is(nullValue()));
        game.moveUnit(new Position(3,0), new Position(4,1));

        assertThat(game.getWinner(), is(Player.RED));
    }



    private void endOfRound(){
        game.endOfTurn();
        game.endOfTurn();
    }

    private void tenEndOfRounds(){
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

    private void goTo100BC(){
        tenEndOfRounds();
        tenEndOfRounds();
        tenEndOfRounds(); //1000BC
        endOfRound();
        endOfRound();
        endOfRound();
        endOfRound();
        endOfRound(); // 500BC
        endOfRound();
        endOfRound();
        endOfRound();
        endOfRound(); // 100BC
    }
}
