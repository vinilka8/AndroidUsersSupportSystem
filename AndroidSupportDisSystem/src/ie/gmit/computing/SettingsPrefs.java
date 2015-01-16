package ie.gmit.computing;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SettingsPrefs extends Activity{
	TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prefs);
		Button btnPrefs = (Button) findViewById(R.id.btnPrefs);
	    Button btnGetPrefs = (Button) findViewById(R.id.btnGetPreferences);
	   
	    textView = (TextView) findViewById(R.id.txtPrefs);
   
   		View.OnClickListener listener = new View.OnClickListener() {
			   @Override
			   public void onClick(View v) {
			   switch (v.getId()) {
			   case R.id.btnPrefs:
			      Intent intent = new Intent(SettingsPrefs.this,Settings.class);
			      startActivity(intent);
			      break;
			   case R.id.btnGetPreferences:
			      displaySharedPreferences();
			      break;
			 
			   default:
			     break;
			   }
		   }
	   };
	   
	   btnPrefs.setOnClickListener(listener);
	   btnGetPrefs.setOnClickListener(listener);
		
	}

	protected void displaySharedPreferences() {
		// TODO Auto-generated method stub
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SettingsPrefs.this);
			 
		String scientificname = prefs.getString("scientificname", "Default Name");
		String shipname = prefs.getString("shipname", "Default Ship name");
		String email = prefs.getString("email", "Default email");
		String phoneNum = prefs.getString("phoneNum", "Default email");
		
		boolean checkBox = prefs.getBoolean("checkBox", false);
		String listPrefs = prefs.getString("listpref", "Default list prefs");
	 
		StringBuilder builder = new StringBuilder();
		builder.append("Scientistic name: " + scientificname + "\n");
		builder.append("Ship name: " + shipname + "\n");
		builder.append("Email: " + email + "\n");
		builder.append("Phone number: " + phoneNum + "\n");
		builder.append("Keep me logged in: " + String.valueOf(checkBox) + "\n");
		builder.append("List preference: " + listPrefs);
	 
		textView.setText(builder.toString());
	
	}

}
