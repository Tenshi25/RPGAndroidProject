package master.ccm.rpgandroidproject.manager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import master.ccm.rpgandroidproject.Entity.Utilisateur;
import master.ccm.rpgandroidproject.activite.Inscription;

public class BDDManager {
    static FirebaseFirestore database = FirebaseFirestore.getInstance();
    static int autoIdentifiant;

    public void AjouterUtilisateur (Utilisateur unUtilisateur, final Inscription context)
    {
        Map<String, Object> utilisateurMap = new HashMap<>();
        utilisateurMap.put("nom", unUtilisateur.getNom());
        utilisateurMap.put("motDePasse", unUtilisateur.getMotDePasse());

        database.collection("Utilisateur").add(utilisateurMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Log.i("ajoutUtilisateur","L'utilisateur à été Ajouter");
                        String id = task.getResult().getId();

                        context.InsertSuccess(id);

                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("ajoutUtilisateurErreur","Erreur L'utilisateur n'a pas été ajouter correctement ");
            }
        });
    }
}
