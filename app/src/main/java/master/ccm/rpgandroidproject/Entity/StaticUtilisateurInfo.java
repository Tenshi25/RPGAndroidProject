package master.ccm.rpgandroidproject.Entity;

import android.util.Log;

public class StaticUtilisateurInfo {
    private String id = "0";
    private String nom = "NULL";

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
}
