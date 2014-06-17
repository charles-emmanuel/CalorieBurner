package dietetique;

import com.calorieburner.R;
//import com.calorieburner.R.drawable;

import android.content.Context;
//import android.graphics.Color;
//import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class RepasAdapter extends BaseAdapter {
	private Context mContext;

	public RepasAdapter(Context c) {
		mContext = c;
	}

	public int getCount() {
		return mThumbIds.length;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

//pour gérer l'affichage des images
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) { 
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(400, 400));
			imageView.setScaleType(ImageView.ScaleType.FIT_XY );
			imageView.setPadding(7, 7, 7, 7);
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(mThumbIds[position]);
		return imageView;
	}

	
	
	
	//références aux images 
	private Integer[] mThumbIds = { R.drawable.ptdj, R.drawable.dejeuner,R.drawable.diner,R.drawable.gouter};
}