package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.R;
import master.ccm.rpgandroidproject.manager.BDDManager;

public class taverne_activite extends AppCompatActivity {
    BDDManager bddManager =new BDDManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taverne_activity);
    }

    public void onClickRetour(View view) {
        this.finish();
        /*Intent maps_activit = new Intent(this, maps_activite.class);
        startActivity(maps_activit);*/
    }

    public void onClickReposLong(View view) {
        int prix =10;
        if(StaticUtilisateurInfo.getInstance().getPersonnageCourant().getOr()>=prix){
            StaticUtilisateurInfo.getInstance().getPersonnageCourant().perteOr(prix);
            StaticUtilisateurInfo.getInstance().getPersonnageCourant().setPv(StaticUtilisateurInfo.getInstance().getPersonnageCourant().getPvMax());
            Toast.makeText(this, " Vous avez payer 10 or et vous vous êtes bien reposer et soigné vos bléssures", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Désolez, vous n'avez pas assez d'or", Toast.LENGTH_SHORT).show();
        }
        bddManager.UpdateDataPersonnage(StaticUtilisateurInfo.getInstance().getPersonnageCourant());

    }

    public void onClickReposCourt(View view) {
        Date now = new Date();
        long diff =now.getTime()-StaticUtilisateurInfo.getInstance().getDernierReposCourt().getTime();
        long diffMinutes = diff / (60*1000);
        // 5 min = 60*5 s= 300 s = 300 000 nanos
        if(diffMinutes>=5){
            int min = 1;
            int max = 6;
            int xPV = getRandomNumberInRange(min,max);
            StaticUtilisateurInfo.getInstance().getPersonnageCourant().gainPv(xPV);
            //Toast.makeText(this, "Vous avez gagné "+xPV +" PV", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Vous avez "+StaticUtilisateurInfo.getInstance().getPersonnageCourant().getPv()+ " point de vie", Toast.LENGTH_LONG).show();
            StaticUtilisateurInfo.getInstance().setDernierReposCourt(now);
        }else{
            Toast.makeText(this, "Vous vous êtes reposé il y a moins de 5 minutes", Toast.LENGTH_SHORT).show();
        }

        bddManager.UpdateDataPersonnage(StaticUtilisateurInfo.getInstance().getPersonnageCourant());

    }
    public static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
