package master.ccm.rpgandroidproject.manager;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.Entity.Utilisateur;
import master.ccm.rpgandroidproject.activite.formCreateGroupeActivity;

public class DonjonManager {
    private static FirebaseFirestore database = FirebaseFirestore.getInstance();
    public void InsertDatastoreGroupe(final Personnage unPersonnage, String nomGroupe, final formCreateGroupeActivity context)
    {

        Map<String, Object> utilisateurMap = new HashMap<>();
        utilisateurMap.put("nomGroupe", nomGroupe);


        database.collection("Groupe").add(utilisateurMap)
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
}
