package master.ccm.rpgandroidproject.Entity;

public class Groupe {
    private String id;
    private String nomGroupe;
    private int nbPerso;

    public Groupe(String id, String nomGroupe, int nbPerso) {
        this.id = id;
        this.nomGroupe = nomGroupe;
        this.nbPerso = nbPerso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public int getNbPerso() {
        return nbPerso;
    }

    public void setNbPerso(int nbPerso) {
        this.nbPerso = nbPerso;
    }
}
