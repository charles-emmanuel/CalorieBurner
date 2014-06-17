package sport;

import com.calorieburner.R;

import android.os.Bundle;
import android.app.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import chronoFiles.Chronometer;

public class Chrono extends Activity {
	
	private Button resetBut;
	private Button startBut;
	private Button pauseBut;
	private Button stopBut;
	private Button getTimeBut;
	private TextView timeTV;
	
	private Chronometer chro;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);
        
        resetBut = (Button) findViewById(R.id.reset_but);
        startBut = (Button) findViewById(R.id.start_but);
        pauseBut = (Button) findViewById(R.id.pause_but);
        stopBut = (Button) findViewById(R.id.stop_but);
        chro = (Chronometer) findViewById(R.id.chronometer1);
        getTimeBut = (Button) findViewById(R.id.get_time_but);
        timeTV = (TextView) findViewById(R.id.time_tv);
        
        chro.setPlayPauseAlphaAnimation(true);
        chro.setTypeFace(Chronometer.getTypeface_FONT_DUPLEX(this));
        chro.setTextSize(25);
        chro.setTextBold(false);
        
//      chro.getState();
//      chro.setTextColor();
//      chro.setStartingTime(time);
        
        resetBut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chro.reset();
			}
		});
        
        startBut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chro.start();
			}
		});
        
        pauseBut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chro.pause();
			}
		});
        
        stopBut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chro.stop();
			}
		});
        
        getTimeBut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				timeTV.setText(chro.duringTime() + "");
			}
		});
    }

   
    
}
