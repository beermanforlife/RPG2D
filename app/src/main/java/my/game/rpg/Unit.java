package my.game.rpg;

public class Unit extends Entity {
	private Location velocity;
	private int speed;
	private int c_hp, m_hp, str, def, buff_d, buff_s;
	
	Unit(){
		buff_d = buff_s = 0;
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
	public void buffStr(int b_s){
		buff_s += b_s;
	}
	public void setBuff_d(int b_d){buff_d = b_d;}
	public String getPrintableHp(){
		return c_hp + " / " + m_hp;
	}
	public int getStr(){return (str + buff_s);}
	public int getDef(){return (def + buff_d);}
	//public int getStr(){return str;}
	public void attackPhysical(Unit a_unit){
		//this will change with more stats and equipment but for now
		int dmg = 0;

		dmg = (str + buff_s) - a_unit.getDef(); //get differnet in str and a_unit def
		if (dmg < 0){dmg =0;} // if def is higher set to 0 so no damage is done
		else {a_unit.modifyCHp(dmg);} // apply damamge



	}
	public void modifyCHp(int a_dmg){
		c_hp = c_hp - a_dmg;
	}
}
