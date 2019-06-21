package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import master.ccm.rpgandroidproject.Entity.Groupe;
import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
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

        maListGroupes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Groupe groupeSelectionner = listeGroupe.get(position);
                maListGroupes.getChildAt(position).setBackgroundColor(Color.RED);
                //AffichePersonnageClicker(personnageSelectionner);

            }

        });

    }

    public void onClickRejoindre(View view) {
        View parentRow = (View) view.getParent();
        Log.i("logGoupePos", "ListView : "+ parentRow.getParent().getParent());
        ListView listView = (ListView) parentRow.getParent().getParent();



        final int position = listView.getPositionForView(parentRow);
        Log.i("logGoupePos", "position : "+ position);
        //Toast.makeText(this,"position : " + position ,Toast.LENGTH_SHORT).show();
        Groupe GroupeAModif = listeGroupe.get(position);

        DM.UpdateNbPerso(this,GroupeAModif,1);
        DM.InsertDatastorePersonnageGroupe(StaticUtilisateurInfo.getInstance().getPersonnageCourant(),GroupeAModif,this);
        //view.getParent()

        Intent groupViewActivity = new Intent(this,GroupeViewActivity .class);

        groupViewActivity.putExtra("idGroupe",GroupeAModif.getId());
        groupViewActivity.putExtra("nbPersoGroupe",GroupeAModif.getNbPerso());
        groupViewActivity.putExtra("nomGroupe",GroupeAModif.getNomGroupe());

        startActivity(groupViewActivity);
        finish();


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
        Log.i("logNomTailleListeGroup","taille : "+ p_listeGroupe.size());
        listeGroupe =p_listeGroupe;
        int cpt = 0;
        tabGroupe = new Groupe[p_listeGroupe.size()];
        Log.i("logNomTailleTabGroupe","taille : "+ tabGroupe.length);

        for (Groupe unGroupe : listeGroupe) {
            tabGroupe[cpt] = unGroupe;
            Log.i("logNomFor",tabGroupe[cpt].getNomGroupe());
            cpt++;
        }

        ArrayAdapter<Groupe> monArrayAdapter = new ArrayAdapter<Groupe>(this, R.layout.descripteur_liste_groupe2, tabGroupe){
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

    public void InsertSuccess(String id) {

    }
}
