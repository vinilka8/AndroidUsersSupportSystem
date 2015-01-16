package ie.gmit.computing;

import java.io.InputStream;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Camera extends Activity implements View.OnClickListener{
	Button em, co, ib;
	ImageView iv;
	Intent i;
	final static int cameraData = 0;
	Bitmap bmp;
	GPSTraker gps;
	//EmailAtt emt = new EmailAtt();
	
	
		
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);
		//run();
	    initialize();
	    InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
	   
	   
	    em = (Button) findViewById(R.id.btnEmail);
	    em.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//em.(EmailAtt.emt);
				Intent intent = new Intent(Camera.this,EmailAtt.class);
        		startActivity(intent);
				
			}
		});
	    
	    co = (Button) findViewById(R.id.btnCoordinate);
	    co.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gps = new GPSTraker(Camera.this);
				if(gps.canGetLocation()){
					double latitude = gps.getLatitude();
					double longitude = gps.getLongitude();

					Toast.makeText(getApplicationContext(), "Your latitude is " + latitude + " and longitude is " + longitude, Toast.LENGTH_LONG).show();
				}else{
					gps.showSettingsAlert();
				}
			}
		});
	    
		
	}
	

	private void initialize() {
		// TODO Auto-generated method stub
		iv = (ImageView) findViewById(R.id.ivReturnPic);
		ib = (Button) findViewById(R.id.ibTakePic);
		ib.setOnClickListener(this);
	}




	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(i, cameraData);
		
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
			// TODO Auto-generated method stub
       /*switch (item.getItemId()) {
	       case android.R.id.home:
	    	   NavUtils.navigateUpFromSameTask(this);
	       return true;
		}*/
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == RESULT_OK)
		{
			Bundle extras = data.getExtras();
			bmp = (Bitmap) extras.get("data");
			iv.setImageBitmap(bmp);
		}
	}
}
