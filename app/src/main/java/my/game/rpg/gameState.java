package my.game.rpg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;

public class gameState {
    //device height and width
    int s_height, s_width, xOff, yOff;
	//input guide
	public final static int TOUCHD = 1, TOUCHU = 2, TOUCHM = 4, TOUCHNON = 0;
    //the input variables
	int [] cordDown = new int[2];
	int [] cordUp = new int[2];
	int inputVar = 0;
	int turn = 0;
	//timer stuff
	long now, then , passed;
	//placing
	gameInput gi;

	int t_x, t_y; 

	
	SpriteSheet sprites;

	Unit player;
	Minion [] minion;
	int target = 0;
	String s = new String();

	public gameState() {
	}
			
	public void init(Rect dimentions, Bitmap bmp, int sp_w, int sp_h, int fildType) {
		//dim saves the dimensions of the screen to scale things
		Rect dim = new Rect();

		dim.set(dimentions);

		//init values
		t_x = t_y = xOff = yOff = 0;

		//setting device height and width
		s_width = dim.width();
		s_height = dim.height();
		gi = new gameInput(s_width, s_height);
		yOff = s_height / 10;
		xOff = s_width / 5;


		sprites = new SpriteSheet(bmp);
		player = new Unit();
		player.setSpriteSize(sprites.getSpriteSheet().getHeight() / 4, sprites.getSpriteSheet().getWidth() / 12);
		player.setWhichSprite(0, 0);
		player.setLoc((s_width / 2) - ((sprites.getSpriteSheet().getWidth() / 12) / 2), (s_height / 2) - (sprites.getSpriteSheet().getHeight() / 4));
		int[] temp = new int[]{10, 2, 1};
		player.setStats(temp);


	}
	public void setMinions(int n_minions, int typeMinions){
		minion = new Minion[n_minions];
		int[] temp = new int[]{10, 2, 1};
		for (int i = 0; i < minion.length; i++) {
			minion[i] = new Minion();
			minion[i].setSpriteSize(sprites.getSpriteSheet().getHeight() / 4, sprites.getSpriteSheet().getWidth() / 12);
			minion[i].setWhichSprite(i+1, 2);
			minion[i].setStats(temp);

			switch (minion.length) {
				case 1:
					minion[i].setLoc((s_width / 2) - ((sprites.getSpriteSheet().getWidth() / 12) / 2),
							(sprites.getSpriteSheet().getHeight() / 4));
					break;
				case 2:
					minion[i].setLoc(((i + 1) * (s_width / 3)) - ((sprites.getSpriteSheet().getWidth() / 12) / 2),
							(sprites.getSpriteSheet().getHeight() / 4));
					break;
				case 3:
					minion[i].setLoc(((i + 1) * (s_width / 4)) - ((sprites.getSpriteSheet().getWidth() / 12) / 2),
							(sprites.getSpriteSheet().getHeight() / 4));
					break;
			}

		}
	}
	public void update(long passed) {
		if(turn != 0){
			minion[turn-1].attackPhysical(player);
			turn++;
			if(turn>minion.length){
				turn=0;
			}
		}
		player.update(passed);
		for(int i =0; i< minion.length; i++){
			minion[i].update(passed);
		}

    }
	public void handleGameInput(int x_location, int y_location){
		int input = -1;
		input = gi.buttonPressed(x_location, y_location);
		if(input ==-1) {

			for(int i = 0; i < minion.length && input ==-1; i ++) {
				if (minion[i].contains(x_location, y_location)) {
					input = i + 10;// doing + 10 to identify input as a selection over a button press. Might want to change in future
				}
			}
		}

		switch(input){
			case 0: player.attackPhysical(minion[target]);turn++;break;
			case 1: player.buffStr(1);turn++;break;
			case 2: player.setBuff_d(1);turn++;break;
			case 10: target = 0;break;
			case 11: target = 1;break;
			case 12: target = 2;break;
		}
		input = -1;
	}

   
    public void Draw(Canvas canvas) {
    	//create paint to use to draw
        Paint paint = new Paint();
		canvas.drawRect(0, 0, s_width, s_height, paint);
		sprites.Draw(canvas, player.getSpriteInfo());

		paint.setARGB(255, 0, 255, 0);
		paint.setTextSize(50);
		canvas.drawText(player.getPrintableHp(), player.getLocaiton().x, player.getLocaiton().y-paint.getTextSize(), paint);
		paint.setColor(Color.YELLOW);
		canvas.drawCircle(minion[target].getLocaiton().x + (minion[target].getSpriteInfo().drawLocation.width()/2),
						minion[target].getLocaiton().y + (float)(minion[target].getSpriteInfo().drawLocation.height()*.75),
						(float)(minion[target].getSpriteInfo().drawLocation.width() * .5), paint);
		paint.setColor(Color.RED);
		for(int i =0; i < minion.length; i++){
			sprites.Draw(canvas, minion[i].getSpriteInfo());
			canvas.drawText(minion[i].getPrintableHp(), minion[i].getLocaiton().x, minion[i].getLocaiton().y-paint.getTextSize(), paint);

		}
		gi.drawBattleButtons(canvas, paint);
	}
}
