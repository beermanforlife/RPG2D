package my.game.rpg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.Button;

public class gameInput {
	Rect button_area;
	Rect [] battleButtons = new Rect[3];
	int buttonPadding = 2;

	public gameInput(int s_w, int s_h){
		button_area = new Rect(0, 4*(s_h/5), s_w, s_h);


		//Gives 3 buttons at bottom of screen equil spacing
		for(int i = 0; i < 3; i++){
			battleButtons[i] = new Rect((i*(s_w/3))+buttonPadding,//left of the buttons based on 1/3 screen
					button_area.top + buttonPadding,//top of the buttons
					(i*(s_w/3)+(s_w/3))-buttonPadding,//right of the buttons
					(s_h-buttonPadding));//bottom of button

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
