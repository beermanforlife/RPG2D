package my.game.rpg;

public class Entity {
	protected Location loc = new Location();
	protected SpriteInfo sprite;
	private int facing = 0;//0=up, 1=right, 2=down, 3=left
	protected int type = 0;//to identify type of entity 
	
	
	public Location getLocaiton(){return loc;}
	public SpriteInfo getSpriteInfo(){ return sprite;}
	
	
	Entity(){
		loc.x = loc.y = 200;
		sprite = new SpriteInfo();
	}
	public void setSpriteSize(int s_h, int s_w){
		sprite.setSpriteSize(s_h, s_w);
	}
	public void setLoc(int ax, int ay){
		loc.x = ax;
		loc.y = ay;
	}
	public void update(long time){
		sprite.update(time, loc);
	}
	public void changeFacing(int cFacing){
		facing = cFacing;
		sprite.changeSpriteRow(facing);
	}
	public void setWhichSprite(int wsX, int wsY){
		sprite.setWhichSprite(wsX, wsY);
	}
}
