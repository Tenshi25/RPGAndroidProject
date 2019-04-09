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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.Entity.Utilisateur;
import master.ccm.rpgandroidproject.activite.Inscription;
import master.ccm.rpgandroidproject.activite.MainActivity;

public class BDDManager {
    private static FirebaseFirestore database = FirebaseFirestore.getInstance();
    private static boolean utilisateurExist;

    public static void setUtilisateurExist(boolean utilisateurExist) {
        BDDManager.utilisateurExist = utilisateurExist;
    }
    public void VerifExistUtilisateur (final Utilisateur unUtilisateur, final Inscription context)
    {
        utilisateurExist=false;
        database.collection("Utilisateur").whereEqualTo("nom",unUtilisateur.getNom()).get(Source.DEFAULT).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.getResult().size() >= 1) {
                    Log.i("task.getResult()",""+task.getResult().size());
                    Log.i("selectUtilisateur","Le nom existe déjà");
                    BDDManager unbddManager = new BDDManager();
                    unbddManager.setUtilisateurExist(true);
                    Log.i("utilisateurExist",""+utilisateurExist);
                    context.AfterInsertSuccessOAuth (task.getResult().getDocuments().get(0).getId(), unUtilisateur.getNom());
                }else{
                    InsertDatastoreUtilisateurOAuth(unUtilisateur, context);
                }

            }
        });

    }

    public void VerifUtilisateur (final Utilisateur unUtilisateur, final Inscription context)
    {
        utilisateurExist=false;
        database.collection("Utilisateur").whereEqualTo("nom",unUtilisateur.getNom()).get(Source.DEFAULT).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.getResult().size() >= 1) {
                    Log.i("task.getResult()",""+task.getResult().size());
                    Log.i("selectUtilisateur","Le nom existe déjà");
                    context.InsertFailNomExist();
                    BDDManager unbddManager = new BDDManager();
                    unbddManager.setUtilisateurExist(true);
                    Log.i("utilisateurExist",""+utilisateurExist);
                }else{
                    InsertDatastoreUtilisateur(unUtilisateur, context);
                }

            }
        });
    }
    public void InsertDatastoreUtilisateurOAuth (final Utilisateur unUtilisateur, final Inscription context)
    {
        Log.i("utilisateurExist2",""+utilisateurExist);
        Map<String, Object> utilisateurMap = new HashMap<>();
        utilisateurMap.put("nom", unUtilisateur.getNom());


        database.collection("Utilisateur").add(utilisateurMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(utilisateurExist==false){
                            Log.i("ajoutUtilisateur","L'utilisateur à été Ajouter");

                            context.AfterInsertSuccessOAuth(task.getResult().getId(),unUtilisateur.getNom());
                        }
                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("ajoutUtilisateurErreur","Erreur L'utilisateur n'a pas été ajouter correctement ");
            }
        });
    }
    public void InsertDatastoreUtilisateur (final Utilisateur unUtilisateur, final Inscription context)
    {
        Log.i("utilisateurExist2",""+utilisateurExist);
        Map<String, Object> utilisateurMap = new HashMap<>();
        utilisateurMap.put("nom", unUtilisateur.getNom());
        utilisateurMap.put("motDePasse", unUtilisateur.getMotDePasse());


        database.collection("Utilisateur").add(utilisateurMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(utilisateurExist==false){
                            Log.i("ajoutUtilisateur","L'utilisateur à été Ajouter");

                            context.InsertSuccess(task.getResult().getId(),unUtilisateur.getNom());
                        }
                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("ajoutUtilisateurErreur","Erreur L'utilisateur n'a pas été ajouter correctement ");
            }
        });
    }
    public void AjouterUtilisateur (Utilisateur unUtilisateur, final Inscription context)
    {
        VerifUtilisateur ( unUtilisateur,context);
        //Thread.sleep(10000);
    }
    public void AjouterUtilisateurOAuth (Utilisateur unUtilisateur, final Inscription context)
    {
        VerifExistUtilisateur( unUtilisateur,context);

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
                    context.ConnectSucess(result.getId(),result.get("nom").toString());
                 } else {
                    context.ConnectionFailed();
                    Log.w("erreur affichage", "Error getting documents.", task.getException());
                }
            }
        });
    }}
        public void selectAllPersonnage(String nomCollection){
            database.collection(nomCollection).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        ArrayList listPersonnages = new ArrayList();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Personnage unPersonnage = new Personnage();
                            // il faut ajouter tout les attribut du personnage
                            Log.d("SelectAll", document.getId() + " => " + document.getData());
                        }
                    } else {
                        Log.w("selectAll", "Error getting documents.", task.getException());
                    }
                }
            });
        }
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



}
