package dietetique;


import com.calorieburner.R;
import mainApplication.MenuPrincipal;
import mainApplication.ViewHistoryActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;


public class Dietetique extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupActionBar();

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new RepasAdapter(this));

		
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Intent intent = new Intent(Dietetique.this, FoodItemActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}
		});
		


	}

	

	
	   
	   // fonction de lancement des autres activités
	   
	   
	   protected void displayHistorique() {
	        startActivity(new Intent(this, ViewHistoryActivity.class));
	    }

		private void setupActionBar() {

			getActionBar().setDisplayHomeAsUpEnabled(true);

		}
		
		

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}

		
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {
			
			case android.R.id.home:
				Intent intent = new Intent(Dietetique.this, MenuPrincipal.class);
				NavUtils.navigateUpTo(this, intent);
				return true;
				
			
			case R.id.helpdietetique:
				alertBox(); //lancement de l'aide
		        return true;
		        

			}
			
			return super.onOptionsItemSelected(item);
		}
		
		
		
		
		// alertbox d'aide
		
		public void alertBox() {

			
				
			
			new AlertDialog.Builder(Dietetique.this)
					.setTitle("Petit conseil :)")
					.setMessage("Commencez par sélectionner votre repas en faisant dérouler la liste d'images.")
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					}).show();
		}
		
		
		

		
}
