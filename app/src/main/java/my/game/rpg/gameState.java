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
	//timer stuff
	long now, then , passed;
	//placing

	int t_x, t_y; 

	
	SpriteSheet sprites;

	Unit player;
	String s = new String();

	public gameState() {

		
		
       
	}
			
	public void init(Rect dimentions, Bitmap bmp, int sp_w, int sp_h){
		//dim saves the dimensions of the screen to scale things
		Rect dim = new Rect();

		dim.set(dimentions);
		        
		        //init values
		t_x = t_y = xOff = yOff = 0;
		        
		        //setting device height and width
		s_width = dim.width();
		s_height = dim.height();
		yOff = s_height/10;
		xOff = s_width/5;

		        
		sprites = new SpriteSheet(bmp);
		player = new Unit();
		player.setSpriteSize(sprites.getSpriteSheet().getHeight()/4, sprites.getSpriteSheet().getWidth()/12);
		player.setWhichSprite(1,2);
		player.setLoc(s_width/2, s_height/4);
		int[] temp = new int[]{10,1,1};
		player.setStats(temp);


        //entity2.setLoc(s_width/2, s_height/3);
		        //init the timer setup
	}

	public void update(long passed) {

		player.update(passed);
    }
	public void handleGameInput(int input){
		
			//entity.changeFacing(input);
		player.handleInput(input);
		
		
		
	}

   
    public void Draw(Canvas canvas) {
    	//create paint to use to draw
        Paint paint = new Paint();
		canvas.drawRect(0, 0, s_width, s_height, paint);
		sprites.Draw(canvas, player.getSpriteInfo());
		paint.setARGB(255, 0, 255, 0);
		paint.setTextSize(50);
		canvas.drawText(player.getPrintableHp(), player.getLocaiton().x, player.getLocaiton().y-paint.getTextSize(), paint);
    }
}
