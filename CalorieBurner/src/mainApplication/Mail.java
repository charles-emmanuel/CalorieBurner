package mainApplication;

import com.calorieburner.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Mail extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        
        final EditText etSujet = (EditText) findViewById(R.id.etSujet);
        final EditText etMsg = (EditText) findViewById(R.id.etMsg);
        ImageButton ibSend = (ImageButton) findViewById(R.id.ibSend);
        ibSend.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View arg0) {
        		
        		String strSujet = etSujet.getText().toString();
        		String strMsg = etMsg.getText().toString();
        		Intent emailIntent = new  Intent(Intent.ACTION_SEND);
        		emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{  "contact@calorieburner.com"}); //destinataire du message
        		emailIntent.putExtra(Intent.EXTRA_SUBJECT,  strSujet);
        		emailIntent.putExtra(Intent.EXTRA_TEXT,  strMsg);
        		startActivity(Intent.createChooser(emailIntent, "Client messagerie ? "));
        	}
        });
	}
}