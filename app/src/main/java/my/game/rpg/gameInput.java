package my.game.rpg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class gameInput {
	Rect button_area;
	Rect [] battleButtons = new Rect[3];


	public gameInput(int s_w, int s_h){
		button_area = new Rect(0, s_h/2, s_w, s_h);
		for(int i = 0; i < 3; i++){
			battleButtons[i] = new Rect(0,
					s_h/2 + ((2*i)*(button_area.height()/6)),//taking screen height in half, then
					s_w,//taking a 6th of the area and setting the top one full button appart from each other
					(s_h/2 + ((2*i)*(button_area.height()/6))) + (button_area.height()/6));

		}
	}
	public int buttonPressed(int a_x, int a_y){//pass in the x, and y of the screen press
		int isPressed = -1;//set to -1 since there is no -1 in the array

		for(int i = 0; i < battleButtons.length; i++){
			if(a_x >= battleButtons[i].left &&
					a_x <= battleButtons[i].right &&
					a_y >= battleButtons[i].top &&
					a_y <= battleButtons[i].bottom){
				isPressed = i;
			}
		}
		return isPressed;
	}
	public void drawBattleButtons(Canvas ca, Paint pa){
		pa.setColor(Color.RED);
		ca.drawRect(button_area, pa);
		pa.setColor(Color.BLUE);
		for(int i = 0; i < battleButtons.length; i++){
			ca.drawRect(battleButtons[i], pa);
		}
	}

}
