package gestionAuthentification;


import com.calorieburner.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Spanned;
 
public class AlertDialogManager {
 //classe générique pour la gestion des messages d'alertes   
    @SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, Spanned spanned2, Spanned spanned,
            int status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(spanned2);
        alertDialog.setMessage(spanned);
        //gestion des différentes options pour les alertDialog (1 2 3)
        if(status ==1 )
        alertDialog.setIcon(R.drawable.team );
        if(status == 2)
        	alertDialog.setIcon(R.drawable.ic_action_warning );
        if(status == 3)
        	alertDialog.setIcon(R.drawable.ic_action_help );
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
 
        alertDialog.show(); //affichage du message d'erreur
    }
}