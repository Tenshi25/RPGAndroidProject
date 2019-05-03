package master.ccm.rpgandroidproject.Entity;

import android.util.Log;

import java.util.ArrayList;

public class StaticUtilisateurInfo {
    private String id = "0";
    private String nom = "NULL";
    private Personnage personnageCourant;
    private ArrayList<Item> listeItemBase=new ArrayList<>();

    private static StaticUtilisateurInfo sui = null;

    public static synchronized StaticUtilisateurInfo getInstance(){
        if(null == sui){
            sui = new StaticUtilisateurInfo();
        }
        return sui;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
        //Log.i("setId", id);
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
        //Log.i("setnom", nom);
    }

    public Personnage getPersonnageCourant() {
        return personnageCourant;
    }

    public void setPersonnageCourant(Personnage personnageCourant) {
        this.personnageCourant = personnageCourant;
    }

    public ArrayList<Item> getListeItemBase() {
        return listeItemBase;
    }

    public void setListeItemBase(ArrayList<Item> listeItemBase) {
        this.listeItemBase = listeItemBase;
    }
}
