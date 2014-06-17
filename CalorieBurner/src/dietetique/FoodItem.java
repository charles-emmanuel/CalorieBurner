package dietetique;

public class FoodItem {
//gestion de la fen�tre et de la disposition des �l�ments
	String foodName;
	int calories;
	String servingSize;
	float totalFat;
	int totalCarbs;

	public FoodItem() {
	}

	public FoodItem(String name, int cal, String servSize, float totFat, int totCarbs) {
		foodName = name;
		calories = cal;
		totalFat = totFat;
		totalCarbs = totCarbs;
		servingSize=servSize ;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("  " + this.foodName);
		sb.append("\n Nombre de calories: ");
		sb.append(this.totalCarbs);
		sb.append("\n Quantit� : ");
		sb.append(this.servingSize +" gr ");
		return sb.toString();
	}

}
