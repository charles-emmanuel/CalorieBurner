package sport;

import gestionAuthentification.AlertDialogManager;

import gestionAuthentification.SessionManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


import com.calorieburner.R;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Context;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;

import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import chronoFiles.Chronometer;
import dietetique.Dietetique;


public class ActivitesPhysiques extends Activity {
	
	SessionManager session;
	AlertDialogManager alert = new AlertDialogManager();
	Spinner respondForSpinner; 
	ImageButton startBut,pauseBut,stopBut;
	private TextView timeTV;
	String sportName;
	long duringTime=0;
	int respondFor;
	long caloriesBrulees;
	private Chronometer chro;

	public final String userHistory = "history.txt"; //fichier de sauvegarde des résultats
	
	int poids;
	String name;
	TextView essai,test;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activites_physique);
		setupActionBar(); //pour gérer l'action des touches de droite sur le téléphone
		addListenerOnButton();
		
		
		
		
		session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        // name
        name = user.get(SessionManager.KEY_NAME);
        String email = user.get(SessionManager.KEY_POIDS);
        poids=Integer.parseInt(email);     //pour convertir le poids rentré au départ d'un string en int
        chro = (Chronometer) findViewById(R.id.chronometer1);
        timeTV = (TextView) findViewById(R.id.essaitv);

        //déf des param du chrono
        chro.setPlayPauseAlphaAnimation(true);
        chro.setTypeFace(Chronometer.getTypeface_FONT_DUPLEX(this));
        chro.setTextSize(100);
        chro.setTextBold(false);
        
        chro.setTextColor(getResources().getColor(R.color.blue));
        
    	test=(TextView)findViewById(R.id.testsport); 
    	test.setText("On se donne à fond aujourd'hui "+ name + " ,allez c'est parti !!!!!");
    	test.setTextSize(30);
    	test.setTypeface(Chronometer.getTypeface_Fabada(this));
    	//
        
        respondForSpinner = (Spinner)findViewById(R.id.spinnerRespondFor);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sports,R.layout.spinner_row);
    	adapter.setDropDownViewResource(R.layout.spinner_row); //personalisation de la liste déroulante
        respondForSpinner.setAdapter(adapter);
        
        
            

    }
	
//gestion de l'écoute des boutons	
	public void addListenerOnButton() {
		 
		stopBut = (ImageButton) findViewById(R.id.imagestop);
 
		stopBut.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				chro.stop();
			}
 
		});
		
		
		startBut = (ImageButton) findViewById(R.id.imageplay);
		 
		startBut.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				chro.start();
			}
 
		});
		
		
		pauseBut = (ImageButton) findViewById(R.id.imagepause);
		 
		pauseBut.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				chro.pause();
			}
 
		});
		
 
	}
	
	
	
	  private void getMet() {
			
		    int respondForIndex = respondForSpinner.getSelectedItemPosition();
		    sportName=(java.lang.String) respondForSpinner.getSelectedItem();
		    Resources r = getResources();
		    int[] respondForValues = 
		    r.getIntArray(R.array.met);
		    respondFor = respondForValues [respondForIndex];

		  }
	  
	  
	  
	  
	  //pour gérer les retours en arrière
		private void setupActionBar() {

			getActionBar().setDisplayHomeAsUpEnabled(true);

		}
		
		

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.sport_summary, menu);
			return true;
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {
			
			
			case R.id.resetChrono:
				chro.reset(); //pour reseter le chrono en utilisant le menu de droite
		        return true;
			
			
		        
			case R.id.getTime:
				duringTime=chro.duringTime();		
				timeTV.setText(duringTime + "");
				return true;
		        
		        
			case android.R.id.home: //pour repartir en arrière
				Intent intent = new Intent(this, Dietetique.class);
				//intent.putExtra("position", position);
				NavUtils.navigateUpTo(this, intent);
				return true;
			
				
				
			case R.id.menu_save:
				
				//pour récupérer la met et le nom du sport
				String sportName=(java.lang.String) respondForSpinner.getSelectedItem(); //récup du sport dans le spinner test
        		getMet();
		        setResult(RESULT_OK, null);
		        
		    	duringTime=chro.duringTime();		
				
		    	
		    	caloriesBrulees =(duringTime/1000)*poids/1000*respondFor;
		        
				Intent intent1 = new Intent(this, CaloriesSportActivity.class);
				intent1.putExtra("caloriesBrulees", caloriesBrulees); //passage du résultat à la fenêtre d'affichage du bilan
				intent1.putExtra("sportName", sportName); //passage du résultat à la fenêtre d'affichage du bilan
				intent1.putExtra("duringTime", duringTime);
				
				writeToFile(); //pour lancer sauvegarde
				startActivity(intent1); 
				return true;
				
				
				
			case R.id.help:
				
				
				
				
				displayHelp(); 
				
				
				return true;
			
			}
			
			return super.onOptionsItemSelected(item);
		}


		private String getDisplaySummary() {
			String sportName=(java.lang.String) respondForSpinner.getSelectedItem(); //récup du sport dans le spinner
			StringBuilder temp = new StringBuilder();
			temp.append(getTodaysDate());
			temp.append(",");
			temp.append(caloriesBrulees);
			temp.append(",");
			temp.append(name);
			temp.append(",");
			temp.append(poids);
			temp.append(",");
			temp.append(sportName);

			return temp.toString();
		}

		//pour écrire dans le fichier texte
		
		private void writeToFile() {
			try {
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(userHistory, Context.MODE_PRIVATE | Context.MODE_APPEND));
				BufferedWriter bwriter = new BufferedWriter(outputStreamWriter);
				bwriter.write(getDisplaySummary());
				bwriter.newLine();
				bwriter.write("*");
				bwriter.newLine();
				bwriter.close();

			} catch (IOException e) {

			}

		}

		@SuppressLint("SimpleDateFormat")
		private String getTodaysDate() {
			SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy");
			Date now = new Date();
			return formatter.format(now);

		}
		

	  
	  
	  //popup d'aide
	  
	  
		public void displayHelp() {
			
			alert.showAlertDialog(this,Html.fromHtml("<font color='Black'>Aide</font>"), Html.fromHtml("<font color='blue'>" +
					"Sélectionnez un sport , lancez le chronomètre , une fois terminé cliquez sur la flèche en haut à gauche " +
					" les résultats s'affichent </font>"), 3); //chargement de l'alertdialog avec le code 1 
		}
		
		
		
		
		

}
