package ie.gmit.computing;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;

public class GPSTraker extends Service implements LocationListener{
	
	private Context context;
	boolean isGPSenable = false;
	boolean canGetLocation = false;
	double latitude;
	double longitude;
	Location location;
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
	
	protected LocationManager locationManager;
	boolean isNetworkEnable = false;
	
	public GPSTraker(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		getLocation();
	}
	
	
	
	public Location getLocation(){
		try {
			locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
			isGPSenable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			if(!isGPSenable && !isNetworkEnable){
				
			}else{
				this.canGetLocation = true;
				
				if(isNetworkEnable){
					locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
				
				
				if(locationManager != null){
					location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
					
					if(location != null){
						latitude = location.getLatitude();
						longitude = location.getLongitude();
						
					}
				}
					
			}
			
			if(isGPSenable){
				if(location == null){
					locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					
					if(locationManager != null){
						location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
						
						if(location != null){
							latitude = location.getLatitude();
							longitude = location.getLongitude();
						}
					}
				}
			}
		}
	} catch (Exception e){
		e.printStackTrace();
	}
	return location;
}

	public void stopUsingGPS(){
		if(locationManager != null){
			locationManager.removeUpdates(GPSTraker.this);
		}
	}
	
	public double getLatitude(){
		if(location != null){
			latitude = location.getLatitude();
			
		}
		return latitude;
	}
	
	public double getLongitude(){
		if(location != null){
			longitude = location.getLongitude();
			
		}
		return longitude;
	}
	
	public boolean canGetLocation(){
		return this.canGetLocation;
	}
	
	public void showSettingsAlert(){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		alertDialog.setTitle("this is settings");
		alertDialog.setPositiveButton("settings", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				context.startActivity(intent);
			}
		});
		alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
				
			}
		});
		alertDialog.show();
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
