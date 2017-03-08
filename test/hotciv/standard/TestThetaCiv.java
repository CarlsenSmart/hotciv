package hotciv.standard;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.factories.ThetaCivFactory;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Yeilloz on 08-03-2017.
 */
public class TestThetaCiv {


    @Test
    public void shouldSelectBombUnit(){
        Game game = new GameImpl(new ThetaCivFactory());
        City city = game.getCityAt(new Position(1,1));
        game.changeProductionInCityAt(new Position(1,1), "bomb");
        assertThat(city.getProduction(), is("bomb"));
    }


    @Test
    public void shouldProduceBombAfter10Rounds(){

    }

}
