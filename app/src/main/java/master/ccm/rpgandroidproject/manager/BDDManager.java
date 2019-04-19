package master.ccm.rpgandroidproject.manager;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.Entity.Utilisateur;
import master.ccm.rpgandroidproject.activite.Inscription;
import master.ccm.rpgandroidproject.activite.MainActivity;
import master.ccm.rpgandroidproject.activite.pageAccueil;
import master.ccm.rpgandroidproject.activite.pageAjoutPersonnage;

public class BDDManager {
    private static FirebaseFirestore database = FirebaseFirestore.getInstance();
    private static boolean utilisateurExist;
    private static boolean PersonnageExist;

    private static void setUtilisateurExist(boolean utilisateurExist) {
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
                        if(!utilisateurExist){
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
                        if(!utilisateurExist){
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
        public void selectAllPersonnage(final pageAccueil context){
            database.collection("Personnage").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        //if (!task.getResult().isEmpty()) {

                            int cpt = 0;
                            ArrayList<Personnage> listPersonnages = new ArrayList<Personnage>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Personnage unPersonnage = new Personnage();
                                unPersonnage.setId(task.getResult().getDocuments().get(cpt).getId());
                                unPersonnage.setNom(task.getResult().getDocuments().get(cpt).get("nom").toString());
                                unPersonnage.setPrenom(task.getResult().getDocuments().get(cpt).get("prenom").toString());
                                unPersonnage.setNiveau(Integer.parseInt(task.getResult().getDocuments().get(cpt).get("niveau").toString()));
                                unPersonnage.setExperience(Integer.parseInt(task.getResult().getDocuments().get(cpt).get("experience").toString()));
                                unPersonnage.setExpNiveauSuivant(Integer.parseInt(task.getResult().getDocuments().get(cpt).get("expNiveauSuivant").toString()));
                                unPersonnage.setPv(Integer.parseInt(task.getResult().getDocuments().get(cpt).get("pv").toString()));
                                unPersonnage.setPvMax(Integer.parseInt(task.getResult().getDocuments().get(cpt).get("pvMax").toString()));
                                unPersonnage.setClasse(task.getResult().getDocuments().get(cpt).get("classePersonnage").toString());

                                listPersonnages.add(unPersonnage);
                                Log.i("Perso", "Nom :" + unPersonnage.getPrenom() + "Prenom : " + unPersonnage.getPrenom());
                                // il faut ajouter tout les attribut du personnage
                                Log.d("SelectAll", document.getId() + " => " + document.getData());
                                cpt = cpt + 1;


                            selectAllPersonnageFini(listPersonnages, context);
                        }
                    } else {
                        Log.w("selectAll", "Error getting documents.", task.getException());
                    }
                }
            });
        }
        public void selectAllPersonnageFini(ArrayList<Personnage> listPersonnages,pageAccueil context){
           context.RemplirListepersonnage(listPersonnages);

        }
    public void InsertDatastorePersonnage (final Utilisateur unUtilisateur ,final Personnage unPersonnage, final pageAjoutPersonnage context)
    {
        Map<String, Object> PersonnageMap = new HashMap<>();
        PersonnageMap.put("idUtilisateur", unUtilisateur.getId());
        PersonnageMap.put("nom", unPersonnage.getNom());
        PersonnageMap.put("prenom", unPersonnage.getPrenom());
        PersonnageMap.put("niveau", unPersonnage.getNiveau());
        PersonnageMap.put("experience", unPersonnage.getExperience());
        PersonnageMap.put("expNiveauSuivant", unPersonnage.getExpNiveauSuivant());
        PersonnageMap.put("classePersonnage", unPersonnage.getClasse());
        PersonnageMap.put("pv", unPersonnage.getPv());
        PersonnageMap.put("pvMax", unPersonnage.getPvMax());


        database.collection("Personnage").add(PersonnageMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        //a changer
                        if(!PersonnageExist){
                            Log.i("ajoutPersonnage","Le Personnage à été Ajouter");

                            context.InsertSuccess();
                        }
                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("ajoutPersonnageErreur","Erreur le personnage n'a pas été ajouter correctement ");
            }
        });
    }
    //verif Personnage
    public void VerifPersonnage (final Utilisateur unUtilisateur, final pageAjoutPersonnage context, final Personnage unPersonnage)
    {
        PersonnageExist=false;
        database.collection("Personnage").whereEqualTo("idUtilisateur",unUtilisateur.getId()).whereEqualTo("nom",unPersonnage.getNom()).whereEqualTo("prenom",unPersonnage.getPrenom()).get(Source.DEFAULT).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.getResult().size() >= 1) {
                    Log.i("task.getResult()",""+task.getResult().size());
                    Log.i("selectPersonnage","Le personnage existe déjà");
                    context.InsertFailPersonnageExist();
                    BDDManager unbddManager = new BDDManager();
                    unbddManager.setUtilisateurExist(true);
                    Log.i("PersonnageExist",""+PersonnageExist);
                }else{
                    InsertDatastorePersonnage(unUtilisateur,unPersonnage, context);
                }

            }
        });
    }
    public void AjoutPersonnage(Utilisateur unUtilisateur, final pageAjoutPersonnage context,Personnage unPersonnage){
        VerifPersonnage(unUtilisateur,context,unPersonnage);
        //InsertDatastorePersonnage(unUtilisateur,unPersonnage,context);
    }
    public void SupprPersonnage( final pageAccueil context,Personnage unPersonnage){
        database.collection("Personnage").document(unPersonnage.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("supprPersonnage", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("supprPersonnage", "Error deleting document", e);
                    }
                });

        //InsertDatastorePersonnage(unUtilisateur,unPersonnage,context);
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
