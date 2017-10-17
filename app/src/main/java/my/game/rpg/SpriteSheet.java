package my.game.rpg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class SpriteSheet {
	private Bitmap spriteSheet;
	
	public SpriteSheet(Bitmap sheet){
		
		setSpriteSheet(Bitmap.createScaledBitmap(sheet, sheet.getWidth(), sheet.getHeight(), true));
	}
	
	public void Draw(Canvas canvas, SpriteInfo spriteI) {
    	//create paint to use to draw
        Paint paint = new Paint();

		canvas.drawBitmap(spriteSheet, spriteI.sourceOfSheet, spriteI.drawLocation , paint);
		
    }
	
	public Bitmap getSpriteSheet() {
		return spriteSheet;
	}
	public void setSpriteSheet(Bitmap spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
}
