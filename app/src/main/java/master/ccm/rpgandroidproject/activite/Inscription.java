package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.Entity.Utilisateur;
import master.ccm.rpgandroidproject.R;
import master.ccm.rpgandroidproject.manager.BDDManager;

public class Inscription extends AppCompatActivity {
    //private EditText champNom,champPassword,champPasswordVerif;
    private EditText champNom;
    private EditText champPassword;
    private EditText champRePassword;
    private static Utilisateur unUtilisateur = new Utilisateur();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        champNom =  findViewById(R.id.tb_userName);
        champPassword =  findViewById(R.id.tb_password);
        champRePassword =  findViewById(R.id.tb_rePassword);
    }
    public Boolean verifPassword (){
        String motDePasse =champPassword.getText().toString();
        String VerifmotDePasse =champRePassword.getText().toString();
        if (motDePasse.equals(VerifmotDePasse)){
            return true;
        }else{
            return  false;
        }
    }


    public void InsertSuccess (String idUtilisateur, String NomUtilisateur){
        Toast.makeText(this,"L'utilisateur à été inseré dans fireBase",Toast.LENGTH_SHORT).show();
        unUtilisateur.setId(idUtilisateur);
        //StaticUtilisateurInfo UtilisateurStatic= new StaticUtilisateurInfo();
        StaticUtilisateurInfo.getInstance().setId(idUtilisateur);
        StaticUtilisateurInfo.getInstance().setNom(NomUtilisateur);
        Intent monIntent = new Intent (this, MainActivity.class);
        startActivity(monIntent);


    }
    public void AfterInsertSuccessOAuth (String idUtilisateur,String NomUtilisateur){
        StaticUtilisateurInfo.getInstance().setId(idUtilisateur);
        StaticUtilisateurInfo.getInstance().setNom(NomUtilisateur);
    }
    public void InsertSuccessOAuth (String NomUtilisateur){
        BDDManager bddManager = new BDDManager();
        Utilisateur UtilisateurOAuth = new Utilisateur();
        UtilisateurOAuth.setNom(NomUtilisateur);
        bddManager.AjouterUtilisateurOAuth(UtilisateurOAuth, this);
    }



    public void onClickBouttonConnexion(View view) {
        Intent monIntent = new Intent (this, MainActivity.class);
        startActivity(monIntent);
    }

    public void onClickBouttonInscription(View view) {
        if (verifPassword()){
            Log.i("Tenshi", "Mdp ok !");
            Toast.makeText(this,"Debut de la procédure d'inscription",Toast.LENGTH_SHORT).show();
            BDDManager bddManager = new BDDManager();

            unUtilisateur.setNom(champNom.getText().toString());
            unUtilisateur.setMotDePasse(champPassword.getText().toString());
            bddManager.AjouterUtilisateur(unUtilisateur, this);

        }
        else{
            Log.i("Tenshi", "Erreur, mdp incorrect");
            Toast.makeText(this,"ERREUR ! Les deux mot de passe ne sont pas identique",Toast.LENGTH_SHORT).show();
        }
    }
    public void InsertFailNomExist() {

        Toast.makeText(this,"Le nom existe déjà",Toast.LENGTH_SHORT).show();

    }
}
