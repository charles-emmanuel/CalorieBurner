package dietetique;

import java.util.ArrayList;
import java.util.Date;
//d�finition du contexte qui est appel� par la suite dans l'affichage de l'historique
public class FoodHistoryItem {
	Date logDate;
	String totalCalories;
	String nameUser;
	String poids;
	String activite;
	ArrayList<String> foodItemNames = new ArrayList<String>();
	ArrayList<Float> calories = new ArrayList<Float>();

	public FoodHistoryItem(Date logDate, String totalCalories, String nameUser,String poids,String activite, ArrayList<String> foodItemNames, ArrayList<Float> calories) {
		super();
		this.logDate = logDate;
		this.totalCalories = totalCalories;
		this.nameUser= nameUser;
		this.foodItemNames = foodItemNames;
		this.calories = calories;
		this.activite = activite;
		this.poids = poids;
		
	}

}
