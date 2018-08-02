package my.game.rpg;

import android.graphics.Rect;
import android.util.Log;

public class SpriteInfo {

	public Rect sourceOfSheet, drawLocation;
	private long lastUpdateTime;
	private int framesUpdateTimer, framesPerAnimation, currentFrame, currentRow;
	private int spriteWidth, spriteHeight;
	private int whichSpriteX, whichSpriteY;
	
	
	SpriteInfo(){
		spriteWidth = 64;
		spriteHeight = 101;
		lastUpdateTime = 0;
		framesPerAnimation = 3;
		currentFrame = 0;
		framesUpdateTimer = 150;
		currentRow = 0;
		whichSpriteX = 0;
		whichSpriteY = 0;
		//sprite rects for source and destination
		drawLocation = new Rect(0,0, spriteWidth, spriteHeight);
		sourceOfSheet = new Rect(0,0, spriteWidth, spriteHeight);//the default size for current sprite sheet
		
	}
	public void setSpriteSize(int s_h, int s_w){
		spriteHeight = s_h;
		spriteWidth = s_w;
		sourceOfSheet.set(0,0, spriteWidth, spriteHeight);
		drawLocation.set(0,0, spriteWidth, spriteHeight);
	}
	public void setWhichSprite(int ws_x, int ws_y){
		whichSpriteX = ws_x;
		whichSpriteY = ws_y;
        //changeSpriteRow(ws_y);
	}
	private void shiftFrame(){
		int left, right, top, bottom;
		left = (currentFrame * spriteWidth) + (framesPerAnimation * spriteWidth * whichSpriteX);
		right = left + spriteWidth;
		top = (whichSpriteY * spriteHeight);// + (framesPerAnimation * spriteWidth * whichSpriteY);
		bottom = top + spriteHeight;
		sourceOfSheet.set(left, top, right, bottom);
	}
	public void changeSpriteRow(int row){
		currentRow = row;
		shiftFrame();
	}
	public void setDrawLocation(int x, int y){
		drawLocation.set(x,y, x+spriteWidth, y+spriteHeight );
	}
	public void update(long time, Location loc){
		//update time stuff	
		if((lastUpdateTime+=time) > framesUpdateTimer){
			currentFrame++;
			if(currentFrame == framesPerAnimation){
				currentFrame = 0;
			}
			shiftFrame();
			lastUpdateTime = 0;
			//Log.d("Update", "CurrentFrame "+currentFrame);
		}
		//update location
		drawLocation.offsetTo(loc.x, loc.y);
		//drawLocation.set(loc.x, loc.x +spriteWidth, loc.y, loc.y + spriteHeight);

	}
	public int getSpriteWidth(){return spriteWidth;}
	public int getSpriteHeight(){return spriteHeight;}
	public Location getPlayerSpace(){
		Location space;
		space = new Location();
		space.x = spriteWidth/2;
		space.y = spriteHeight/4;
		return space;
	}
}
