package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import master.ccm.rpgandroidproject.Entity.Groupe;
import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.R;
import master.ccm.rpgandroidproject.manager.DonjonManager;

public class formCreateGroupeActivity extends AppCompatActivity {
    EditText et_nomGroupe;
    private DonjonManager DM= new DonjonManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_create_groupe);
    }

    public void onClick_createGroupe(View view) {
        DonjonManager DM=new DonjonManager();
        et_nomGroupe = findViewById(R.id.editText_nomGroupe);
        DM.InsertDatastoreGroupe(StaticUtilisateurInfo.getInstance().getPersonnageCourant(),et_nomGroupe.getText().toString(),this);
    }

    public void onClickRetour(View view) {
        finish();
    }

    public void InsertSuccess(Groupe groupe) {
        //DM.UpdateNbPerso(this,groupe,1);
        DM.InsertDatastorePersonnageGroupe(StaticUtilisateurInfo.getInstance().getPersonnageCourant(),groupe,this);
        Toast.makeText(this,"Le groupe à été ajouter ! ",Toast.LENGTH_LONG);
        Intent listGroupeView = new Intent (this,GroupeViewActivity.class);
        listGroupeView.putExtra("idGroupe",groupe.getId());
        listGroupeView.putExtra("nbPersoGroupe",groupe.getNbPerso());
        listGroupeView.putExtra("nomGroupe",groupe.getNomGroupe());
        startActivity(listGroupeView);
        finish();
    }
}
