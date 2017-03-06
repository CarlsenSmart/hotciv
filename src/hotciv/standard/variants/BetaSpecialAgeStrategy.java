package hotciv.standard.variants;

import hotciv.framework.variants.AgeStrategy;

/**
 * Created by Yeilloz on 04-03-2017.
 */
public class BetaSpecialAgeStrategy implements AgeStrategy {

    public int calculateAgeing(int worldAge){

        if(worldAge>=1970)
            worldAge += 1;
        else if(worldAge>=1900)
            worldAge += 5;
        else if(worldAge>=1750)
            worldAge += 25;
        else if(worldAge >= 50)
            worldAge += 50;

        switch (worldAge){
            case -100: worldAge = -1;
                break;
            case -1: worldAge = 1;
                break;
            case 1: worldAge = 50;
        }
        if(worldAge<-100)
            worldAge += 100;

        return worldAge;
    }
}
