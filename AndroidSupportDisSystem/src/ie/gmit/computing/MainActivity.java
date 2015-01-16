package ie.gmit.computing;

//REQUIRES
//Video 26 - toast smarter 

import java.util.ArrayList;

import com.activeandroid.query.Select;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
	
	
	
	public void gotoActivity(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.btnTree:
			Intent treePage = new Intent(MainActivity.this, TreeStructure.class);
			startActivity(treePage);
	
			break;
			
		case R.id.btnUserTree:
			Intent settingsPage = new Intent(MainActivity.this, UsersDisTree.class);
			startActivity(settingsPage);
			
			break;
		
		case R.id.btnCamera:
			Intent cameraPage = new Intent(MainActivity.this, Camera.class);
			startActivity(cameraPage);
	
			break;
			
		case R.id.btnSett:
			Intent prefs = new Intent(MainActivity.this, SettingsPrefs.class);
			startActivity(prefs);
			
			break;
			
		case R.id.btnUsersGroup:
			Intent groups = new Intent(MainActivity.this, MyGroup.class);
			startActivity(groups);
			
			break;
		}

	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	
}
