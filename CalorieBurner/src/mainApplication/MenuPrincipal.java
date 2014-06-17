package mainApplication;





import gestionAuthentification.AlertDialogManager;
import gestionAuthentification.LoadUser;
import gestionAuthentification.SessionManager;

import java.util.HashMap;
import sport.ActivitesPhysiques;
import com.calorieburner.R;
import dietetique.Dietetique;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;




public class MenuPrincipal extends Activity   {

	SessionManager session;
	
	AlertDialogManager alert = new AlertDialogManager();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuprincipal);
		
		
		session = new SessionManager(getApplicationContext());
        addListenerOnButton(); //démarrage de l'action des boutons
        
        HashMap<String, String> user = session.getUserDetails(); //récupérer les données de l'utilisateurs
        String name = user.get(SessionManager.KEY_NAME);
        this.setTitle( name); //pour avoir l'affichage du bonjour en haut à gauche
		
	}
	
	
	
	
	//fonctions d'écoute sur les images et les boutons
	
		public void addListenerOnButton() {

		ImageButton imageDietetique = (ImageButton) findViewById(R.id.imagedietetique);

		  imageDietetique.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {

					loadDietetique();

				}

			});


			ImageButton imageSport = (ImageButton) findViewById(R.id.imagesport);

			 imageSport.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {

					loadSport();

				}

			});
			
			
			
			Button sport = (Button) findViewById(R.id.sport);
			  sport.setOnClickListener(new OnClickListener() {
						
			  @Override
			  public void onClick(View v) {
				  
				loadSport();
				
				}
			});
			
				
				Button dietetetique = (Button) findViewById(R.id.dietetique);
				dietetetique.setOnClickListener(new OnClickListener() {
							
				  @Override
				  public void onClick(View v) {
					  
					  loadDietetique();	
					  
					  }
				});		
			
				
				
				Button stat = (Button) findViewById(R.id.historique);
				stat.setOnClickListener(new OnClickListener() {
							
				  @Override
				  public void onClick(View v) {
					  
					  loadHistory();	
					  
					  }
				});	
		
		}
		
		
		
		
		//fonctions de chargement des activités
		
		 protected void loadDietetique() {
		 		Intent intent = new Intent(this, Dietetique.class);   //pour pouvoir charger l'activité diététique
		 		startActivity(intent);
		     }	
		 	 

		 protected void loadSport() {
		 		Intent intent = new Intent(this, ActivitesPhysiques.class);    //pour pouvoir charger l'activité sport
		 		startActivity(intent);
		     }	
		
		 protected void loadHistory() {
		 		Intent intent = new Intent(this, ViewHistoryActivity.class);    //pour pouvoir charger l'historique
		 		startActivity(intent);
		     }	
		 
		 
		  protected void loadHelp() {
				Intent intent = new Intent(this, Help.class); //pour pouvoir charger l'aide

				startActivity(intent);
			
		  }
		  
		  
		  
		  protected void dataUser() {
				Intent intent = new Intent(this, LoadUser.class); //pour pouvoir charger les profils utilisateurs

				startActivity(intent);
			
		  }
		  
		  
		  
		  protected void writeEmail() {
				Intent intent = new Intent(this, Mail.class); //pour pouvoir charger les profils utilisateurs

				startActivity(intent);
			
		  }
		  
		  


		 
		 
	
		 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		
		case R.id.profil:
			dataUser() ; //lancement des profils
	        return true;
		

		case R.id.help:
			loadHelp(); //lancement de l'aide
	        return true;
	        
	        
	        
		case R.id.mail:
			writeEmail(); //lancement du client messagerie
	        return true;
	        
	        
		case R.id.action_settings:
			writeEmail(); //lancement de la création du raccourci
	        return true;
	        

	        
	        

		}
		
		
		
		return super.onOptionsItemSelected(item);
	}
	

}
