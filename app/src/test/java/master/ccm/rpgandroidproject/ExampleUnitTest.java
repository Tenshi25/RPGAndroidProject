package master.ccm.rpgandroidproject;

import org.junit.Test;

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
        StaticUtilisateurInfo.getInstance().setNom("moi");

        // verification si l'objet à bien engistré la chaîne
        assertEquals("moi", StaticUtilisateurInfo.getInstance().getNom(), "moi");
    }
}
