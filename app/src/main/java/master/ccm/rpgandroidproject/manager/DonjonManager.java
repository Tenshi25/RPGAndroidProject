package master.ccm.rpgandroidproject.manager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import master.ccm.rpgandroidproject.Entity.Groupe;
import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.Entity.PersonnageGroupe;
import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.Entity.Utilisateur;
import master.ccm.rpgandroidproject.activite.GroupeViewActivity;
import master.ccm.rpgandroidproject.activite.formCreateGroupeActivity;
import master.ccm.rpgandroidproject.activite.listeGroupeActivity;
import master.ccm.rpgandroidproject.activite.pageChoixPerso;

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
                            Groupe unGroupe = new Groupe(task.getResult().getId(),nomGroupe,1);
                            context.InsertSuccess(unGroupe);
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
        int newNbPerso =groupe.getNbPerso()+joinQuit;
        Log.i("newNbPerso", String.valueOf(newNbPerso));
        if(newNbPerso > 0) {
            itemMap.put("nbPersonnage", newNbPerso);


            database.collection("Groupe").document(groupe.getId()).update(itemMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Log.i("UpdateGroupe", "Le nombre d'item à été modifier");
                            switch (context.getClass().toString()) {
                                case "GroupeViewActivity":
                                    ((GroupeViewActivity) context).UpdateSucess();
                                    break;

                                case "listeGroupeActivity":
                                    ((listeGroupeActivity) context).UpdateSucess();
                                    break;

                                default:
                                    Log.i("logSwitch", context.getClass().toString());

                            }


                        }

                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i("ModifItem", "Erreur le nombre d'item n'a pas été modifier ");
                }
            });
        }else {
            SupprGroups(groupe);
        }
    }
    public void selectGroupById(final GroupeViewActivity context, final Groupe unGroupe){
        database.collection("Groupe").whereEqualTo("idGroupe", unGroupe.getId()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //if (!task.getResult().isEmpty()) {


                    ArrayList<Groupe> listGroupe = new ArrayList<Groupe>();
                    List<DocumentSnapshot> result = task.getResult().getDocuments();
                    for (DocumentSnapshot document : result) {
                        Groupe unGroupe = new Groupe(document.getId(),document.get("nomGroupe").toString(),Integer.parseInt(document.get("nbPersonnage").toString()));
                        unGroupe.setId(document.getId());


                        listGroupe.add(unGroupe);


                    }
                    context.selectGroupById(unGroupe);
                } else {
                    Log.w("selectAll", "Error getting documents.", task.getException());
                }
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
    public void SupprGroups( Groupe unGroupe){
        database.collection("Groupe").document(unGroupe.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("supprGroupePersonnage", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("supprGroupePersonnage", "Error deleting document", e);
                    }
                });

        //InsertDatastorePersonnage(unUtilisateur,unPersonnage,context);
    }
    public void selectAllPersoGroups(final GroupeViewActivity context){
        database.collection("GroupePersonnage").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //if (!task.getResult().isEmpty()) {

//                            int cpt = 0;
                    ArrayList<PersonnageGroupe> listPersoGroupe = new ArrayList<PersonnageGroupe>();
                    List<DocumentSnapshot> result = task.getResult().getDocuments();
                    for (DocumentSnapshot document : result) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
                        PersonnageGroupe unPersonnageGroupe = new PersonnageGroupe(document.getId(),document.get("idPersonnage").toString(),document.get("nomPersonnage").toString(),document.get("idGroupe").toString());
//
                        listPersoGroupe.add(unPersonnageGroupe);
                        Log.i("logNomPersonnageGroupe", "Nom :" + unPersonnageGroupe.getNomPersonnage());
                        Log.d("logPersonnageGroupeSelectAll", document.getId() + " => " + document.getData());
//                                cpt = cpt + 1;
                    }
                    context.selectAllPersoGroupFini(listPersoGroupe);
                } else {
                    Log.w("selectAll", "Error getting documents.", task.getException());
                }
            }
        });
    }
    public void InsertDatastorePersonnageGroupe(final Personnage unPersonnage, final Groupe groupe, final Context context)
    {

        Map<String, Object> groupMap = new HashMap<>();
        groupMap.put("idGroupe", groupe.getId());
        groupMap.put("idPersonnage", unPersonnage.getId());
        groupMap.put("nomPersonnage", unPersonnage.getNom() +" "+unPersonnage.getPrenom());


        database.collection("GroupePersonnage").add(groupMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        //if(!utilisateurExist){
                        Log.i("NewGroup","Le nouveau Groupe à été Ajouter");
                        switch (context.getClass().toString()) {
                            case "GroupeViewActivity":
                                ((GroupeViewActivity) context).InsertSuccess(task.getResult().getId());
                                break;

                            case "listeGroupeActivity":
                                ((listeGroupeActivity) context).InsertSuccess(task.getResult().getId());
                                break;

                            default:
                                Log.i("logSwitch", context.getClass().toString());

                        }

                        //context.InsertSuccess(task.getResult().getId());

                    }
                    //}

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("ajoutGroupeErreur","Erreur Le groupe n'a pas été ajouter correctement ");
            }
        });
    }
    public void SupprPersonnageGroups( PersonnageGroupe unPersonnageGroupe){
        database.collection("GroupePersonnage").document(unPersonnageGroupe.getIdPersonnageGroupe())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("supprGroupePersonnage", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("supprGroupePersonnage", "Error deleting document", e);
                    }
                });

        //InsertDatastorePersonnage(unUtilisateur,unPersonnage,context);
    }
    public void SupprPersonnageGroups( Personnage unPersonnageGroupe, String idGroupe){


        final WriteBatch batch = database.batch();
        database
                .collection("GroupePersonnage")
                .whereEqualTo("idPersonnage", unPersonnageGroupe.getId())
                .whereEqualTo("idGroupe",idGroupe)
                .get(Source.DEFAULT)
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<DocumentSnapshot> result = task.getResult().getDocuments();
                        for (DocumentSnapshot unResult : result) {
                            batch.delete(unResult.getReference());
                        }
                        batch.commit();
                    }
                });
    }
}
