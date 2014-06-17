package sport;



import mainApplication.MenuPrincipal;
import mainApplication.ViewHistoryActivity;



import com.calorieburner.R;


import dietetique.Dietetique;


import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;

public class CaloriesSportActivity extends Activity {

	//int position;
	//int respondFor = 0;
	String sportName;
	int weigth=67;
	long duringTime; //récup de l'heure du chrono
	long caloriesBrulees;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calories_sport);
		
		// Show the Up button in the action bar.
		setupActionBar();

		// Select position from previous activity and bring that menu here
		//position = this.getIntent().getExtras().getInt("position");

		duringTime = this.getIntent().getExtras().getLong("duringTime"); //recup des param du chrono
		

		
		
		sportName = this.getIntent().getExtras().getString("sportName");
		//respondFor = this.getIntent().getExtras().getInt("respondFor");
		caloriesBrulees = this.getIntent().getExtras().getLong("caloriesBrulees");

	
		//pour faire la conversion ms en h min et s
		
		int h = (int) ((duringTime / 1000) / 3600);
		int m = (int) (((duringTime / 1000) / 60) % 60);
		int s = (int) ((duringTime / 1000) % 60);
		
		
		TextView cal = (TextView) findViewById(R.id.total_calories);
		cal.setText("Calories brûlées : " + String.valueOf(caloriesBrulees) + "\n");
		
		TextView duration = (TextView) findViewById(R.id.duration);
		duration.setText("Durée de l'activité : " +	h +" H "+ m +" minutes "+" et " + s + " s "+"\n");
		
			
		
		//TextView met1 = (TextView) findViewById(R.id.total_met);
		//met1.setText("met: " + String.valueOf(respondFor) + "\n");
		
		TextView sport = (TextView) findViewById(R.id.sport_name);
		sport.setText("Activitée pratiquée : " + String.valueOf(sportName) + "\n");
		

		

		

	
	}
	

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.calories_sport, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		

	     
				case android.R.id.home: //pour repartir en arrière
					Intent intent = new Intent(this, Dietetique.class);
					//intent.putExtra("position", position);
					NavUtils.navigateUpTo(this, intent);
					return true;
					
					
		case R.id.action_settings: //pour repartir en arrière
			Intent i = new Intent(this, MenuPrincipal.class);
			startActivity(i);
			return true;
			
			
		case R.id.stat: //pour repartir en arrière
			Intent j = new Intent(this, ViewHistoryActivity.class);
			startActivity(j);
			return true;
	
		}
		
		
		
		
		
		return super.onOptionsItemSelected(item);
	}

	
}
