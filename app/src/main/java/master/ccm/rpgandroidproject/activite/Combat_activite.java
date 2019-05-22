package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import master.ccm.rpgandroidproject.Entity.Item;
import master.ccm.rpgandroidproject.Entity.ItemArme;
import master.ccm.rpgandroidproject.Entity.ItemFactory;
import master.ccm.rpgandroidproject.Entity.Monstre;
import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.R;
import master.ccm.rpgandroidproject.manager.BDDManager;

public class Combat_activite extends AppCompatActivity {
    ImageView imageMonstre;
    Monstre unMonstre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat_activite);

        imageMonstre = findViewById(R.id.imageMonstre);
        int r = getRandomNumberInRange(1,3);

        unMonstre = new Monstre();
        ItemFactory maFabriqueItem = new ItemFactory();
        ItemArme ArmeMonstre;
        switch(r) {
            case 1:
                //rat
                unMonstre.setNom("Rat");
                unMonstre.setPv(5);
                unMonstre.setPvMax(5);
                unMonstre.setCapArm(5);
                unMonstre.setForce(8);
                unMonstre.setValeurExp(10);

                ArmeMonstre = (ItemArme) maFabriqueItem.fabriqueMoiUnItem("Arme");
                ArmeMonstre.setMaxDegat(4);
                ArmeMonstre.setMinDegat(1);

                unMonstre.setArme(ArmeMonstre);
                // code block
                imageMonstre.setImageResource(R.drawable.rat);
                break;
            case 2:
                // code block
                //Orc
                unMonstre.setNom("Orc");

                unMonstre.setPv(20);
                unMonstre.setPvMax(20);
                unMonstre.setCapArm(12);
                unMonstre.setForce(12);
                imageMonstre.setImageResource(R.drawable.orc);
                unMonstre.setValeurExp(50);

                ArmeMonstre = (ItemArme) maFabriqueItem.fabriqueMoiUnItem("Arme");
                ArmeMonstre.setMaxDegat(6);
                ArmeMonstre.setMinDegat(1);

                unMonstre.setArme(ArmeMonstre);
                break;
            case 3:
                // code block
                //guepe

                unMonstre.setPv(8);
                unMonstre.setPvMax(8);
                unMonstre.setNom("Guepe");
                unMonstre.setCapArm(10);
                unMonstre.setForce(10);
                unMonstre.setValeurExp(25);

                imageMonstre.setImageResource(R.drawable.frelon);

                ArmeMonstre = (ItemArme) maFabriqueItem.fabriqueMoiUnItem("Arme");
                ArmeMonstre.setMaxDegat(6);
                ArmeMonstre.setMinDegat(1);

                unMonstre.setArme(ArmeMonstre);

                break;
            default:
                unMonstre.setCapArm(10);
                unMonstre.setForce(10);


                // code block
        }


    }
    public static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void onClickFuir(View view) {
        finish();
    }

    public void onClickAttaquer(View view) {
        //Attaque Du Héros
        int resultDe,DegatDe ;
        Personnage hero =StaticUtilisateurInfo.getInstance().getPersonnageCourant();
        switch(hero.getClasse()) {
            case "Guerrier":

                resultDe= getRandomNumberInRange(1,20)+Math.round((float)((hero.getForce()-10)/2));
                if(resultDe>unMonstre.getCapArm()){
                    Toast.makeText(this, "Vous avez avez touché "+unMonstre.getNom(), Toast.LENGTH_LONG).show();
                    imageMonstre.setColorFilter(1);
                    if(hero.getArmeprincipal() != null){
                        DegatDe = getRandomNumberInRange(hero.getArmeprincipal().getMinDegat(),hero.getArmeprincipal().getMaxDegat());
                    }else{
                        DegatDe = getRandomNumberInRange(1,3);
                    }
                    unMonstre.pertePv(DegatDe);
                    imageMonstre.clearColorFilter();
                }else{
                    Toast.makeText(this, "Vous avez raté votre attaque ", Toast.LENGTH_LONG).show();
                }

                break;
            case "Mage":
                resultDe= getRandomNumberInRange(1,20)+Math.round((float)((hero.getInteligence()-10)/2));
                if(resultDe>unMonstre.getCapArm()){
                    Toast.makeText(this, "Vous avez infligé " + resultDe + " au monstre "+unMonstre.getNom(), Toast.LENGTH_LONG).show();
                    imageMonstre.setColorFilter(1);
                    if(hero.getArmeprincipal() != null){
                        DegatDe = getRandomNumberInRange(hero.getArmeprincipal().getMinDegat(),hero.getArmeprincipal().getMaxDegat());
                    }else{
                        DegatDe = getRandomNumberInRange(1,3);
                    }
                    unMonstre.pertePv(DegatDe);
                    imageMonstre.clearColorFilter();
                }else{
                    Toast.makeText(this, "Vous avez raté votre attaque ", Toast.LENGTH_LONG).show();
                }

                break;
            case "Rodeur":
                resultDe= getRandomNumberInRange(1,20)+Math.round((float)((hero.getDexterite()-10)/2));
                if(resultDe>unMonstre.getCapArm()){
                    Toast.makeText(this, "Vous avez avez touché "+unMonstre.getNom(), Toast.LENGTH_LONG).show();
                    imageMonstre.setColorFilter(1);
                    if(hero.getArmeprincipal() != null){
                        DegatDe = getRandomNumberInRange(hero.getArmeprincipal().getMinDegat(),hero.getArmeprincipal().getMaxDegat());
                    }else{
                        DegatDe = getRandomNumberInRange(1,3);
                    }
                    unMonstre.pertePv(DegatDe);
                    imageMonstre.clearColorFilter();
                }else{
                    Toast.makeText(this, "Vous avez raté votre attaque ", Toast.LENGTH_LONG).show();
                }

                break;
        }

        if(unMonstre.VerifPvMort()){
            Toast.makeText(this, "Le Monstre est mort ", Toast.LENGTH_LONG).show();
            hero.gainExp(unMonstre.getValeurExp());
            BDDManager bddManager =new BDDManager();
            bddManager.UpdateDataPersonnage(hero);
            imageMonstre.setVisibility(View.INVISIBLE);
            /*try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            this.finish();
        }else {


            //Attaque du Monstre
            resultDe = getRandomNumberInRange(1, 20) + Math.round((float) ((unMonstre.getForce() - 10) / 2));
            if (resultDe > hero.getCapArmure()) {
                DegatDe = getRandomNumberInRange(unMonstre.getArme().getMinDegat(), unMonstre.getArme().getMaxDegat());
                hero.pertePv(DegatDe);
                Toast.makeText(this, "Vous avez été touché ! vous avez perdu " + DegatDe + " pv", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Le " + unMonstre.getNom() + " vous a raté", Toast.LENGTH_LONG).show();
            }
            BDDManager bddManager =new BDDManager();
            if (hero.VerifPvMort()) {
                bddManager.SupprPersonnage(hero);
                Toast.makeText(this, "Votre personnage est mort ", Toast.LENGTH_LONG).show();
                Intent monIntent = new Intent(this, GameOver.class);
                startActivity(monIntent);
                finish();
            }

            bddManager.UpdateDataPersonnage(StaticUtilisateurInfo.getInstance().getPersonnageCourant());
        }


        /*int pointDegat = 4;
        StaticUtilisateurInfo.getInstance().getPersonnageCourant().pertePv(pointDegat);

        Toast.makeText(this, "Vous avez perdu "+ pointDegat + " point de vie", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Vous avez "+StaticUtilisateurInfo.getInstance().getPersonnageCourant().getPv() + " point de vie", Toast.LENGTH_LONG).show();*/
    }
}
