package gestionAuthentification;

import java.util.HashMap;



import android.annotation.SuppressLint;
import android.content.Context;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

@SuppressLint("CommitPrefEdits")
public class SessionManager {
	
	SharedPreferences pref;
	Editor editor;
	Context _context;
	int PRIVATE_MODE = 0;
	private static final String PREF_NAME = "AndroidHivePref";
	public static final String KEY_NAME = "name";
	public static final String KEY_POIDS = "poids";
	public SessionManager(Context context){
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}
	
//session
	public void createLoginSession(String name, String poids){
		editor.putString(KEY_NAME, name);
		editor.putString(KEY_POIDS, poids);
		editor.commit();
	}	
	

	
	
//stockage des données
	
	public HashMap<String, String> getUserDetails(){
		HashMap<String, String> user = new HashMap<String, String>();
		user.put(KEY_NAME, pref.getString(KEY_NAME, "entrez votre nom"));
		user.put(KEY_POIDS, pref.getString(KEY_POIDS, null));
		return user;
	}
	

	
}
