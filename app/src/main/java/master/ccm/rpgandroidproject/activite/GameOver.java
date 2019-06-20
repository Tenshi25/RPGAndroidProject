package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.R;
import master.ccm.rpgandroidproject.manager.BDDManager;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        BDDManager bddManager = new BDDManager();
        bddManager.SupprPersonnage(StaticUtilisateurInfo.getInstance().getPersonnageCourant());


        //maMap.StopActivite();
    }

    public void onClickRetour(View view) {
        Intent monIntent = new Intent(this, pageChoixPerso.class);
        startActivity(monIntent);
        finish();
    }
}
