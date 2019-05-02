package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import master.ccm.rpgandroidproject.Entity.Item;
import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.R;

public class MenuPersonnage extends AppCompatActivity {
    private TextView untextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menupersonnage);

        untextView = findViewById(R.id.tv_titreMenuPersonnage);
        untextView.setText(StaticUtilisateurInfo.getInstance().getPersonnageCourant().getNom());
    }

    public void onClickEquipement(View view) {
        Intent monIntent = new Intent (this, page_equipement.class);
        startActivity(monIntent);
        finish();
    }

    public void onClickInventaire(View view) {

        Intent monIntent = new Intent (this, page_inventaire.class);
        startActivity(monIntent);
        finish();
    }

    public void onClickQuitter(View view) {
        finish();
        System.exit(0);
    }

    public void onClickMenuPerso(View view) {
        Intent monIntent = new Intent (this, pageChoixPerso.class);
        startActivity(monIntent);
        finish();
    }
}
