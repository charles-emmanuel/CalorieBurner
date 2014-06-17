package mainApplication;

import com.calorieburner.R;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.webkit.WebView;
import android.widget.Toast;

public class FacebookViewer extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.facebookviewer);
		//vérification des paramètres wifi et renvoie d'un message d'erreur si il n'y en a pas
		WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
		int wifiState = wifiManager.getWifiState();
		if (wifiState != WifiManager.WIFI_STATE_ENABLED){
			Toast toast = Toast.makeText(getApplicationContext(), "Wifi Inactif", Toast.LENGTH_LONG);
			toast.show();
		}else{
			Toast toast = Toast.makeText(getApplicationContext(), "Wifi Aactif", Toast.LENGTH_LONG);
			toast.show();
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			String strBSSID = wifiInfo.getBSSID();
			String strIp = String.valueOf(wifiInfo.getIpAddress());
			String strMac = wifiInfo.getMacAddress();
			
		}
		
		WebView webView = (WebView)findViewById(R.id.webView);
		webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		webView.loadUrl("http://www.facebook.fr"); //chargement de la page facebbok
	}
}
