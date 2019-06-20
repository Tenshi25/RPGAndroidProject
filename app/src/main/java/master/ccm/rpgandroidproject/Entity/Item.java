package master.ccm.rpgandroidproject.Entity;

import java.util.HashMap;
import java.util.Map;

public class Item {
    private String id;
    private String nom;
    private Map<String, Integer> Effet = new HashMap<>();
    private int quantite;
    private String TypeItem;

    public Item(String id, String nom,Map<String, Integer> mapEffet, String typeItem) {
        this.id = id;
        this.nom = nom;
        Effet = mapEffet;
        this.TypeItem = typeItem;



    }

    public Item() {
    }

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

    public Map<String, Integer> getEffet() {
        return Effet;
    }

    public void setEffet(Map<String, Integer> effet) {
        Effet = effet;
    }

    public String getTypeItem() {
        return TypeItem;
    }

    public void setTypeItem(String typeItem) {
        TypeItem = typeItem;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
