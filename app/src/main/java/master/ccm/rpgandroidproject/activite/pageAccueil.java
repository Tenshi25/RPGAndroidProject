package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.R;


public class pageAccueil extends AppCompatActivity {
    private TextView untextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_accueil);
        untextView = findViewById(R.id.tv_message);
        //StaticUtilisateurInfo unUtilisateurStatic = new StaticUtilisateurInfo();
        String chaineBienvenue ="bienvenue : "+StaticUtilisateurInfo.getInstance().getNom();
        //Log.i("setInfo", StaticUtilisateurInfo.getInstance().getNom());
        untextView.setText(chaineBienvenue);
    }

    public void onConnectDisconnect(View view) {
        StaticUtilisateurInfo.getInstance().setId(null);
        StaticUtilisateurInfo.getInstance().setNom(null);
        Intent monIntent = new Intent (this, MainActivity.class);
        startActivity(monIntent);

    }
}
