package my.game.rpg;

public class Unit extends Entity {
	private Location velocity;
	private int speed;
	private int c_hp, m_hp, str, def;
	
	Unit(){
		type = 1;
		speed = 1;
		velocity = new Location();
		c_hp = m_hp = str = def = 0;
	}
	public void update(long time){
		super.update(time);
		//loc.shiftBy(velocity);
		//sprite.drawLocation.offset(loc.x, loc.y);
	}
	public void handleInput(int input){
		if(input!= -1){
			changeFacing(input);
			velocity.setToZero();
		}
		switch(input){
		case 0:velocity.y -= speed; break;
		case 1:velocity.x += speed; break;
		case 2:velocity.y += speed; break;
		case 3:velocity.x -= speed; break;
		default:
			velocity.setToZero();
		}
	}
	public int getC_hp(){return c_hp;}
	public int getM_hp_hp(){return m_hp;}
	public void setStats(int[] a_stats){
		c_hp = m_hp = a_stats[0];
		str = a_stats[1];
		def = a_stats[2];
	}
	public String getPrintableHp(){
		return c_hp + " / " + m_hp;
	}
}
