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
    private List<Stats> statistique=new ArrayList<>();

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

    private String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        this.nom = nom;
    }

    private String getPrenom() {
        return prenom;
    }

    private void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    private int getExperience() {
        return experience;
    }

    private void setExperience(int experience) {
        this.experience = experience;
    }

    private int getPv() {
        return pv;
    }

    private void setPv(int pv) {
        this.pv = pv;
    }
    private int getPvmax() {
        return pvmax;
    }

    private void setPvmax(int pvmax) {
        this.pvmax = pvmax;
    }
    private void pertePv(int pvDegat){
        if (this.pv> 0){
            setPv(this.pv-pvDegat);
        }
        if (this.pv<=0){
            setPv(0);
        }
    }

    private void gainPv(int pvRegain){
        if (this.pv < this.pvmax){
            setPv(this.pv+pvRegain);
        }
        if (this.pv>pvmax){
            setPv(pvmax);
        }
    }
    private void gainExp(int exp){
        if (this.experience < this.expNiveauSuivant){
            setExperience(this.experience+exp);
        }
        if (this.experience>expNiveauSuivant){
            setExperience(this.experience-this.expNiveauSuivant);
            setNiveau(this.niveau+1);


        }
    }


    private int getNiveau() {
        return niveau;
    }

    private void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    private int getExpNiveauSuivant() {
        return expNiveauSuivant;
    }

    private void setExpNiveauSuivant(int expNiveauSuivant) {
        this.expNiveauSuivant = expNiveauSuivant;
    }
}
