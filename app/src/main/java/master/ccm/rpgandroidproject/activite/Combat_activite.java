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
        StaticUtilisateurInfo.getInstance().getPersonnageCourant().pertePv(4);
        Toast.makeText(this, "Vous avez "+StaticUtilisateurInfo.getInstance().getPersonnageCourant().getPv(), Toast.LENGTH_LONG).show();
    }
}
