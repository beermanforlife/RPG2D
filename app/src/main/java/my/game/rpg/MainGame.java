package my.game.rpg;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainGame extends Activity {
	public gameView game;
	public MediaPlayer mp;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);




        setContentView(R.layout.game);

        game = (gameView)findViewById(R.id.game_view);
        game.setMode(gameView.RUNNING);

              
    }
    public void onStop(){
    	super.onStop();
    	if(game.music.mp != null){
    	game.music.stop();
		game.music.mp.release();
    	}
    }

}
