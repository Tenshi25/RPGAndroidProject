package master.ccm.rpgandroidproject.Entity;

import java.util.ArrayList;
import java.util.List;

public class Personnage {
    private String id;
    private String nom;
    private String prenom;
    private int experience;
    private int pv;
    private int pvMax;
    private int niveau;
    private int expNiveauSuivant;
    private int force;
    private int dexterite;
    private int constitution;
    private int inteligence;
    private int sagesse;
    private int charisme;
    private String classe;
    private Item Armeprincipal;
    private Item Armure;
    private List<Item>Inventaire;

    //private List<Stats> statistique=new ArrayList<Stats>();

    public Personnage(String nom, String prenom, int experience, int pv, int pvmax, int niveau, int expNiveauSuivant) {
        this.nom = nom;
        this.prenom = prenom;
        this.experience = experience;
        this.pv = pv;
        this.pvMax = pvmax;
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
    public int getPvMax() {
        return pvMax;
    }

    public void setPvMax(int pvMax) {
        this.pvMax = pvMax;
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
        if (this.pv < this.pvMax){
            setPv(this.pv+pvRegain);
        }
        if (this.pv> pvMax){
            setPv(pvMax);
        }
    }
    public void gainExp(int exp){
        if (this.experience < this.expNiveauSuivant){
            setExperience(this.experience+exp);
        }
        if (this.experience >= expNiveauSuivant){
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

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getDexterite() {
        return dexterite;
    }

    public void setDexterite(int dexterite) {
        this.dexterite = dexterite;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getInteligence() {
        return inteligence;
    }

    public void setInteligence(int inteligence) {
        this.inteligence = inteligence;
    }

    public int getSagesse() {
        return sagesse;
    }

    public void setSagesse(int sagesse) {
        this.sagesse = sagesse;
    }

    public int getCharisme() {
        return charisme;
    }

    public void setCharisme(int charisme) {
        this.charisme = charisme;
    }

    public Item getArmeprincipal() {
        return Armeprincipal;
    }

    public void setArmeprincipal(Item armeprincipal) {
        Armeprincipal = armeprincipal;
    }

    public Item getArmure() {
        return Armure;
    }

    public void setArmure(Item armure) {
        Armure = armure;
    }

    public List<Item> getInventaire() {
        return Inventaire;
    }

    public void setInventaire(List<Item> inventaire) {
        Inventaire = inventaire;
    }
    public void ajoutItemInventaire(Item p_item) {
        Inventaire.add(p_item);
    }

    public void supprItemInventaire(Item p_item) {
        Inventaire.remove(p_item);
    }

}
