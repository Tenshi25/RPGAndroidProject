package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import master.ccm.rpgandroidproject.R;

public class pageAjoutPersonnage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_ajout_personnage);
    }

    public void onClickRetour(View view) {
        Intent monIntent = new Intent (this, pageAccueil.class);
        startActivity(monIntent);
    }

    public void onClickCreerPersonnage(View view) {

    }
}
