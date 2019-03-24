package master.ccm.rpgandroidproject.manager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import master.ccm.rpgandroidproject.Entity.Utilisateur;
import master.ccm.rpgandroidproject.activite.Inscription;
import master.ccm.rpgandroidproject.activite.MainActivity;

public class BDDManager {
    private static FirebaseFirestore database = FirebaseFirestore.getInstance();
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

                        context.InsertSuccess(task.getResult().getId());

                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("ajoutUtilisateurErreur","Erreur L'utilisateur n'a pas été ajouter correctement ");
            }
        });
    }
    public void ConnexionUtilisateur (final Utilisateur unUtilisateur, final MainActivity context)
    {{
        database.collection("Utilisateur").whereEqualTo("nom",unUtilisateur.getNom()).whereEqualTo("motDePasse",unUtilisateur.getMotDePasse()).get(Source.DEFAULT).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.getResult().size() == 1) {
                    Log.d("affichage", ""+task.getResult().size());
                    DocumentSnapshot result = task.getResult().getDocuments().get(0);
                    Log.d("succes affichage", result.getId() + " => " + result.get("nom"));
                    context.ConnectSucess(result.getId());
                 } else {
                    Log.w("erreur affichage", "Error getting documents.", task.getException());
                }
            }
        });
        /*
        database.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });*/
    }}


}
