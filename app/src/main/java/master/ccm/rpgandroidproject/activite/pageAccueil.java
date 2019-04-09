package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.R;


public class pageAccueil extends AppCompatActivity {
    private TextView untextView;


    // on s'occupe de la liste view

    private ListView maListPersonnage;
    private String[] tableauChaines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_accueil);
        untextView = findViewById(R.id.tv_message);
        //StaticUtilisateurInfo unUtilisateurStatic = new StaticUtilisateurInfo();
        String chaineBienvenue ="bienvenue : "+StaticUtilisateurInfo.getInstance().getNom();
        //Log.i("setInfo", StaticUtilisateurInfo.getInstance().getNom());
        untextView.setText(chaineBienvenue);
        maListPersonnage = (ListView) findViewById(R.id.id_maliste);


        //recupéré une ressource

        /*tableauChaines =getResources().getStringArray(R.array.tableau_de_chaines);*/

        //adapter fais le lien entre la liste et le tableau de chaine
        /*ArrayAdapter<String> monArrayAdapter=new ArrayAdapter(this,R.layout.descripteur_de_ligne,R.id.tv_nom_perso,tableauChaines);
        maListView.setAdapter(monArrayAdapter);*/
    }

    public void onConnectDisconnect(View view) {
        StaticUtilisateurInfo.getInstance().setId(null);
        StaticUtilisateurInfo.getInstance().setNom(null);
        Intent monIntent = new Intent (this, MainActivity.class);
        startActivity(monIntent);

    }

    public void onClickNouveauPersonnage(View view) {
        Intent monIntent = new Intent (this, pageAjoutPersonnage.class);
        startActivity(monIntent);
    }
}
