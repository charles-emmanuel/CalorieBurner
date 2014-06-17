package dietetique;

import gestionAuthentification.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.calorieburner.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FoodHistoryAdapter extends ArrayAdapter<FoodHistoryItem> {
	SessionManager session;
	public static final String PREFS_NAME = "MyPrefsFile";
	private final Context context;
	private final List<FoodHistoryItem> foodHistoryItems;
	String name;
	

	public FoodHistoryAdapter(Context context, List<FoodHistoryItem> itemsList) {
		super(context, R.layout.activity_indv_foodhistoryitem, itemsList);
		this.context = context;
		this.foodHistoryItems = itemsList;
		session = new SessionManager(context);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		FoodHistoryItem item = foodHistoryItems.get(position);

		View food_history_view = inflater.inflate(R.layout.activity_indv_foodhistoryitem, parent, false);

		TextView date = (TextView) food_history_view.findViewById(R.id.date);
		date.setText(getDate(item.logDate));

		TextView total_history_calories = (TextView) food_history_view.findViewById(R.id.total_history_calories);
		total_history_calories.setText(String.valueOf(item.totalCalories) + " cal"+"\n"+String.valueOf(item.poids));


		TextView username = (TextView) food_history_view.findViewById(R.id.total_history_price);
		username.setText(String.valueOf(item.nameUser)+"\n"+String.valueOf(item.activite)+"kg"); //affichage de l'utilisateur sur l'historiquue

		return food_history_view;

	}
	
	
	@SuppressLint("SimpleDateFormat")
	private String getDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM");
		return formatter.format(date);

	}
	

}
