package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import master.ccm.rpgandroidproject.Entity.Utilisateur;
import master.ccm.rpgandroidproject.R;
import master.ccm.rpgandroidproject.manager.BDDManager;

public class MainActivity extends AppCompatActivity {
    private EditText champNom;
    private EditText champPassword;
    private static Utilisateur unUtilisateur = new Utilisateur();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        champNom =  findViewById(R.id.tb_username);
        champPassword =  findViewById(R.id.tb_password);

    }

    public void onClickButtonInscription(View view) {
        Intent monIntent = new Intent (this, Inscription.class);
        startActivity(monIntent);
    }
    public void onClickBouttonConnection(View view) {
            Log.i("Tenshi", "Mdp ok !");
            Toast.makeText(this,"Debut de la procédure de connexion",Toast.LENGTH_SHORT).show();
            BDDManager bddManager = new BDDManager();
            unUtilisateur.setNom(champNom.getText().toString());
            unUtilisateur.setMotDePasse(champPassword.getText().toString());
            bddManager.ConnexionUtilisateur(unUtilisateur, this);

    }
    public void ConnectSucess(String idUtilisateur) {
        unUtilisateur.setId(idUtilisateur);
        Toast.makeText(this,"Vous êtes connecté",Toast.LENGTH_SHORT).show();
        Intent monIntent = new Intent (this, pageAccueil.class);
        startActivity(monIntent);
    }
}
