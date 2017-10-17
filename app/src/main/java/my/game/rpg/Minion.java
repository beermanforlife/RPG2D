package my.game.rpg;

public class Minion extends Unit{

	int lastUpdate = 0;
	int timeToUpdate, moveDir;
	
	
	public Minion(){
		timeToUpdate = 2000;
		moveDir = 0;
	}
	
	public void update(long time){
		super.update(time);
		//wander(time);

	}
	public void wander(long time){
		if ((lastUpdate += time) > timeToUpdate){
			moveDir++;
			
			if(moveDir > 3){
				moveDir=0;
			}
			handleInput(moveDir);
			lastUpdate = 0;
		}
		
		
	}
}
