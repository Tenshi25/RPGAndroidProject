package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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

import master.ccm.rpgandroidproject.Entity.Location;
import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.R;

public class maps_activite  extends FragmentActivity implements GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerClickListener,OnMapReadyCallback {

    private GoogleMap mMap;
    private List<MarkerOptions> listeTaverne = new ArrayList<MarkerOptions>();
    private List<MarkerOptions> listeMonstre = new ArrayList<MarkerOptions>();
    private List<MarkerOptions> listeDongeon = new ArrayList<MarkerOptions>();
    private Intent monServiceGeo ;
    private TextView tv_pv,tv_Nom;
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
        tv_pv= findViewById(R.id.tv_pv2);
        tv_Nom =findViewById(R.id.tv_nomPerso);
        String NomPrenom =StaticUtilisateurInfo.getInstance().getPersonnageCourant().getNom() +" " +StaticUtilisateurInfo.getInstance().getPersonnageCourant().getPrenom();
        tv_Nom.setText(NomPrenom) ;



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
        MiseAJourCoordonnes();
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

            //mMap.addMarker(monstreMarker);
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
            listeTaverne.add(taverne);

            //mMap.addMarker(taverne);

        }
    }
    public void MiseAJourCoordonnes() {
        mMap.clear();

        //SupprimerLieuMonstreLointain();
        //Toast.makeText(this,"j'ai bougé",Toast.LENGTH_LONG).show();
        // Add a marker in Sydney and move the camera
        tv_pv.setText(String.valueOf(StaticUtilisateurInfo.getInstance().getPersonnageCourant().getPv())) ;
        LatLng maPosition = new LatLng(StaticUtilisateurInfo.getInstance().getCoordonnes().getLatitude(), StaticUtilisateurInfo.getInstance().getCoordonnes().getLongitude());
        MarkerOptions maPositionMarker =new MarkerOptions().position(maPosition).title("Me").icon(BitmapDescriptorFactory.fromResource(R.drawable.pointeurpersonnage));
        //
        //maPositionMarker.
        mMap.setOnMarkerClickListener(this);



        mMap.setOnInfoWindowClickListener(this);

        // Remettre les taverne et les monstre qui existe déja
        //createRandomTaverne(5);
        //


        int nombreaCree = 0;
       if(listeTaverne.size()<5){
            nombreaCree = 5- listeTaverne.size();
            //Toast.makeText(this,"nbCree Taverne : "+ nombreaCree,Toast.LENGTH_SHORT).show();
            if(nombreaCree != 0){
                createRandomTaverne(nombreaCree);
            }

           //Toast.makeText(this,"Creation taverne",Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this,"Il y a "+ listeTaverne.size() +" tavernes.",Toast.LENGTH_LONG).show();


        nombreaCree = 0;
        if(listeMonstre.size()<10){
            nombreaCree = 10 - listeMonstre.size();
            //Toast.makeText(this,"nbCree Monstre : "+ nombreaCree,Toast.LENGTH_SHORT).show();
            if(nombreaCree != 0) {
                createRandomMonstre(nombreaCree);
            }
            //Toast.makeText(this,"Creation monstre",Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this,"Il y a "+ listeMonstre.size()+" Monstre.",Toast.LENGTH_LONG).show();

        reactualisationTavmons();
        mMap.addMarker(maPositionMarker);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(maPosition));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(18));
        //LatLng positionCamp = new LatLng( 49.56531,3.609383);

        /*mMap.addMarker(new MarkerOptions()
                .position(positionCamp)
                .title("Campement")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_feu_camp2)));*/



        //SupprimerLieuMonstreLointain();
    }

    private void reactualisationTavmons() {
        for (MarkerOptions unMonstre : listeMonstre)
        {
            //Toast.makeText(this,"reactualisationTav Monstre.",Toast.LENGTH_LONG).show();

            /*LatLng positionMonstre = new LatLng(unMonstre.getPosition().latitude,unMonstre.getPosition().longitude);
            MarkerOptions monstreMarker =new MarkerOptions().position(positionMonstre).title("monstre");
            monstreMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_monstre2) );
            listeMonstre.add(monstreMarker);*/
            Location maLocation = new Location (StaticUtilisateurInfo.getInstance().getCoordonnes().getLatitude(),StaticUtilisateurInfo.getInstance().getCoordonnes().getLongitude());
            Location monstreLoc = new Location(unMonstre.getPosition().latitude, unMonstre.getPosition().longitude);
/*
            if (maLocation.getDistanceWithOtherPosition(monstreLoc) > 500) {
                listeMonstre.remove(unMonstre);
                //Toast.makeText(this,"Il y a "+ listeMonstre.size()+" Monstre.",Toast.LENGTH_LONG).show();
            }else{
            mMap.addMarker(unMonstre);
            }*/
            mMap.addMarker(unMonstre);
        }
        for (MarkerOptions uneTaverne : listeTaverne)
        {
            /*LatLng positionTaverne = new LatLng(uneTaverne.getPosition().latitude,uneTaverne.getPosition().longitude);
            MarkerOptions TaverneMarker =new MarkerOptions().position(positionTaverne).title("monstre");
            TaverneMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.taverne_icone3) );
            listeMonstre.add(TaverneMarker);*/

            //Toast.makeText(this,"reactualisationTav Taverne.",Toast.LENGTH_LONG).show();
            mMap.addMarker(uneTaverne);
        }


    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Location maLoc = StaticUtilisateurInfo.getInstance().getCoordonnes();
        Location makerLoc = new Location(marker.getPosition().longitude,marker.getPosition().latitude);
        double DistanceMetre =maLoc.getDistanceWithOtherPosition(makerLoc);
        if(DistanceMetre < 40 ){
            /*oolean contient =contientMarker(listeMonstre,marker);
            Toast.makeText(this, "j'ai cliker sur "+marker.getTitle() +" "+ contient,
                    Toast.LENGTH_SHORT).show();*/
            contientMarker(listeMonstre,marker);

            if(contientMarker(listeMonstre,marker)){
                Toast.makeText(this, "Vous rencontrai un monstre !",
                        Toast.LENGTH_SHORT).show();
                marker.remove();
                Intent combatMonstre = new Intent(this,Combat_activite.class);
                startActivity(combatMonstre);
                marker.remove();

            }
            if(contientMarker(listeTaverne,marker)){
                Toast.makeText(this, "Vous entré dans une taverne",
                        Toast.LENGTH_SHORT).show();

                Intent taverne = new Intent(this, taverne_activite.class);
                startActivity(taverne);
                //stopService(monServiceGeo);
            }


        }else{
            Toast.makeText(this, "Vous êtes trop loin.",
                    Toast.LENGTH_SHORT).show();
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

    public void onClickMenu(View view) {
        Intent menu = new Intent(this, MenuPersonnage.class);
        startActivity(menu);
        //stopService(monServiceGeo);
        finish();
    }

    public void onClickRecenter(View view) {
        //MiseAJourCoordonnes();
        //Location maLoc = StaticUtilisateurInfo.getInstance().getCoordonnes();

        LatLng maPosition = new LatLng(StaticUtilisateurInfo.getInstance().getCoordonnes().getLatitude(), StaticUtilisateurInfo.getInstance().getCoordonnes().getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng( maPosition));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(18));
    }

    public void SupprimerLieuMonstreLointain() {

        Location maLocation = new Location(StaticUtilisateurInfo.getInstance().getCoordonnes().getLatitude(), StaticUtilisateurInfo.getInstance().getCoordonnes().getLongitude());


        for (MarkerOptions unMonster : listeMonstre) {
            Location monstreLoc = new Location(unMonster.getPosition().latitude, unMonster.getPosition().longitude);

            if (maLocation.getDistanceWithOtherPosition(monstreLoc) > 500) {
                listeMonstre.remove(unMonster);
                //Toast.makeText(this,"Il y a "+ listeMonstre.size()+" Monstre.",Toast.LENGTH_LONG).show();
            }
        }

        if (listeTaverne.size() != 0) {
            for (MarkerOptions uneTaverne : listeTaverne) {
                if (listeMonstre.size() != 0) {
                    Location taverneLoc = new Location(uneTaverne.getPosition().latitude, uneTaverne.getPosition().longitude);
                    if (maLocation.getDistanceWithOtherPosition(taverneLoc) > 1000) {
                        listeTaverne.remove(uneTaverne);
                    }

                }
            }

        }
    }
}
