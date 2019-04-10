package master.ccm.rpgandroidproject.Entity;

import java.util.ArrayList;
import java.util.List;

public class Personnage {
    private String nom;
    private String prenom;
    private int experience;
    private int pv;
    private int pvmax;
    private int niveau;
    private int expNiveauSuivant;
    private String classe;
    private List<Stats> statistique=new ArrayList<Stats>();

    public Personnage(String nom, String prenom, int experience, int pv, int pvmax, int niveau, int expNiveauSuivant) {
        this.nom = nom;
        this.prenom = prenom;
        this.experience = experience;
        this.pv = pv;
        this.pvmax = pvmax;
        this.niveau = niveau;
        this.expNiveauSuivant = expNiveauSuivant;
    }
    public Personnage(){}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }
    public int getPvmax() {
        return pvmax;
    }

    public void setPvmax(int pvmax) {
        this.pvmax = pvmax;
    }
    public void pertePv(int pvDegat){
        if (this.pv> 0){
            setPv(this.pv-pvDegat);
        }
        if (this.pv<=0){
            setPv(0);
        }
    }

    public void gainPv(int pvRegain){
        if (this.pv < this.pvmax){
            setPv(this.pv+pvRegain);
        }
        if (this.pv>pvmax){
            setPv(pvmax);
        }
    }
    public void gainExp(int exp){
        if (this.experience < this.expNiveauSuivant){
            setExperience(this.experience+exp);
        }
        if (this.experience>expNiveauSuivant){
            setExperience(this.experience-this.expNiveauSuivant);
            setNiveau(this.niveau+1);


        }
    }


    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getExpNiveauSuivant() {
        return expNiveauSuivant;
    }

    public void setExpNiveauSuivant(int expNiveauSuivant) {
        this.expNiveauSuivant = expNiveauSuivant;
    }
}
