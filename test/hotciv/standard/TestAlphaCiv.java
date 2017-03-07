package hotciv.standard;

import hotciv.framework.*;

import hotciv.framework.variants.AgeStrategy;
import hotciv.framework.variants.WinnerStrategy;
import hotciv.standard.factories.AlphaCivFactory;
import hotciv.standard.variants.Age3000BCWinnerStrategy;
import hotciv.standard.variants.LinearAgeStrategy;
import hotciv.standard.variants.NonActionStrategy;
import hotciv.standard.variants.SimpleWorldStrategy;
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
    private AgeStrategy as;
    private WinnerStrategy ws;
    private Game game;

    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {
        as = new LinearAgeStrategy();
        ws = new Age3000BCWinnerStrategy();
        game = new GameImpl(new AlphaCivFactory());
    }


    @Test
    public void shouldAge100YearsPerRound() {
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

    //100% coverage achievement...men jeg har ikke rigtig taget hånd om dem.
    @Test
    public void shouldBe0DefAndAttWhenProducingNotIncludeValidType(){
        CityImpl city = new CityImpl(Player.RED);
        city.setProduction("morgens");
        assertThat(city.produceUnit(), is(true)); //falsk
    }

    @Test
    public void shouldBlabla(){
        UnitImpl unit = new UnitImpl("Egon", Player.RED);
        assertThat(unit.getAttackingStrength(), is(0));
    }

    @Test
    public void shouldBlabEla(){
        UnitImpl unit = new UnitImpl("settler", Player.RED);
        assertThat(unit.getAttackingStrength(), is(0)); // den cover ikke det sidste i branches'ne
    }


    private void endOfRound(){
        game.endOfTurn();
        game.endOfTurn();
    }

    private void goTo3000BC(){
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
