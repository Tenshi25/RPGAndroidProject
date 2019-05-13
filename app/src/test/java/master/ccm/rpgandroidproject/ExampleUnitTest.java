package master.ccm.rpgandroidproject;

import org.junit.Test;

import java.util.Random;

import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;

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
}
