package master.ccm.rpgandroidproject.Entity;

public class Personnage {
    private String nom;
    private String prenom;
    private int experience;
    private int pv;

    public Personnage(String nom, String prenom, int experience, int pv) {
        this.nom = nom;
        this.prenom = prenom;
        this.experience = experience;
        this.pv = pv;
    }

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

}
