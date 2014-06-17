package dietetique;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import chronoFiles.Chronometer;
import chronoFiles.Countdown;
import com.calorieburner.R;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DietetiquePhysique extends Activity {
	private Button startButton, pauseButton, resetButton;
	private Countdown countdown;
	private TextView countdownTextView;
	private static MediaPlayer airhorn  = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dietetiquephysique);
		
		
		  addListenerOnButton();
		  countdownTextView = (TextView) findViewById(R.id.countdown);
		  countdownTextView.setTextColor(getResources().getColor(R.color.blue));
		  countdownTextView.setTypeface(Chronometer.getTypeface_FONT_DUPLEX(this)); //réutilisation de la même police d'écriture que le chrono
		  countdownTextView.setTextSize(100);
		 
	      int time = this.getIntent().getExtras().getInt("time");  //récup du timing de l'activité précédente

	        
	      countdown = new Countdown(this, countdownTextView, 0, time);
		   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.dietetique_physique, menu);
		return true;
	}
	
	
	
	
	public void addListenerOnButton() {
		 
		 pauseButton = (Button) findViewById(R.id.pausecdt);

		 pauseButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				countdown.pause();
			}

		});
		
		
		startButton = (Button) findViewById(R.id.startcdt);
		 
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				countdown.start();
			}

		});
		
		
		resetButton = (Button) findViewById(R.id.resetcdt);
		 
		resetButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				playFinishedSound();
				finish();
			}

		});
		
		
		
		
		
		

	}
	
	protected void playFinishedSound() {
		airhorn = MediaPlayer.create(this, R.raw.airhorn);
        playSound(airhorn);
    }
	
	
	private void playSound(MediaPlayer mp) {
        mp.seekTo(0);
        mp.start();
    }
	
	
	
	
	
	
	

}
