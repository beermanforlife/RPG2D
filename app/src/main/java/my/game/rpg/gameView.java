package my.game.rpg;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class gameView extends View implements OnTouchListener{
	
	//needed for real time updates
	private RefreshHandler _redrawHandler = new RefreshHandler();
	private long _moveDelay = 10;
	//state running or not
	public static final int PAUSE = 0;
    public static final int RUNNING = 1;
    //device height and width
    int s_height, s_width, xOff, yOff;
	//input guide
	public final static int TOUCHD = 1, TOUCHU = 2, TOUCHM = 4, TOUCHNON = 0;
    //the active piece and the board

    //the input variables
	//gameInput gi;

	int t_x, t_y, input;
	boolean touched = false;
	int [] cordDown = new int[2];
	int [] cordUp = new int[2];
	int inputVar = 0;
	//timer stuff
	long now, then , passed, lastDrop, timeToDrop = 5000, changeColor = 250, colorTimer;
	
	musicManager music;
	int currentLevel = 0;
	Bitmap []bmp;
	//States
	gameState gs = new gameState();

	int state = 0;
	static Context m_context;
	
	//remove just for testing
	int rowc = 0;
	int sH, sW;
	
	
		
	public gameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setClickable(true);
		setOnTouchListener(this);
		
		//dim saves the dimensions of the screen to scale things
		Rect dim = new Rect();
        getWindowVisibleDisplayFrame(dim);
        
        //init values
        xOff = yOff = 0;
        
        //setting device height and width
        s_width = dim.width();
        s_height = dim.height();
        //yOff = s_height/10;
        //xOff = s_width/5;

        m_context = context;
        music = new musicManager();
       //music.init(m_context);
        //This scales the loaded bmp based on the correct window size.
        float s_h, s_w;
        bmp = new Bitmap[2];

        bmp[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.units);
        bmp[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.t1);
        
        
        sH = s_height/10;
        sW = s_width/10;
        
        s_h = s_w = 0;
        
        s_h = (bmp[0].getHeight()/4);
        s_h = s_h/sH;
                
        s_w = (bmp[0].getWidth()/12);
        s_w = s_w/sW;
        
        Log.d("Scales", "S_H: " + s_h);

        bmp[0] = Bitmap.createScaledBitmap(bmp[0], (int)(bmp[0].getWidth()/s_w), (int)(bmp[0]
				.getHeight()
				/s_h), true);
        Log.d("Sprite: ", "Height: " + sH + " / " + bmp[0].getHeight());
		bmp[1] = Bitmap.createScaledBitmap(bmp[1], s_width*3, s_width*3, false);
        Log.d("Sprite: ", "Height: " + bmp[1].getWidth() + " / " + bmp[1].getHeight());

        

        //init the game State
		gs.init(dim, bmp, sW, sH, 0);
		gs.setMinions(2, 0);
		//gs.setSpriteSize(sH, sW);
		
		
        
        //init the timer setup
        then = java.lang.System.currentTimeMillis();


        
	}
	public void updateTime(){
		now = java.lang.System.currentTimeMillis();
		passed = now - then;
		then = now;
	}
	class RefreshHandler extends Handler{
		public void handleMessage(Message message){
				gameView.this.update();

				gameView.this.invalidate();
		}
		public void sleep(long delayMillis){
			this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	}

	private void update() {

		gs.update(passed);
		
		//this needs to change but will work for now
	
		//this prevents device from overloading
		updateTime();
		_redrawHandler.sleep(_moveDelay);
    }

    public boolean onTouch(View v, MotionEvent event) {
    	
		input = 0;
		t_x = (int) event.getRawX();
		t_y = (int) event.getRawY();
		/*
    	if(event.getAction() == android.view.MotionEvent.ACTION_DOWN){
    		//check dpad
    		input = gi.handleInput(t_x, t_y);
    		gs.handleGameInput(input);
    		
		 }else*/ if(event.getAction() == android.view.MotionEvent.ACTION_UP){
			//input = gi.buttonPressed(t_x, t_y);
			//input = -1;
			if(input!=-1) {//if there is some input other then default
				gs.handleGameInput(t_x, t_y);
			}
    	}
    	if(event.getAction() == android.view.MotionEvent.ACTION_MOVE){
			//input = gi.handleInput(t_x, t_y);
			//gs.handleGameInput(input);
    	}

        return false;
    }
    public void setMode(int mode) {
        if (mode == RUNNING) {
            update();
            return;
        }
        if (mode == PAUSE) {
            // TODO: implement.
        }
    }


    protected void onDraw(Canvas canvas) {
    	//create paint to use to draw
        Paint paint = new Paint();

        //setup the paint needed to draw stuff
        paint.setColor(Color.rgb(0, 0, 110));
        
        // draw background
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);


        gs.Draw(canvas);
		//gi.drawBattleButtons(canvas, paint);
    }

}
