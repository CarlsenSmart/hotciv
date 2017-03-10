package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.factories.AlphaCivFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by Yeilloz on 09-03-2017.
 */
public class TestLoggedGame {
    private Game game;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);

// let the receipt print it self

  //  receipt.print(ps);
// get the string printed to the stream
    String output = baos.toString();
// split the string in to individual lines
    String[] lines = output.split( "\n " );

    @Before
    public void setUp(){
        game = new LoggedGame(new GameImpl(new AlphaCivFactory()));
    }

    @Test
    public void shouldLogWhenMoveUnits(){
        game.moveUnit(new Position(2,0), new Position(3,0));
    }

    @Test void shouldLogGetAge(){

    }

}
