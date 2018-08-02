package my.game.rpg;

public class map extends Entity{

    mapTile []tiles;
    int path,  door,  summons,  obj,  vault,  exit,  tileW, tileH;
    map(){
        tiles= new mapTile[9];
        path = door = summons = obj = vault = exit = tileW = tileH = 0;
        initTiles();
    }
    map(int numTiles){
        tiles= new mapTile[numTiles];
        path = door = summons = obj = vault = exit = tileW = tileH = 0;
        initTiles();
    }
    public void initTiles(){
        for(int i = 0; i < tiles.length; i++ ){
            tiles[i] = new mapTile();
            tiles[i].init( path,  door,  summons,  obj,  vault,  exit,  tileW,
             tileH);
        }
    }
}
