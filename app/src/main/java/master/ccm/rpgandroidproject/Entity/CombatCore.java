package master.ccm.rpgandroidproject.Entity;

import android.content.Context;

import java.util.ArrayList;

public class CombatCore {
    private int idCombat;
    private int turnOrder=0;
    private int sumPersonnage;
    private ArrayList<EtreVivant> listeCombattant =new ArrayList<EtreVivant>() ;

    public void combatMain(Context context){
        EtreVivant Etre = listeCombattant.get(0);
        // faire dans l'activité un affiche message;

        if (Etre.getId().equals(StaticUtilisateurInfo.getInstance().getPersonnageCourant().getId()))
        {
            // faire une fonction pour disable
        }else {
            // enlever le disable
        }


        // tour suivant

    }
    public void TurnOrderNext()
    {
        setTurnOrder(getSumPersonnage()+1);
    }
    public void ajouterCombattant (EtreVivant unEtre){
        this.listeCombattant.add(unEtre);
        setSumPersonnage(countCombatant());

    }
    public int countCombatant (){
        return this.listeCombattant.size();
    }

    public void SupprimerCombatant(EtreVivant unEtre){
        this.listeCombattant.remove(unEtre);
        setSumPersonnage(countCombatant());
    }

    public int getTurnOrder() {
        return turnOrder;
    }

    public void setTurnOrder(int turnOrder) {
        this.turnOrder = turnOrder;
    }

    public int getSumPersonnage() {
        return sumPersonnage;
    }

    public void setSumPersonnage(int sumPersonnage) {
        this.sumPersonnage = sumPersonnage;
    }

    public ArrayList<EtreVivant> getListeCombattant() {
        return listeCombattant;
    }

    public void setListeCombattant(ArrayList<EtreVivant> listeCombattant) {
        this.listeCombattant = listeCombattant;
    }
}
