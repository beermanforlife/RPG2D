package my.game.rpg;

import android.graphics.Rect;

public class mapTile extends Entity{
    Rect tile;
    int paths, doors, summonPoints, objectves, vaults, exits;

    mapTile(){
        tile = new Rect();
    }
    public void init(int path, int door, int summons, int obj, int vault, int exit, int tileW,
                     int tileH){
        paths           = path;
        doors           = door;
        summonPoints    = summons;
        objectves       = obj;
        vaults          = vault;
        exits           = exit;
        tile.set(0, 0, tileW, tileH);
    }
}
