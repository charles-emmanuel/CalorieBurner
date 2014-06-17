package mainApplication;

import gestionAuthentification.AlertDialogManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;



import com.calorieburner.R;


import dietetique.Dietetique;
import dietetique.FoodHistoryAdapter;
import dietetique.FoodHistoryItem;

import android.annotation.SuppressLint;
import android.app.Activity;


import android.content.Context;

import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.NavUtils;
import android.text.Html;

import android.view.Menu;
import android.view.MenuItem;

import android.widget.ListView;


public class ViewHistoryActivity extends Activity {
	AlertDialogManager alert = new AlertDialogManager();
	InputStream inputStream ;
	public final String userHistory = "history.txt";
	ArrayList<FoodHistoryItem> items = new ArrayList<FoodHistoryItem>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_history);
		setupActionBar();
	
		loadHistory(); //affichage de l'historique des calories consommées ou dépensées

	}

	

	private void loadHistory() {
		FoodHistoryAdapter adapter = new FoodHistoryAdapter(getApplicationContext(), readFoodHistoryItems());
		ListView action_list = (ListView) findViewById(R.id.history_item_list);
		action_list.setAdapter(adapter);
	}

	@SuppressWarnings("finally")
	private List<FoodHistoryItem> readFoodHistoryItems() {
		FoodHistoryItem fd;
		Date logDate = null;
		String totalCalories = null;
		String nameUser = null;
		String poids=null;
		String activite=null;
		
		InputStream inputStream = null;
		try {
			inputStream = openFileInput(userHistory);
			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

				String line = "";
				int count = 0;
				while ((line = bufferedReader.readLine()) != null) {
					if(count > 0){
						// processing
						count++;
						if(isEndOfRecord(line)){
							fd = new FoodHistoryItem(logDate, totalCalories, nameUser,activite, poids,null, null);
							items.add(fd);
							count = 0;
							line = line.substring(1);
						}
					}
					if (count == 0 && !"".equalsIgnoreCase(line) && !isEndOfRecord(line)) {
					StringTokenizer tokens = new StringTokenizer(line, ",");  //pour chercher les différents éléments dans dans le fichier texte qui sont séparés par une  ,
					
						StringBuilder date = new StringBuilder();
						if (tokens.hasMoreTokens()) {
							date.append((String) tokens.nextElement()).append((String) tokens.nextElement());
							logDate = getDate(date.toString().trim());
						}
						if (tokens.hasMoreTokens()) {
							totalCalories = (String) tokens.nextElement();
						}
						if (tokens.hasMoreTokens()) {
							nameUser = (String) tokens.nextElement();
						}
						if (tokens.hasMoreTokens()) {
							poids = (String) tokens.nextElement();
						}
						if (tokens.hasMoreTokens()) {
							activite = (String) tokens.nextElement();
						}
						count++;
					}
					
					
					
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return items;
		}

	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}


	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.layout.menudelete, menu);
		return true;
	}


	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		
		
		

	    case R.id.refreshdata:

	    	startActivity(getIntent());
	        return true;
	        
	        
	    case R.id.deletedata:
	    	
	    	//effacement des donnnées
	    	
			try {
				inputStream = openFileInput(userHistory);
				deleteFile(userHistory);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(userHistory, Context.MODE_PRIVATE | Context.MODE_APPEND));
				BufferedWriter bwriter = new BufferedWriter(outputStreamWriter);
				bwriter.write("file empty"); //pour effacer et remettre une seule donnée dans le fichier
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//affichage de l'alertdialog
	    	alert.showAlertDialog(this,Html.fromHtml("<font color='red'>Attention !</font>"),Html.fromHtml("<font color='red'>Etes-vous sur de bien vouloir effacer les données ?</font>"), 2);
	    	
	        return true;
			
	    case R.id.displaydata:
	    	loadGraphiques();
	        return true;
			
		case android.R.id.home:
			Intent intent = new Intent(ViewHistoryActivity.this, Dietetique.class);
			
			NavUtils.navigateUpTo(this, intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private boolean isEndOfRecord(String receiveString) {
		return ('*' == receiveString.charAt(0));
	}

	@SuppressLint("SimpleDateFormat")
	private Date getDate(String dateString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd MMM yyyy");
		try {
			Date convertedDate = dateFormat.parse(dateString);
			return convertedDate;
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	 
	protected void loadGraphiques() {
	 		Intent intent = new Intent(this, Graph.class);   //pour pouvoir charger les graphiques la définition du type dialog est dans le manifest
	 		startActivity(intent);
	     }	
	  
	}
