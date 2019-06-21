package master.ccm.rpgandroidproject;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import master.ccm.rpgandroidproject.Entity.Classdd;
import master.ccm.rpgandroidproject.Entity.CombatCore;
import master.ccm.rpgandroidproject.Entity.EtreVivant;
import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.activite.Combat_activite;
import master.ccm.rpgandroidproject.activite.taverne_activite;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void SetStaticUtilisateur() throws Exception {

        // set le nom
        StaticUtilisateurInfo.getInstance().setNom("Test");

        // verification si l'objet à bien engistré la chaîne
        assertEquals("Test", StaticUtilisateurInfo.getInstance().getNom(), "Test");
    }
    @Test
    public void SetGainExpPersonnage() throws Exception {

        // set le nom
        Personnage unPersonnage = new Personnage();
        unPersonnage.setExperience(0);
        Random rand = new Random();

        // Obtain a number between [0 - 49].
        int nbExp = rand.nextInt(50);
        unPersonnage.setExpNiveauSuivant(100);
        unPersonnage.gainExp(nbExp);
        // verification si l'objet à bien engistré la chaîne
        assertEquals("TestGainExp", nbExp, unPersonnage.getExperience());
    }
    @Test
    public void SetGainNiveau() throws Exception {

        // set le nom
        Personnage unPersonnage = new Personnage();
        unPersonnage.setExperience(0);
        Random rand = new Random();

        // Obtain a number between [0 - 49].
        int nbExp = 100;
        unPersonnage.setExpNiveauSuivant(100);
        unPersonnage.setNiveau(1);
        unPersonnage.gainExp(nbExp);
        // verification si l'objet à bien engistré la chaîne
        assertEquals("TestGainExp", 2, unPersonnage.getNiveau());
    }
    @Test
    public void SetGainNiveauExp() throws Exception {

        // set le nom
        Personnage unPersonnage = new Personnage();
        unPersonnage.setExperience(0);
        Random rand = new Random();

        // Obtain a number between [0 - 49].
        int nbExp = 160;

        unPersonnage.setExpNiveauSuivant(100);
        int ExpExpected =nbExp-unPersonnage.getExpNiveauSuivant();
        unPersonnage.setNiveau(1);
        unPersonnage.gainExp(nbExp);
        // verification si l'objet à bien engistré la chaîne
        assertEquals("TestGainExp2", ExpExpected, unPersonnage.getExperience());
    }
    @Test
    public void testGainor() throws Exception {

        Personnage unPersonnage = new Personnage();
        int montantDepart = 20;
        unPersonnage.setOr(montantDepart);

        int montantGain = 100;
        unPersonnage.gainOr(montantGain);
        // verification si l'objet à bien engistré la chaîne
        assertEquals("TestGainExp", montantGain+montantDepart, unPersonnage.getOr());
    }
    @Test
    public void testPerteOrPetite() throws Exception {

        Personnage unPersonnage = new Personnage();
        int montantDepart = 20;
        unPersonnage.setOr(montantDepart);

        int montantPerte = 10;
        unPersonnage.perteOr(montantPerte);
        // verification si l'objet à bien engistré la chaîne
        assertEquals("TestPerteOrPetite", montantDepart-montantPerte, unPersonnage.getOr());
    }
    @Test
    public void testPerteOrBoolean() throws Exception {

        Random rand = new Random();

        // Obtain a number between [0 - 49].
        int montantPerte = rand.nextInt(50);

        Personnage unPersonnage = new Personnage();
        int montantDepart = 20;
        unPersonnage.setOr(montantDepart);

        boolean testboolean=unPersonnage.perteOr(montantPerte);

        if(montantPerte>montantDepart){
            assertEquals("TestPerteorBoolean", false, testboolean);
        }else{
            assertEquals("TestGainExp", true, testboolean);
        }


    }
    @Test
    public void testPertePv() throws Exception {

        Random rand = new Random();

        // Obtain a number between [0 - 49].
        int montantPerte = rand.nextInt(60);

        Personnage unPersonnage = new Personnage();
        int PvDepart = 20;
        unPersonnage.setPv(PvDepart);
        unPersonnage.setPvMax(PvDepart);

        unPersonnage.pertePv(montantPerte);
        boolean testboolean=unPersonnage.VerifPvMort();

        if(montantPerte >= PvDepart){
            assertEquals("TestPersonnageMort", true, testboolean);
        }else{
            assertEquals("TestPersonnageVivant", false, testboolean);
        }


    }
    @Test
    public void testCombatCoreSum() throws Exception {


        EtreVivant hero = new EtreVivant();
        CombatCore CC = new CombatCore();
        hero.setNom("Yugi");
        CC.ajouterCombattant(hero);
        EtreVivant mechant = new EtreVivant();
        mechant.setNom("Kaiba");
        CC.ajouterCombattant(mechant);
        assertEquals("TestNombreCombatant", 2, CC.countCombatant());


    }


    /*
    @Test
    public void testGetRandom() throws Exception {

        taverne_activite taverne_activity = new taverne_activite();
        int min = 10;
        int max = 20;
        int randomValue = taverne_activity.getRandomNumberInRange(min,max);
        boolean resultatboolean = false;
        if( randomValue<max && randomValue>min){
            resultatboolean =true;

        }
        assertEquals("TestRandom", true, resultatboolean);

    }*/
}
