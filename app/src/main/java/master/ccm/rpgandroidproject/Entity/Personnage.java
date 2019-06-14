package master.ccm.rpgandroidproject.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Personnage extends EtreVivant{


    private String prenom;
    private int experience;
    private int niveau;
    private int expNiveauSuivant;
    private int force;
    private int dexterite;
    private int constitution;
    private int inteligence;
    private int sagesse;
    private int charisme;
    private String classe;
    private ItemArme Armeprincipal;
    private ItemArmure Armure;
    private int or;
    private List<Item>Inventaire;
    private int CapArmure;

    //private List<Stats> statistique=new ArrayList<Stats>();

    public Personnage(String nom, String prenom, int experience, int pv, int pvmax, int niveau, int expNiveauSuivant,int or) {
        this.nom = nom;
        this.prenom = prenom;
        this.experience = experience;
        this.pv = pv;
        this.pvMax = pvmax;
        this.niveau = niveau;
        this.expNiveauSuivant = expNiveauSuivant;
        this.or =or;
    }
    public Personnage(){}

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

    public void gainExp(int exp){
        if (this.experience < this.expNiveauSuivant){
            setExperience(this.experience+exp);
        }
        if (this.experience >= expNiveauSuivant){
            setExperience(this.experience-this.expNiveauSuivant);
            setNiveau(this.niveau+1);
            setExpNiveauSuivant(getExpNiveauSuivant()*2+10);
            setPvMax(getPvMax()+getRandomNumberInRange(1,8));
            setPv(getPvMax());
        }
    }
    public static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    public boolean perteOr(int montantOr){
        if (this.or >= montantOr){
            setOr(this.or - montantOr);
            return true;
        }else{
            return false;
        }
    }
    public void gainOr(int montantOr){
        setOr(this.or + montantOr);
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

    public ItemArme getArmeprincipal() {
        return Armeprincipal;
    }

    public void setArmeprincipal(ItemArme armeprincipal) {
        Armeprincipal = armeprincipal;
    }

    public ItemArmure getArmure() {
        return Armure;
    }

    public void setArmure(ItemArmure armure) {
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

    public int getOr() {
        return or;
    }

    public void setOr(int or) {
        this.or = or;
    }

    public int getCapArmure() {
        return CapArmure;
    }

    public void setCapArmure(int capArmure) {
        CapArmure = capArmure;
    }

}
