package master.ccm.rpgandroidproject.activite;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;

public class ServiceSuiviGeo extends Service {
    public ServiceSuiviGeo() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    private LocationListener listener;
    private LocationManager locationManager;
    private static maps_activite activiteMap = null;


    @Override
    public void onCreate() {
        Log.i("", "onCreateSuiviGeo");
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.i("RemiLogLongLat", "" + location.getLongitude() + " / " + location.getLatitude());
                StaticUtilisateurInfo.getInstance().getCoordonnes().setLongitude(location.getLongitude());
                StaticUtilisateurInfo.getInstance().getCoordonnes().setLatitude(location.getLatitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
    }
}
