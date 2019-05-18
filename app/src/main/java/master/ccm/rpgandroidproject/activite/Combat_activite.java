package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.R;

public class Combat_activite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat_activite);
    }

    public void onClickFuir(View view) {
        this.finish();
    }

    public void onClickAttaquer(View view) {
        int pointDegat = 4;
        StaticUtilisateurInfo.getInstance().getPersonnageCourant().pertePv(pointDegat);
        Toast.makeText(this, "Vous avez perdu "+ pointDegat + " point de vie", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Vous avez "+StaticUtilisateurInfo.getInstance().getPersonnageCourant().getPv() + " point de vie", Toast.LENGTH_LONG).show();

    }
}
