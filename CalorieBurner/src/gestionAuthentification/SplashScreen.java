package gestionAuthentification;

import mainApplication.MenuPrincipal;

import com.calorieburner.R;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

public class SplashScreen extends Activity 
{

  private boolean mIsBackButtonPressed;
  private static final int SPLASH_DURATION = 1000; //6 seconds
  private Handler myhandler;
  SharedPreferences appPref;
	boolean isFirstTime = true;

  public void onCreate(Bundle savedInstanceState) 
  {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_login);
      
		appPref = getSharedPreferences("isFirstTime", 0);
		isFirstTime = appPref.getBoolean("isFirstTime", true);

		if (isFirstTime) {
			//lancement de l'appli quand on clique sur le raccourci
			Intent shortcutIntent = new Intent(getApplicationContext(),
					SplashScreen.class);
			shortcutIntent.setAction(Intent.ACTION_MAIN);
			Intent intent = new Intent();

		
			intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
			intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "CalorieBurner");
			intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
					Intent.ShortcutIconResource.fromContext(
							getApplicationContext(), R.drawable.logo));
			intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
			getApplicationContext().sendBroadcast(intent);
//sharedPreference pour annoncer ou non si il y a déjà un raccourci
			SharedPreferences.Editor editor = appPref.edit();
			editor.putBoolean("isFirstTime", false);
			editor.commit();

		}
      
      
      myhandler = new Handler();
      myhandler.postDelayed(new Runnable() {

          @Override
          public void run() 
          {

             finish();
              
             if (!mIsBackButtonPressed)
             {
                  Intent intent = new Intent(SplashScreen.this, MenuPrincipal.class);
                  SplashScreen.this.startActivity(intent);
             }
               	
          }

      }, SPLASH_DURATION); 
  }
  
 
  @Override
  public void onBackPressed() 
  {
      mIsBackButtonPressed = true;
      super.onBackPressed();
  }
  
	
  
}