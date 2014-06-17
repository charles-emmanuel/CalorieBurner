package dietetique;

import com.calorieburner.R;
//import com.calorieburner.R.layout;
//import com.calorieburner.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HelpApplication extends Activity {
//affichage de l'aide du menu principal
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.helpdietetique);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.help_dietetique, menu);
		return true;
	}

}
