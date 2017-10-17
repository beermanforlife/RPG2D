package my.game.rpg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class gameInput {
	//sub classes
	class Circle{
		int x, y, r, color;
		public void draw(Canvas c, Paint p){
			p.setColor(color);
			c.drawCircle(x, y, r, p);
		}
	}
	class dPad{
		int color;
		Rect [] directions = new Rect[4];
		public dPad(){	}
		public dPad(Rect dim){
			init(dim);
			setColor(Color.BLACK);
		}
		public void init(Rect dim){//pixel density of screen
			
			//set location
			for(int i = 0; i < 4; i ++){
				directions[i] = new Rect();
				directions[i].left = 0;
				directions[i].right = (dim.width()/20);
				directions[i].top = 0;
				directions[i].bottom = (dim.height()/20);
				switch(i){
				case 0:
					directions[i].offsetTo((int)(dim.width() * .10), (int)(dim.height() * .75));
					break;
				case 1:
					directions[i].offsetTo((int)(dim.width() * .15), (int)(dim.height() * .80));
					break;
				case 2:
					directions[i].offsetTo((int)(dim.width() * .10), (int)(dim.height() * .85));
					break;
				case 3:
					directions[i].offsetTo((int)(dim.width() * .05), (int)(dim.height() * .80));
					break;
								
				
				}
			}
		}
		public int buttonPressed(int x, int y){
			for(int i = 0; i < 4; i++){
				if(directions[i].contains(x, y)){
					return i;
				}
			}
			return -1;
		}
		public void setColor(int c){
			color = c;
		}
		public void draw(Canvas c, Paint p){
			p.setColor(color);
			for(int i = 0; i < 4; i++){
				c.drawRect(directions[i], p);
			}
			
		}
	}
	//variables
	private Circle [] buttons = new Circle[2];
	private dPad dirPad;

	//functions
	public gameInput(){	}
	public gameInput(Rect dim){
		dirPad = new dPad(dim);
	}
	
	public void draw(Canvas c, Paint p){
		dirPad.draw(c, p);
		
		
	}
	public int handleInput(int x, int y){
		//check dpad
		return dirPad.buttonPressed(x, y);
	}
}
