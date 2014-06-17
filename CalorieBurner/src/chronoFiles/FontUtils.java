package chronoFiles;

import android.content.Context;
import android.graphics.Typeface;
//gestion des différentes polices d'affichage du chronomètre
public class FontUtils {

	public static final String FONT_BREAKDOWN = "fonts/digitalism.ttf";
	public static final String FONT_DUPLEX = "fonts/digitalism.ttf";
	public static final String SQUID_REGULAR = "fonts/digitalism.ttf";
	public static final String SQUID_SMALL_CAPS = "fonts/digitalism.ttf";
	public static final String VTKS_UNTITLED = "fonts/digitalism.ttf";
	public static final String N_GAGE = "fonts/digitalism.ttf";
	public static final String Fabada = "fonts/Fabada-regular.ttf";

	public static Typeface getTypeface(Context context, String typeFaceName) {
		return Typeface.createFromAsset(context.getAssets(), typeFaceName);
	}

}
