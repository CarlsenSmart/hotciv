package hotciv.standard;

import hotciv.framework.Tile;

/**
 * Created by Yeilloz on 03-03-2017.
 */
public class TileImpl implements Tile {

    private String type;

    public TileImpl(String type) {
        this.type = type;
    }

    @Override
    public String getTypeString() {
        return type;
    }
}
