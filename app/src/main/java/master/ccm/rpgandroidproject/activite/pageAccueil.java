package master.ccm.rpgandroidproject.activite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import master.ccm.rpgandroidproject.Entity.Utilisateur;
import master.ccm.rpgandroidproject.R;
import master.ccm.rpgandroidproject.manager.BDDManager;

public class pageAccueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_accueil);
    }
}
