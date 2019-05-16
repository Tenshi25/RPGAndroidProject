package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.common.collect.MapMaker;

import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.R;

public class maps_activite  extends FragmentActivity implements GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerClickListener,OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ServiceSuiviGeo.setActiviteMap(this);
        startService(new Intent(this, ServiceSuiviGeo.class));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.zoomTo(18));

    }
    public void MiseAJourCoordonnes() {
        mMap.clear();
        Toast.makeText(this,"lol j'ai bougé",Toast.LENGTH_LONG).show();
        // Add a marker in Sydney and move the camera
        LatLng maPosition = new LatLng(StaticUtilisateurInfo.getInstance().getCoordonnes().getLatitude(), StaticUtilisateurInfo.getInstance().getCoordonnes().getLongitude());
        MarkerOptions maPositionMarker =new MarkerOptions().position(maPosition).title("Me");
        //maPositionMarker.
        mMap.setOnMarkerClickListener(this);
        mMap.addMarker(maPositionMarker);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(maPosition));

        mMap.setOnInfoWindowClickListener(this);


    }
    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this, "j'ai cliker sur un marquer",
                Toast.LENGTH_SHORT).show();
        return true;
    }
}
