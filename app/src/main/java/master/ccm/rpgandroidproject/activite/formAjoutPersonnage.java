package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import master.ccm.rpgandroidproject.Entity.ItemArme;
import master.ccm.rpgandroidproject.Entity.ItemFactory;
import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.Entity.Utilisateur;
import master.ccm.rpgandroidproject.R;
import master.ccm.rpgandroidproject.manager.BDDManager;

public class formAjoutPersonnage extends AppCompatActivity {
    private Personnage unPersonnage;
    private EditText champNomPersonnage;
    private EditText champPrenomPersonnage;
    private Spinner listclasse;
    private int pvmax;
    private ItemArme armeHero;
    private static Utilisateur unUtilisateur = new Utilisateur();
    String URL ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_ajout_personnage);

        champNomPersonnage= findViewById(R.id.tb_nom_perso);
        champPrenomPersonnage= findViewById(R.id.tb_prenom_perso);
        listclasse =findViewById(R.id.id_listeClasse);

    }

    public void onClickRetour(View view) {
        Intent monIntent = new Intent (this, pageChoixPerso.class);
        startActivity(monIntent);
        finish();
    }

    public void onClickCreerPersonnage(View view) {
        Toast.makeText(this,"Debut de l'insertion du personnage",Toast.LENGTH_SHORT).show();
        unPersonnage = new Personnage();
        unPersonnage.setNom(champNomPersonnage.getText().toString());
        unPersonnage.setPrenom(champPrenomPersonnage.getText().toString());
        unPersonnage.setNiveau(1);
        unPersonnage.setExperience(0);
        unPersonnage.setExpNiveauSuivant(20);
        unPersonnage.setOr(20);
        unPersonnage.setClasse(listclasse.getSelectedItem().toString());
        ItemFactory maFabriqueItem = new ItemFactory();
        //ItemArme armeHero;

        armeHero = (ItemArme) maFabriqueItem.fabriqueMoiUnItem("Arme");
        armeHero.setNom("Epée");
        armeHero.setTypeItem("arme");
        armeHero.setEquiper(true);
        armeHero.setMaxDegat(6);
        armeHero.setMinDegat(2);

        switch(listclasse.getSelectedItem().toString()) {
            case "Guerrier":
                unPersonnage.setCapArmure(12);
                unPersonnage.setCharisme(10);
                unPersonnage.setConstitution(14);
                unPersonnage.setForce(16);
                unPersonnage.setInteligence(10);
                unPersonnage.setSagesse(8);
                unPersonnage.setDexterite(12);

                URL ="http://www.dnd5eapi.co/api/classes/5/";
                // code block
                break;
            case "Mage":
                // code block
                unPersonnage.setCapArmure(8);
                unPersonnage.setCharisme(12);
                unPersonnage.setConstitution(10);
                unPersonnage.setForce(8);
                unPersonnage.setInteligence(16);
                unPersonnage.setSagesse(14);
                unPersonnage.setDexterite(10);
                URL ="http://www.dnd5eapi.co/api/classes/12/";

                armeHero = (ItemArme) maFabriqueItem.fabriqueMoiUnItem("Arme");
                armeHero.setNom("Septre");
                armeHero.setTypeItem("arme");
                armeHero.setEquiper(true);
                armeHero.setMaxDegat(8);
                armeHero.setMinDegat(1);

                break;
            case "Rodeur":
                // code block
                unPersonnage.setCapArmure(10);
                unPersonnage.setCharisme(8);
                unPersonnage.setConstitution(10);
                unPersonnage.setForce(10);
                unPersonnage.setInteligence(12);
                unPersonnage.setSagesse(14);
                unPersonnage.setDexterite(16);

                URL ="http://www.dnd5eapi.co/api/classes/8/";

                armeHero = (ItemArme) maFabriqueItem.fabriqueMoiUnItem("Arme");
                armeHero.setNom("arc court");
                armeHero.setTypeItem("arme");
                armeHero.setEquiper(true);
                armeHero.setMaxDegat(7);
                armeHero.setMinDegat(1);
                break;
            default:
                unPersonnage.setCapArmure(10);
                // code block
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest= new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Rest Response", response.toString());
                        try {
                            Log.e("Rest Response name", response.get("hit_die").toString());
                            pvmax = Integer.parseInt(response.get("hit_die").toString());
                            Log.e("Rest Response integer", String.valueOf(pvmax));
                            FinAjoutPersonnage(unPersonnage,armeHero);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response", error.toString());

                    }
                }

        );
        requestQueue.add(objectRequest);


    }
    public void FinAjoutPersonnage(Personnage unPersonnage,ItemArme armeHero){
        Log.e("Rest Response integer 2", String.valueOf(pvmax));
        unPersonnage.setPvMax(pvmax);
        unPersonnage.setPv(pvmax);
        BDDManager leBDDManager =new BDDManager();
        unUtilisateur = new Utilisateur();
        unUtilisateur.setId(StaticUtilisateurInfo.getInstance().getId());
        leBDDManager.AjoutPersonnage(unUtilisateur,this,unPersonnage);
        leBDDManager.InsertitemInventaire(unPersonnage,armeHero,this);
    }
    public void InsertFailPersonnageExist(){
        Toast.makeText(this,"Le personnage existe déjà",Toast.LENGTH_SHORT).show();
    }
    public void InsertSuccess (){
        Toast.makeText(this,"Le Personnage à été inseré dans fireBase",Toast.LENGTH_SHORT).show();
        /*Intent monIntent = new Intent (this, pageChoixPerso.class);
        startActivity(monIntent);
        finish();*/
    }
    public void InsertArmeSuccess (/*String idObjet0*/){
        Log.i("insertArmeHero","L'arme à bien été inseré");
        //StaticUtilisateurInfo.getInstance().getPersonnageCourant().getArmeprincipal().setId(/*idObjet*/);
        Intent monIntent = new Intent (this, pageChoixPerso.class);
        startActivity(monIntent);
        finish();
    }
}
