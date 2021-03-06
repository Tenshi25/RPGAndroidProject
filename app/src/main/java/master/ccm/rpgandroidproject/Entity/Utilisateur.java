package master.ccm.rpgandroidproject.Entity;

import java.util.Objects;

public class Utilisateur {
    private String id;
    private String nom;
    private String motDePasse;
    private Personnage persoCurrent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Personnage getPersoCurrent() {
        return persoCurrent;
    }

    public void setPersoCurrent(Personnage persoCurrent) {
        this.persoCurrent = persoCurrent;
    }
}
