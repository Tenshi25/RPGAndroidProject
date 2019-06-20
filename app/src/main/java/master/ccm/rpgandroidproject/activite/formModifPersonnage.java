package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.R;
import master.ccm.rpgandroidproject.manager.BDDManager;

public class formModifPersonnage extends AppCompatActivity {
    private EditText champNomModif;
    private EditText champPrenomModif;
    private String idPersonnage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_modif_personnage);
        champNomModif =findViewById(R.id.id_nomModifPerso);
        champPrenomModif= findViewById(R.id.id_prenomModifPerso);
        Intent intent = getIntent();

        idPersonnage = intent.getStringExtra("idPersonnage");
        champPrenomModif.setText(intent.getStringExtra("prenomPersonnage"));
        champNomModif.setText(intent.getStringExtra("nomPersonnage"));
    }

    public void onclickRetour(View view) {
        Intent monIntent = new Intent (this, pageChoixPerso.class);
        startActivity(monIntent);
        finish();
    }

    public void onClickConfirmModif(View view) {

        BDDManager bddManager = new BDDManager();
        Personnage unPersonnage = new Personnage();
        unPersonnage.setNom(champNomModif.getText().toString());
        unPersonnage.setPrenom(champPrenomModif.getText().toString());
        unPersonnage.setId(idPersonnage);
        bddManager.ModifPersonnage(unPersonnage, this);
    }
    public void ModifSucess() {
        Toast.makeText(this,"Le personnage "+champNomModif.getText().toString()+"a été modifier", Toast.LENGTH_SHORT);
        Intent monIntent = new Intent (this, pageChoixPerso.class);
        startActivity(monIntent);
        finish();
    }

}
