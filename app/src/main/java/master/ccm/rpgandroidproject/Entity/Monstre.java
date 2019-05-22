package master.ccm.rpgandroidproject.Entity;

public class Monstre {
    private String nom;
    private int pvMax,pv;
    private int force;
    private ItemArme Arme;
    private ItemArmure Armure;
    private int capArm;
    private int valeurExp;
    private int maxOr;

    public int getMaxOr() {
        return maxOr;
    }

    public void setMaxOr(int maxOr) {
        this.maxOr = maxOr;
    }

    public int getCapArm() {
        return capArm;
    }

    public void setCapArm(int capArm) {
        this.capArm = capArm;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPvMax() {
        return pvMax;
    }

    public void setPvMax(int pvmax) {
        this.pvMax = pvmax;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public ItemArme getArme() {
        return Arme;
    }

    public void setArme(ItemArme arme) {
        Arme = arme;
    }

    public ItemArmure getArmure() {
        return Armure;
    }

    public void setArmure(ItemArmure armure) {
        Armure = armure;
    }
    public void pertePv(int pvDegat){
        if (this.pv>= pvDegat) {
            setPv(this.pv - pvDegat);
        }else{
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
    public boolean VerifPvMort(){
        if (this.pv <= 0){
            setPv(0);
            return true;
        }else{
            return false;
        }

    }

    public int getValeurExp() {
        return valeurExp;
    }

    public void setValeurExp(int valeurExp) {
        this.valeurExp = valeurExp;
    }
}
