package hotciv.standard;

import hotciv.framework.*;

import java.io.PrintStream;

/**
 * Created by Yeilloz on 09-03-2017.
 */
public class LoggedGame implements Game {
    private Game gameLogged;

    public LoggedGame(Game game){
        gameLogged = game;
    }

    @Override
    public Tile getTileAt(Position p) {
        return gameLogged.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return gameLogged.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return gameLogged.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        System.out.print(gameLogged.getPlayerInTurn() + " ");
        return gameLogged.getPlayerInTurn();
    }

    @Override
    public Player getWinner() {
        if(gameLogged.getWinner() == null)
            print("No winner yet");
        else
            print("The winner of the game is " + gameLogged.getWinner());
        return gameLogged.getWinner();
    }

    @Override
    public int getAge() {
        if(gameLogged.getAge() < 0)
            print("The current age is: "+ gameLogged.getAge()+  " BC");
        else
            print("The current age is: "+ gameLogged.getAge() + " AD");
        return gameLogged.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        Unit unit = getUnitAt(from);
        getPlayerInTurn();
        System.out.println("moves " + unit.getTypeString() + " from " + from + " to " + to + ".");
        return gameLogged.moveUnit(from,to);
    }

    @Override
    public void endOfTurn() {
        getPlayerInTurn();
        System.out.println( "ends turn.");
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        print("Not implemented yet");
        gameLogged.changeWorkForceFocusInCityAt(p,balance);

    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        getPlayerInTurn();
        print("has change production to " + unitType + " in city at " + p + ".");
        gameLogged.changeProductionInCityAt(p, unitType);

    }

    @Override
    public void performUnitActionAt(Position p) {
        getPlayerInTurn();
        print("performs action on " + getUnitAt(p) + " in " + p + ".");
        gameLogged.performUnitActionAt(p);
    }

    public void print(String s){
        System.out.println(s);
    }
}
