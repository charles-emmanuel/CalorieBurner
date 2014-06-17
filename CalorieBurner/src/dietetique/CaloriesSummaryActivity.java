package dietetique;

import gestionAuthentification.SessionManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import mainApplication.FacebookViewer;
import mainApplication.MenuPrincipal;
import com.calorieburner.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class CaloriesSummaryActivity extends Activity {
	Spinner respondForSpinner; 
	String name;

	int weigth=67;
	int respondFor=0;
	
	int poids;
	int totalCalories = 0;
	int totalCarbs = 0;
	float totalFat = 0;
	
	
	public final String PREF_NAME = "threshold";
	public final String userHistory = "history.txt";
	
	ArrayList<String> selectedFoodItems;
	ArrayList<String> selectedCal;
	int timer=0; //
	SessionManager session;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calories_summary);
		
		
		session = new SessionManager(getApplicationContext());
		// get user data from session
        HashMap<String, String> user = session.getUserDetails();
        // name
        name = user.get(SessionManager.KEY_NAME);
        // email
        String email = user.get(SessionManager.KEY_POIDS);
        poids=Integer.parseInt(email);
		setupActionBar(); //gestion des icônes dans la barre d'action en haut 
		selectedCal = this.getIntent().getExtras().getStringArrayList("indvCalorie");
		totalCalories = this.getIntent().getExtras().getInt("totalCal");
		totalCarbs = this.getIntent().getExtras().getInt("totalCarbs");
		totalFat = this.getIntent().getExtras().getFloat("totalFat");
		
		selectedFoodItems = this.getIntent().getExtras().getStringArrayList("foodNames");


		
		FoodNameAdapter adapter = new FoodNameAdapter(getApplicationContext(), selectedFoodItems);
		ListView food_list = (ListView) findViewById(R.id.selected_fooditem);
		
		food_list.setAdapter(adapter);

		TextView cal = (TextView) findViewById(R.id.total_calories);
		cal.setTextSize(15);
		cal.setText("Total Calories : " + String.valueOf(totalCarbs) + "\n");
/*
		TextView carbs = (TextView) findViewById(R.id.total_carbs);
		carbs.setTextSize(15);
		carbs.setText("Total Carbs : " + String.valueOf(totalCarbs) + "\n");

		TextView fat = (TextView) findViewById(R.id.total_fat);
		fat.setTextSize(15);
		fat.setText("Total Fat : " + String.valueOf(totalFat) + "\n");
		
		*/
		
		
		 respondForSpinner = (Spinner)findViewById(R.id.spinnerRespondForDiet);
	     ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.sports, android.R.layout.simple_spinner_item);
	     adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	     respondForSpinner.setAdapter(adapter1);
	        
	        
	        
	        
	        
	
	}
	
	
	
	  private void getMet() {
			
			
		    int respondForIndex = respondForSpinner.getSelectedItemPosition();
		    Resources r = getResources();
		    int[] respondForValues = 
		    r.getIntArray(R.array.met);
		    respondFor = respondForValues [respondForIndex];

		  }
	  
	  
	

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.calories_summary, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(CaloriesSummaryActivity.this, Dietetique.class);
			writeToFile();
			NavUtils.navigateUpTo(this, intent);
			return true;
		
		
		
			
				
		case R.id.sportdietetique:
			writeToFile();
    		getMet(); //récup de la met pour le calcul du timer 
	        setResult(RESULT_OK, null);
	        timer=(totalCarbs*1000)/(respondFor*poids)+10; //formule de calcul pour retrouver le temps nécessaire au chrono
			Intent intent2 = new Intent(CaloriesSummaryActivity.this, DietetiquePhysique.class); //démarrage de l'activité diététique sportive
			intent2.putExtra("time", timer); //passage de la valeur du chrono en paramètres
			startActivity(intent2);
			return true;
			
			
			
		case R.id.savedata:
			
			writeToFile();
			Intent k = new Intent(this, MenuPrincipal.class); //retour au menu principal
			startActivity(k);
			return true;
			
			
			
		case R.id.sharedata:
			
			
			Intent l = new Intent(this, FacebookViewer.class); //retour au menu principal
			startActivity(l);
			return true;
			
		}
		return super.onOptionsItemSelected(item);
	}

//ajout des éléments dans le fichier txt qui seront lus après  
	
	private String getDisplaySummary() {
		StringBuilder temp = new StringBuilder();
		temp.append(getTodaysDate());
		temp.append(",");
		temp.append(totalCarbs);
		temp.append(",");
		temp.append(name);
		temp.append(",");
		temp.append(poids);
		temp.append(",");
		temp.append("Dietetique");
		

		return temp.toString();
		

		
	}

	//écriture des données
	private void writeToFile() {
		try {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(userHistory, Context.MODE_PRIVATE | Context.MODE_APPEND));
			BufferedWriter bwriter = new BufferedWriter(outputStreamWriter);
			bwriter.write(getDisplaySummary());
			bwriter.newLine();
			int i = 0;
			StringBuilder temp;
			while (i < selectedFoodItems.size()) {
				temp = new StringBuilder();
				temp = temp.append(selectedFoodItems.get(i));
				temp = temp.append(",");
				temp = temp.append(selectedCal.get(i));
				i++;
				bwriter.write(temp.toString());
				bwriter.newLine();
			}
			bwriter.write("*");
			bwriter.newLine();
			bwriter.close();
		} catch (IOException e) {

		}

	}

	@SuppressLint("SimpleDateFormat") //récup de la date
	private String getTodaysDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy");
		Date now = new Date();
		return formatter.format(now);

	}
	
	
	
	protected void loadSport() {
 		Intent intent = new Intent(this, DietetiquePhysique.class);    //pour pouvoir charger l'activité sport
 		startActivity(intent);
     }	
	
	

}
