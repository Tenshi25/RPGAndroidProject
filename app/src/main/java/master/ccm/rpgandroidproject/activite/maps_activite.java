package master.ccm.rpgandroidproject.activite;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.R;

public class maps_activite  extends FragmentActivity implements GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerClickListener,OnMapReadyCallback {

    private GoogleMap mMap;
    private List<MarkerOptions> listeFeuCamp = new ArrayList<MarkerOptions>();
    private List<MarkerOptions> listeMonstre = new ArrayList<MarkerOptions>();
    private List<MarkerOptions> listeDongeon = new ArrayList<MarkerOptions>();
    private Intent monServiceGeo ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ServiceSuiviGeo.setActiviteMap(this);
        monServiceGeo=new Intent(this, ServiceSuiviGeo.class);
        startService(monServiceGeo);
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
    public void createRandomMonstre(int nbGenerate) {
        for(int i = 1; i <= nbGenerate; i++)
        {
            float lat =getRandomNumberInRange(0,10);
            lat=lat/10000;
            int r =getRandomNumberInRange(0,1);
            if(r == 0){
                lat = -lat;
            }
            float lng =getRandomNumberInRange(0,10);
            lng=lng/10000;
            r =getRandomNumberInRange(0,1);
            if(r == 0){
                lng = -lng;
            }

            //création de monstre

            LatLng positionMonstre = new LatLng( StaticUtilisateurInfo.getInstance().getCoordonnes().getLatitude()+lat,StaticUtilisateurInfo.getInstance().getCoordonnes().getLongitude()+lng);
            MarkerOptions monstreMarker =new MarkerOptions().position(positionMonstre).title("monstre");
            monstreMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_monstre2) );
            listeMonstre.add(monstreMarker);

            mMap.addMarker(monstreMarker);
        }
    }
    public void createRandomTaverne(int nbGenerate) {
        for(int i = 1; i <= nbGenerate; i++)
        {
            float lat =getRandomNumberInRange(0,10);
            lat=lat/10000;
            int r =getRandomNumberInRange(0,1);
            if(r == 0){
                lat = -lat;
            }
            float lng =getRandomNumberInRange(0,10);
            lng=lng/10000;
            r =getRandomNumberInRange(0,1);
            if(r == 0){
                lng = -lng;
            }
            LatLng positionTaverne = new LatLng( StaticUtilisateurInfo.getInstance().getCoordonnes().getLatitude()+lat,StaticUtilisateurInfo.getInstance().getCoordonnes().getLongitude()+lng);
            MarkerOptions taverne =new MarkerOptions().position(positionTaverne).title("Taverne");
            taverne.icon(BitmapDescriptorFactory.fromResource(R.drawable.taverne_icone3) );
            listeFeuCamp.add(taverne);

            mMap.addMarker(taverne);

        }
    }
    public void MiseAJourCoordonnes() {
        mMap.clear();

        Toast.makeText(this,"j'ai bougé",Toast.LENGTH_LONG).show();
        // Add a marker in Sydney and move the camera
        LatLng maPosition = new LatLng(StaticUtilisateurInfo.getInstance().getCoordonnes().getLatitude(), StaticUtilisateurInfo.getInstance().getCoordonnes().getLongitude());
        MarkerOptions maPositionMarker =new MarkerOptions().position(maPosition).title("Me").icon(BitmapDescriptorFactory.fromResource(R.drawable.pointeurpersonnage));
        //
        //maPositionMarker.
        mMap.setOnMarkerClickListener(this);
        mMap.addMarker(maPositionMarker);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(maPosition));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(17));

        mMap.setOnInfoWindowClickListener(this);


        //création de feu de camp
        createRandomMonstre(10);
        createRandomTaverne(5);

        //LatLng positionCamp = new LatLng( 49.56531,3.609383);

        /*mMap.addMarker(new MarkerOptions()
                .position(positionCamp)
                .title("Campement")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_feu_camp2)));*/




    }
    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        boolean contient =contientMarker(listeMonstre,marker);
        Toast.makeText(this, "j'ai cliker sur "+marker.getTitle() +" "+ contient,
                Toast.LENGTH_SHORT).show();
        contientMarker(listeMonstre,marker);

        if(contientMarker(listeMonstre,marker)){
            Toast.makeText(this, "j'ai clicker sur un monstre",
                    Toast.LENGTH_SHORT).show();
            marker.remove();
            Intent combatMonstre = new Intent(this,Combat_activite.class);
            startActivity(combatMonstre);
            marker.remove();

        }
        if(contientMarker(listeFeuCamp,marker)){
            Toast.makeText(this, "j'ai clicker sur une taverne",
                    Toast.LENGTH_SHORT).show();

            Intent taverne = new Intent(this, taverne_activite.class);
            startActivity(taverne);
            stopService(monServiceGeo);
        }
        return true;
    }
    public boolean contientMarker(List<MarkerOptions> listMarkerOptions,Marker leMarkerATrouver) {
        for (MarkerOptions unMarker : listMarkerOptions)
        {
            /*
            if(unMarker.getTitle().equals(leMarkerATrouver.getTitle())){
                return true;
            }*/

            if(unMarker.getPosition().equals(leMarkerATrouver.getPosition())){
                return true;
            }
        }

        return false;
    }

    public void StopActivite() {
        stopService(monServiceGeo);
        finish();
    }
    public static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
