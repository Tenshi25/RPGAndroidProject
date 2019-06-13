package master.ccm.rpgandroidproject.Entity;

public class Monstre extends EtreVivant{

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


    public int getValeurExp() {
        return valeurExp;
    }

    public void setValeurExp(int valeurExp) {
        this.valeurExp = valeurExp;
    }
}
