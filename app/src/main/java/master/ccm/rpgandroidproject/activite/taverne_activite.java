package master.ccm.rpgandroidproject.activite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.R;

public class taverne_activite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taverne_activity);
    }

    public void onClickRetour(View view) {
        this.finish();
    }

    public void onClickReposLong(View view) {
        int prix =10;
        if(StaticUtilisateurInfo.getInstance().getPersonnageCourant().getOr()>=prix){
            StaticUtilisateurInfo.getInstance().getPersonnageCourant().perteOr(prix);
            StaticUtilisateurInfo.getInstance().getPersonnageCourant().setPv(StaticUtilisateurInfo.getInstance().getPersonnageCourant().getPvMax());
            Toast.makeText(this, " Vous vous êtes bien reposer et soigné vos bléssures", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Désolez, vous n'avez pas assez d'or", Toast.LENGTH_SHORT).show();
        }


    }

    public void onClickReposCourt(View view) {
        //StaticUtilisateurInfo.getInstance().getDernierReposCourt()
        //if()
        int min = 1;
        int max = 6;
        int xPV = getRandomNumberInRange(min,max);
        StaticUtilisateurInfo.getInstance().getPersonnageCourant().gainPv(10);
        Toast.makeText(this, "Vous avez gagné "+xPV +" PV", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Vous avez "+StaticUtilisateurInfo.getInstance().getPersonnageCourant().getPv(), Toast.LENGTH_LONG).show();
        StaticUtilisateurInfo.getInstance().setDernierReposCourt(new Date());
    }
    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
