package my.game.rpg;

public class Location {
	public int x, y;
	public Location(){x=0;y=0;}
	public void setToZero(){x = y = 0;}
	public void shiftBy(Location a_loc){
		x += a_loc.x;
		y += a_loc.y;
	}
	public void checkMaxSpeed(int speed){
		if(y == 0 && x < 0)
			x = -speed;
		if(x == 0 && y < 0)
			y = -speed;
		if(y == 0 && x > 0)
			x = speed;
		if (x == 0 && y > 0)
			y = speed;
	}
}
