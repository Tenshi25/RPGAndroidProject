package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import master.ccm.rpgandroidproject.Entity.Groupe;
import master.ccm.rpgandroidproject.Entity.PersonnageGroupe;
import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.R;
import master.ccm.rpgandroidproject.manager.DonjonManager;

public class GroupeViewActivity extends AppCompatActivity {
    private PersonnageGroupe[] tabPersonnageGroupe;
    private ListView maListPersonnageGroupes;
    private List<PersonnageGroupe> listePersonnageGroupe;
    private DonjonManager DM= new DonjonManager();
    private  Groupe leGroupe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupe_view);
        Intent intent = getIntent();

        if (intent.hasExtra("idGroupe")){ // vérifie qu'une valeur est associée à la clé “edittext”
            String idGroupe = "";
            idGroupe = intent.getStringExtra("idGroupe"); // on récupère la valeur associée à la clé
            if (intent.hasExtra("nomGroupe")){
                String nomGroupe = "";
                nomGroupe = intent.getStringExtra("nomGroupe"); //
                if (intent.hasExtra("nbPersoGroupe")) {
                    int nbPersoGroupe = 0;
                    nbPersoGroupe = intent.getIntExtra("nbPersoGroupe",0);

                    leGroupe = new Groupe(idGroupe, nomGroupe, nbPersoGroupe);
                    Log.i("leGroupe","groupe : "+ leGroupe.getId() +" / "+leGroupe.getNomGroupe() +" / "+leGroupe.getNbPerso());
                }
            }

        }
        maListPersonnageGroupes = findViewById(R.id.liste_personnage_groupe);

        DM.selectAllPersoGroups(this);
    }

    public void onClickCommencerCombat(View view) {
        Intent formCreateGroupe= new Intent(this,combatBossActivity.class);
        startActivity(formCreateGroupe);
    }

    public void onClickRetour(View view) {
        selectGroupById(leGroupe);
        Log.i("leGroupe","groupe : "+ leGroupe.getId() +" / "+leGroupe.getNomGroupe() +" / "+leGroupe.getNbPerso());
        DM.UpdateNbPerso(this,leGroupe,-1);
        DM.SupprPersonnageGroups(StaticUtilisateurInfo.getInstance().getPersonnageCourant(),leGroupe.getId());
        finish();
    }

    public void UpdateSucess() {
    }

    public void selectAllPersoGroupFini(ArrayList<PersonnageGroupe> listPersoGroupe) {
        remplirListeGroupe(listPersoGroupe);
    }
    public void remplirListeGroupe(ArrayList<PersonnageGroupe> p_listePersonnageGroupe){

        listePersonnageGroupe =p_listePersonnageGroupe;
        int cpt = 0;
        tabPersonnageGroupe = new PersonnageGroupe[p_listePersonnageGroupe.size()];
        Log.i("logNomTailleTabGroupe","taille : "+ tabPersonnageGroupe.length);

        for (PersonnageGroupe unPerssonnageGroupe : listePersonnageGroupe) {
            tabPersonnageGroupe[cpt] = unPerssonnageGroupe;
            cpt++;
        }

        ArrayAdapter<PersonnageGroupe> monArrayAdapter = new ArrayAdapter<PersonnageGroupe>(this, R.layout.descripteur_listpersonnagegroups, tabPersonnageGroupe){
            private int vraiPosition=0;
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.i("logGoupePos", "pos : "+ position);


                PersonnageGroupe personnagesGroupe = tabPersonnageGroupe[position];

                if(convertView == null){
                    convertView = getLayoutInflater()
                            .inflate(R.layout.descripteur_listpersonnagegroups, parent, false);
                }
                TextView tv_nomPersonnage = (TextView) convertView.findViewById(R.id.tv_nomPersonnage);
                //TextView tv_niveau = (TextView) convertView.findViewById(R.id.tv_niveau);

                tv_nomPersonnage.setText(personnagesGroupe.getNomPersonnage());
                //tv_nbPerso.setText(String.valueOf(personnagesGroupe.));
                vraiPosition++;
                return convertView;
            }
        };
        maListPersonnageGroupes.setAdapter(monArrayAdapter);
    }

    public void InsertSuccess(String id) {
    }

    public void selectGroupById(Groupe unGroupe) {
        leGroupe =unGroupe;
    }
}
