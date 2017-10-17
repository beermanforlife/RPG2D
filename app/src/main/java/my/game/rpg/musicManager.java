package my.game.rpg;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class musicManager {
	Context m_context;
	MediaPlayer mp;
	int currentTrack;
	public musicManager() {
		// TODO Auto-generated constructor stub
	}
	public void init(Context c){
		m_context = c;
		mp = new MediaPlayer();
		currentTrack = 0;
		playMusic(currentTrack);
	}
    public void playMusic(int track) {
    	String trackName; 
    	trackName = "Music"+track+".mp3";
        try {
           
			if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = new MediaPlayer();
            }
            AssetFileDescriptor descriptor = m_context.getAssets().openFd(trackName);
            mp.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            mp.prepare();
            mp.setVolume(1f, 1f);
            mp.setLooping(true);
            mp.start();
        } catch (Exception e) {}
    }
    public void playNextTrack(){
    	currentTrack++;
    	playMusic(currentTrack);
    }
    public void start(){mp.start();}
    public void stop(){mp.stop();}
}
