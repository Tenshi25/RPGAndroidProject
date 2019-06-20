package master.ccm.rpgandroidproject.Entity;

public class PersonnageGroupe {
    private String idPersonnageGroupe;
    private String idPersonnage;
    private String nomPersonnage;
    private String idGroupe;

    public PersonnageGroupe(String idPersonnageGroupe, String idPersonnage, String nomPersonnage, String idGroupe) {
        this.idPersonnageGroupe = idPersonnageGroupe;
        this.idPersonnage = idPersonnage;
        this.nomPersonnage = nomPersonnage;
        this.idGroupe = idGroupe;
    }

    public String getIdPersonnageGroupe() {
        return idPersonnageGroupe;
    }

    public void setIdPersonnageGroupe(String idPersonnageGroupe) {
        this.idPersonnageGroupe = idPersonnageGroupe;
    }

    public String getIdPersonnage() {
        return idPersonnage;
    }

    public void setIdPersonnage(String idPersonnage) {
        this.idPersonnage = idPersonnage;
    }

    public String getNomPersonnage() {
        return nomPersonnage;
    }

    public void setNomPersonnage(String nomPersonnage) {
        this.nomPersonnage = nomPersonnage;
    }

    public String getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(String idGroupe) {
        this.idGroupe = idGroupe;
    }
}
