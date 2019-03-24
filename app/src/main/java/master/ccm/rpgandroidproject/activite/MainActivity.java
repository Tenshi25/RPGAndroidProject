package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import master.ccm.rpgandroidproject.Entity.Utilisateur;
import master.ccm.rpgandroidproject.R;
import master.ccm.rpgandroidproject.manager.BDDManager;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient mGoogleApiClient;
    private EditText champNom;
    private EditText champPassword;
    private static Utilisateur unUtilisateur = new Utilisateur();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        champNom =  findViewById(R.id.tb_username);
        champPassword =  findViewById(R.id.tb_password);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
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
    }public void onClickGoogleOAuth(View view) {
        Intent sign = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(sign, 9025);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 9025){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount acct = result.getSignInAccount();
                Log.i("Login", acct.getDisplayName());
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
