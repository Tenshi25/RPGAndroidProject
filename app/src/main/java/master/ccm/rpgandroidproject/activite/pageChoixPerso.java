package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.R;
import master.ccm.rpgandroidproject.manager.BDDManager;


public class pageChoixPerso extends AppCompatActivity {
    private TextView untextView;


    // on s'occupe de la liste view

    private ListView maListPersonnage;
    private List<Personnage>listePersonnage;
    private List<String> tableauChaines = new ArrayList<String>();
    private Personnage[] test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_choix_perso);
        untextView = findViewById(R.id.tv_message);
        //StaticUtilisateurInfo unUtilisateurStatic = new StaticUtilisateurInfo();
        String chaineBienvenue ="bienvenue : "+StaticUtilisateurInfo.getInstance().getNom();
        //Log.i("setInfo", StaticUtilisateurInfo.getInstance().getNom());
        untextView.setText(chaineBienvenue);
        maListPersonnage = findViewById(R.id.id_ListeViewTest);
        //(ListView)

        //recupéré une ressource

        BDDManager unBDDManager =  new BDDManager();
        unBDDManager.selectAllPersonnage(this);
        //tableauChaines=unBDDManager.selectAllPersonnage();

        //tableauChaines =getResources().getStringArray(R.array.tableau_de_chaines);*/

        //adapter fais le lien entre la liste et le tableau de chaine
        //ArrayAdapter<String> monArrayAdapter=new ArrayAdapter(this,R.layout.descripteur_de_ligne,R.id.tv_nom_perso,tableauChaines);
        //maListPersonnage.setAdapter(monArrayAdapter);
    }
    public void RemplirListepersonnage(ArrayList<Personnage> p_listePersonnage){
        Log.i("logNomTailleListePerso","taille : "+ p_listePersonnage.size());
        listePersonnage =p_listePersonnage;
        int cpt = 0;
        test = new Personnage[p_listePersonnage.size()];
        Log.i("logNomTailleTabPerso","taille : "+ test.length);

        for (Personnage unPersonnage : listePersonnage) {
            test[cpt] = unPersonnage;
        //    tableauChaines[cpt] = unPersonnage.getNom();

            //tableauChaines.add(unPersonnage.getNom());
            Log.i("logNomFor",cpt+". nom : "+ unPersonnage.getNom()+" prenom : "+ unPersonnage.getPrenom());
            cpt++;
        }


        ArrayAdapter<Personnage> monArrayAdapter = new ArrayAdapter<Personnage>(this, R.layout.descripteur_de_ligne2, test){
            private int vraiPosition=0;
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.i("logNomPos", "pos : "+ position);

                Personnage perso = test[position];

                if(convertView == null){
                    convertView = getLayoutInflater()
                            .inflate(R.layout.descripteur_de_ligne, parent, false);
                }
                TextView nomPerso = (TextView) convertView.findViewById(R.id.tv_nom_perso);
                TextView prenomPerso = (TextView) convertView.findViewById(R.id.tv_niv_perso);
                //ImageButton modifPerso = (ImageButton) convertView.findViewById(R.id.bt_modifPerso);
                //ImageButton SupprPerso = (ImageButton) convertView.findViewById(R.id.bt_supprPerso);
                nomPerso.setText(perso.getNom());
                prenomPerso.setText(perso.getPrenom());
                Log.i("logNomPerso","logNomPerso : "+perso.getNom()+" Prenom : "+perso.getPrenom());
                //
                vraiPosition++;
                return convertView;
            }
        };
        maListPersonnage.setAdapter(monArrayAdapter);
    }

    public void onConnectDisconnect(View view) {
        BDDManager unBDDManager =  new BDDManager();
        Personnage unPersonnage = new Personnage();
        unPersonnage.setId("");
        unBDDManager.SupprPersonnage(this,unPersonnage);

        StaticUtilisateurInfo.getInstance().setId(null);
        StaticUtilisateurInfo.getInstance().setNom(null);
        Intent monIntent = new Intent (this, MainActivity.class);
        startActivity(monIntent);

    }

    public void onClickNouveauPersonnage(View view) {
        Intent monIntent = new Intent (this, formAjoutPersonnage.class);
        startActivity(monIntent);
    }

    public void onClickModification(View view) {

    }

    public void onClickSupprimer(View view) {
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        Toast.makeText(this,"position : " + position ,Toast.LENGTH_SHORT).show();
        Personnage PersonnageASupprimer = listePersonnage.get(position);
        Toast.makeText(this,"Personnage à supprimer : id : "+PersonnageASupprimer.getId() +" "+ PersonnageASupprimer.getNom()+ ", "+ PersonnageASupprimer.getPrenom() ,Toast.LENGTH_SHORT).show();
        BDDManager unBDDManager =  new BDDManager();
        unBDDManager.SupprPersonnage(this,PersonnageASupprimer);
        listePersonnage.remove(PersonnageASupprimer);
        int cpt = 0;
        test = new Personnage[listePersonnage.size()];

        for (Personnage unPersonnage : listePersonnage) {
            test[cpt] = unPersonnage;
            cpt++;
        }

        //maListPersonnage.invalidateViews();
    }
}
