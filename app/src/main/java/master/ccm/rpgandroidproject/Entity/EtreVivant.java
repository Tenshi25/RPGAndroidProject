package master.ccm.rpgandroidproject.Entity;

public class EtreVivant {
    protected String id;
    protected String nom;
    protected int pvMax,pv;
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


    public void gainPv(int pvRegain){
        if (this.pv < this.pvMax){
            setPv(this.pv+pvRegain);
        }
        if (this.pv> pvMax){
            setPv(pvMax);
        }
    }
    public void pertePv(int pvPerte){
        if (this.pv < pvPerte){

            setPv(this.pv-pvPerte);
        }
        if (this.pv > pvPerte){
            setPv(this.pv-pvPerte);
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

}
