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
import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.R;
import master.ccm.rpgandroidproject.manager.DonjonManager;

public class listeGroupeActivity extends AppCompatActivity {

    private ListView maListGroupes;
    private List<Groupe> listeGroupe;
    private Groupe[] tabGroupe;
    private DonjonManager DM= new DonjonManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_groupe);

        maListGroupes = findViewById(R.id.liste_groupe);

        DM.selectAllGroups(this);
    }

    public void onClickRejoindre(View view) {
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        //Toast.makeText(this,"position : " + position ,Toast.LENGTH_SHORT).show();
        Groupe GroupeAModif = listeGroupe.get(position);

        DM.UpdateNbPerso(this,GroupeAModif,1);
        //view.getParent()


    }
    public void onClickRetour(View view) {
        finish();
    }

    public void onClickCreerGroupe(View view) {
        Intent formCreateGroupe = new Intent(this,formCreateGroupeActivity.class);
        startActivity(formCreateGroupe);
    }

    public void UpdateSucess() {
    }

    public void selectAllPersoFini(ArrayList<Groupe> listGroupes) {
        RemplirListeGroupe(listGroupes);
    }

    public void RemplirListeGroupe(ArrayList<Groupe> p_listeGroupe){

        listeGroupe =p_listeGroupe;
        int cpt = 0;
        tabGroupe = new Groupe[p_listeGroupe.size()];
        Log.i("logNomTailleTabGroupe","taille : "+ tabGroupe.length);

        for (Groupe unGroupe : listeGroupe) {
            tabGroupe[cpt] = unGroupe;
            cpt++;
        }

        ArrayAdapter<Groupe> monArrayAdapter = new ArrayAdapter<Groupe>(this, R.layout.descripteur_liste_groupe, tabGroupe){
            private int vraiPosition=0;
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.i("logGoupePos", "pos : "+ position);

                Groupe groupe = tabGroupe[position];

                if(convertView == null){
                    convertView = getLayoutInflater()
                            .inflate(R.layout.descripteur_liste_groupe, parent, false);
                }
                TextView tv_nomGroupe = (TextView) convertView.findViewById(R.id.tv_nomGroupe);
                TextView tv_nbPerso = (TextView) convertView.findViewById(R.id.tv_nbPerso);

                tv_nomGroupe.setText(groupe.getNomGroupe());
                tv_nbPerso.setText(String.valueOf(groupe.getNbPerso()));
                vraiPosition++;
                return convertView;
            }
        };
        maListGroupes.setAdapter(monArrayAdapter);
    }
}
