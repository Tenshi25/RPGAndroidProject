package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.Entity.Utilisateur;
import master.ccm.rpgandroidproject.R;
import master.ccm.rpgandroidproject.manager.BDDManager;

public class pageAjoutPersonnage extends AppCompatActivity {
    private Personnage unPersonnage;
    private EditText champNomPersonnage;
    private EditText champPrenomPersonnage;
    private Spinner listclasse;
    private static Utilisateur unUtilisateur = new Utilisateur();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_ajout_personnage);

        champNomPersonnage= findViewById(R.id.tb_nom_perso);
        champPrenomPersonnage= findViewById(R.id.tb_prenom_perso);
        listclasse =findViewById(R.id.id_listeClasse);

    }

    public void onClickRetour(View view) {
        Intent monIntent = new Intent (this, pageAccueil.class);
        startActivity(monIntent);
    }

    public void onClickCreerPersonnage(View view) {
        Toast.makeText(this,"Debut de l'insertion du personnage",Toast.LENGTH_SHORT).show();
        unPersonnage = new Personnage();
        unPersonnage.setNom(champNomPersonnage.getText().toString());
        unPersonnage.setPrenom(champPrenomPersonnage.getText().toString());
        unPersonnage.setNiveau(1);
        unPersonnage.setExperience(0);
        unPersonnage.setExpNiveauSuivant(20);
        unPersonnage.setClasse(listclasse.getSelectedItem().toString());
        //a remplacer par une fonction qui demande à l'api rest
        int pvmax = 8;
        unPersonnage.setPvMax(pvmax);
        unPersonnage.setPv(pvmax);
        BDDManager leBDDManager =new BDDManager();
        unUtilisateur = new Utilisateur();
        unUtilisateur.setId(StaticUtilisateurInfo.getInstance().getId());
        leBDDManager.AjoutPersonnage(unUtilisateur,this,unPersonnage);
    }
    public void InsertFailPersonnageExist(){
        Toast.makeText(this,"Le personnage existe déjà",Toast.LENGTH_SHORT).show();
    }
    public void InsertSuccess (){
        Toast.makeText(this,"Le Personnage à été inseré dans fireBase",Toast.LENGTH_SHORT).show();
        Intent monIntent = new Intent (this, pageAccueil.class);
        startActivity(monIntent);
    }
}
