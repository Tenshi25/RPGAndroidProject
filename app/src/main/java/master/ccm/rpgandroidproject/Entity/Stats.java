package master.ccm.rpgandroidproject.Entity;

public class Stats {
    private String lib;
    private  int valeur;

    public Stats(String lib, int valeur) {
        this.lib = lib;
        this.valeur = valeur;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
}
