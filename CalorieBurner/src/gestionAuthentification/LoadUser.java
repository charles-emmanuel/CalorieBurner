package gestionAuthentification;

import mainApplication.MenuPrincipal;



import com.calorieburner.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoadUser extends Activity {
	EditText txtUsername, txtPassword;
	Button btnLogin;
	CheckBox saveLogin;
	AlertDialogManager alert = new AlertDialogManager();
	SessionManager session;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loaduser);
		 session = new SessionManager(getApplicationContext());      
		
		
		Typeface authentification = Typeface.createFromAsset(getAssets(), "fonts/Top Secret.ttf");
		Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/Comfortaa-Bold.ttf");
	    
	    
	   
	    final EditText  txtPassword = (EditText)findViewById(R.id.poids);
	    txtPassword.setTypeface(myTypeface);
	    
	    final EditText  txtUsername = (EditText)findViewById(R.id.name);
	    txtUsername.setTypeface(myTypeface);
	    
	    
	    Button btnLogin = (Button)findViewById(R.id.loginButton);
	    btnLogin.setTypeface(myTypeface);
	    btnLogin.setTextSize(20);
	    
	    TextView authentif = (TextView)findViewById(R.id.authentif);
	    authentif.setTypeface(authentification);
	    authentif.setTextSize(32);
	    
	    saveLogin = (CheckBox) findViewById(R.id.saveLoginCheckBox);
	    saveLogin.setTypeface(myTypeface);
        btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				
				
				
				String username = txtUsername.getText().toString();
				String password = txtPassword.getText().toString();
				
				int poids =Integer.parseInt(password); //conversion string en int
				
				
				
				
				
				//conditions sur le poids et le nom ,pour ne pas que l'utilisateur ne se connecte pas sans nom				
				if(username.trim().length() > 0 && 40 <= poids && poids <= 150  )  {
 
								
		
					session.createLoginSession(username, password);
					Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
					startActivity(i);
				}
				
					else{
					//affichage de l'alertdialog
						
						alert.showAlertDialog(LoadUser.this,Html.fromHtml("<font color='white'>Oups !!!</font>"), Html.fromHtml("<font color='green'>" +
								"Veuillez renseigner un nom et" +
								" un poids correct! </font>"), 1); //chargement de l'alertdialog avec le code 1 
					}				
				
				
				
			}
		});
	    
	}

	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.load_user, menu);
		return true;
	}

	
	

	
	
	
	
	
}
