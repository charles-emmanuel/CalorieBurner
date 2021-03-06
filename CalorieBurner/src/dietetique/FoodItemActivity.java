package dietetique;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import com.calorieburner.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;


public class FoodItemActivity extends ListActivity {

	int position;
	FoodItemAdapter adapter;
	int totalCalories = 0;
	int totalCarbs = 0;
	float totalFat = 0;
	ArrayList<String> indvCal = new ArrayList<String>();
	public final String PREF_NAME = "threshold";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupActionBar();
		position = this.getIntent().getExtras().getInt("position");
		adapter = new FoodItemAdapter(this, readFoodItems(getRepasName(position)));
		setTitle(RepasName.actual_display_name[position]);
		setListAdapter(adapter);
		
		
	}

	private String getRepasName(int position) {
		return RepasName.raw_resource_name[position];
	}
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	public void printItems(ArrayList<FoodItem> fd) {
		System.out.println("Size: " + fd.size());
		for (int i = 0; i < fd.size(); i++) {
			System.out.println(fd.get(i).toString());

		}
	}

	private ArrayList<FoodItem> readFoodItems(String restaurantName) {
		ArrayList<FoodItem> items = new ArrayList<FoodItem>();
		try {
			String foodName = null;
			int calories = 0;
			String servingSize = null;
			float totalFat = 0;
			int totalCarbs = 0;

			FoodItem fd;
			InputStream inputStream = getResources().openRawResource(getResources().getIdentifier(restaurantName, null, this.getPackageName()));
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
			String fileData = null;
			while ((fileData = in.readLine()) != null) {
				foodName = fileData;
				if ((fileData = in.readLine()) != null) {
					servingSize = fileData;
				}
				if ((fileData = in.readLine()) != null) {
					StringTokenizer line = new StringTokenizer(fileData);
					if (line.hasMoreTokens()) {
						///calories = Integer.parseInt((String) line.nextElement());
						//totalFat = Float.parseFloat((String) line.nextElement());
						totalCarbs = Integer.parseInt((String) line.nextElement());
					}
				}

				fd = new FoodItem(foodName, calories, servingSize, totalFat, totalCarbs);
				items.add(fd);

			}
		
			inputStream.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return items;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.fooditem, menu);
		return true;
	}
//gestion des évènements et passages des paramètres
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.menu_go:
			Intent intent = new Intent(FoodItemActivity.this, CaloriesSummaryActivity.class);
			intent.putStringArrayListExtra("foodNames", getSummary());
			intent.putExtra("totalCal", totalCalories);
			intent.putExtra("totalCarbs", totalCarbs);
			intent.putExtra("totalFat", totalFat);
			
			intent.putStringArrayListExtra("indvCalorie", indvCal);
			intent.putExtra("position", position);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private ArrayList<String> getSummary() {
		ArrayList<String> selFoodString = new ArrayList<String>();
		for (FoodItem fd : adapter.getSelectedFoodItemList()) {
			selFoodString.add(fd.foodName);
			totalCalories = totalCalories + fd.calories;
			totalCarbs = totalCarbs + fd.totalCarbs;
			totalFat = totalFat + fd.totalFat;
			indvCal.add(String.valueOf(fd.calories));
		}

		return selFoodString;
	}


}
