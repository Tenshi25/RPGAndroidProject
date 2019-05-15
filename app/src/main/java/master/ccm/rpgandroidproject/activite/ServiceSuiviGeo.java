package master.ccm.rpgandroidproject.activite;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
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


    public static maps_activite getActiviteMap() {
        return activiteMap;
    }

    public static void setActiviteMap(maps_activite activiteMap) {
        ServiceSuiviGeo.activiteMap = activiteMap;
    }

    @Override
    public void onCreate() {
        Log.i("", "onCreateSuiviGeo");
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.i("RemiLogLongLat", "" + location.getLongitude() + " / " + location.getLatitude());
                StaticUtilisateurInfo.getInstance().getCoordonnes().setLongitude(location.getLongitude());
                StaticUtilisateurInfo.getInstance().getCoordonnes().setLatitude(location.getLatitude());
                activiteMap.MiseAJourCoordonnes();
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
        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, listener);
    }
}
