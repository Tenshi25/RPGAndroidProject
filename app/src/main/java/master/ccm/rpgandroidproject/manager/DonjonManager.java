package master.ccm.rpgandroidproject.manager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import master.ccm.rpgandroidproject.Entity.Groupe;
import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.Entity.Utilisateur;
import master.ccm.rpgandroidproject.activite.GroupeViewActivity;
import master.ccm.rpgandroidproject.activite.formCreateGroupeActivity;
import master.ccm.rpgandroidproject.activite.listeGroupeActivity;

public class DonjonManager {
    private static FirebaseFirestore database = FirebaseFirestore.getInstance();
    public void InsertDatastoreGroupe(final Personnage unPersonnage, final String nomGroupe, final formCreateGroupeActivity context)
    {

        Map<String, Object> groupMap = new HashMap<>();
        groupMap.put("nomGroupe", nomGroupe);
        groupMap.put("nbPersonnage", 1);


        database.collection("Groupe").add(groupMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        //if(!utilisateurExist){
                            Log.i("NewGroup","Le nouveau Groupe à été Ajouter");

                            context.InsertSuccess(task.getResult().getId());
                        }
                    //}

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("ajoutGroupeErreur","Erreur Le groupe n'a pas été ajouter correctement ");
            }
        });
    }

    public void UpdateNbPerso (final Context context, Groupe groupe, int joinQuit )
    {
        HashMap<String, Object> itemMap = new HashMap<>();
        itemMap.put("nbPersonnage", groupe.getNbPerso()+joinQuit);


        database.collection("Groupe").document(groupe.getId()).update(itemMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Log.i("UpdateGroupe","Le nombre d'item à été modifier");
                        switch (context.getClass().toString()){
                            case "page_equipement":
                                ((GroupeViewActivity) context).UpdateSucess();
                                break;

                            case "page_inventaire":
                                ((listeGroupeActivity) context).UpdateSucess();
                                break;

                            default: Log.i("logSwitch",context.getClass().toString());

                        }


                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("ModifItem","Erreur le nombre d'item n'a pas été modifier ");
            }
        });
    }

    public void selectAllGroups(final listeGroupeActivity context){
        database.collection("Groupe").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //if (!task.getResult().isEmpty()) {

//                            int cpt = 0;
                    ArrayList<Groupe> listGroupes = new ArrayList<Groupe>();
                    List<DocumentSnapshot> result = task.getResult().getDocuments();
                    for (DocumentSnapshot document : result) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
                        Groupe unGroupe = new Groupe(document.getId(),document.get("nomGroupe").toString(),Integer.parseInt(document.get("nbPersonnage").toString()));
//
                        listGroupes.add(unGroupe);
                        Log.i("logNomGroupe", "Nom :" + unGroupe.getNomGroupe());
                        Log.d("logGroupeSelectAll", document.getId() + " => " + document.getData());
//                                cpt = cpt + 1;


                    }
                    context.selectAllPersoFini(listGroupes);
                } else {
                    Log.w("selectAll", "Error getting documents.", task.getException());
                }
            }
        });
    }
}
