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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import master.ccm.rpgandroidproject.Entity.Item;
import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.Entity.Stats;
import master.ccm.rpgandroidproject.Entity.Utilisateur;
import master.ccm.rpgandroidproject.activite.MenuPersonnage;
import master.ccm.rpgandroidproject.activite.formInscription;
import master.ccm.rpgandroidproject.activite.MainActivity;
import master.ccm.rpgandroidproject.activite.formModifPersonnage;
import master.ccm.rpgandroidproject.activite.pageChoixPerso;
import master.ccm.rpgandroidproject.activite.formAjoutPersonnage;
import master.ccm.rpgandroidproject.activite.page_equipement;
import master.ccm.rpgandroidproject.activite.page_inventaire;

public class BDDManager {
    private static FirebaseFirestore database = FirebaseFirestore.getInstance();
    private static boolean utilisateurExist;
    private static boolean PersonnageExist;

    private static void setUtilisateurExist(boolean utilisateurExist) {
        BDDManager.utilisateurExist = utilisateurExist;

    }
    public void VerifExistUtilisateur (final Utilisateur unUtilisateur, final formInscription context)
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

    public void VerifUtilisateur (final Utilisateur unUtilisateur, final formInscription context)
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
    public void InsertDatastoreUtilisateurOAuth (final Utilisateur unUtilisateur, final formInscription context)
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
    public void InsertDatastoreUtilisateur (final Utilisateur unUtilisateur, final formInscription context)
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
    public void AjouterUtilisateur (Utilisateur unUtilisateur, final formInscription context)
    {
        VerifUtilisateur ( unUtilisateur,context);
        //Thread.sleep(10000);
    }
    public void AjouterUtilisateurOAuth (Utilisateur unUtilisateur, final formInscription context)
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
        public void selectAllPersonnage(final pageChoixPerso context){
            database.collection("Personnage").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        //if (!task.getResult().isEmpty()) {

//                            int cpt = 0;
                            ArrayList<Personnage> listPersonnages = new ArrayList<Personnage>();
                            List<DocumentSnapshot> result = task.getResult().getDocuments();
                            for (DocumentSnapshot document : result) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Personnage unPersonnage = new Personnage();
//                                unPersonnage.setId(task.getResult().getDocuments().get(cpt).getId());
                                unPersonnage.setId(document.getId());
                                unPersonnage.setNom(document.get("nom").toString());
                                unPersonnage.setPrenom(document.get("prenom").toString());
                                unPersonnage.setNiveau(Integer.parseInt(document.get("niveau").toString()));
                                unPersonnage.setExperience(Integer.parseInt(document.get("experience").toString()));
                                unPersonnage.setExpNiveauSuivant(Integer.parseInt(document.get("expNiveauSuivant").toString()));
                                unPersonnage.setPv(Integer.parseInt(document.get("pv").toString()));
                                unPersonnage.setPvMax(Integer.parseInt(document.get("pvMax").toString()));
                                unPersonnage.setClasse(document.get("classePersonnage").toString());

                                listPersonnages.add(unPersonnage);
                                Log.i("logNomPerso", "Nom :" + unPersonnage.getPrenom() + "Prenom : " + unPersonnage.getPrenom());
                                // il faut ajouter tout les attribut du personnage
                                Log.d("logNomSelectAll", document.getId() + " => " + document.getData());
//                                cpt = cpt + 1;


                        }
                        selectAllPersonnageFini(listPersonnages, context);
                    } else {
                        Log.w("selectAll", "Error getting documents.", task.getException());
                    }
                }
            });
        }
        public void selectAllPersonnageFini(ArrayList<Personnage> listPersonnages, pageChoixPerso context){


            context.RemplirListepersonnage(listPersonnages);

        }
    public void InsertDatastorePersonnage (final Utilisateur unUtilisateur ,final Personnage unPersonnage, final formAjoutPersonnage context)
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
    public void VerifPersonnage (final Utilisateur unUtilisateur, final formAjoutPersonnage context, final Personnage unPersonnage)
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
    public void AjoutPersonnage(Utilisateur unUtilisateur, final formAjoutPersonnage context, Personnage unPersonnage){
        VerifPersonnage(unUtilisateur,context,unPersonnage);
        //InsertDatastorePersonnage(unUtilisateur,unPersonnage,context);
    }
    public void SupprPersonnage(final pageChoixPerso context, Personnage unPersonnage){
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
    public void ModifPersonnage (final Personnage unPersonnage, final formModifPersonnage context)
    {
        Map<String, Object> personnageMap = new HashMap<>();
        personnageMap.put("nom", unPersonnage.getNom());
        personnageMap.put("prenom", unPersonnage.getPrenom());


        database.collection("Personnage").document(unPersonnage.getId()).update(personnageMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                            Log.i("modifpersonnage","Le personnage à été modifier");

                            context.ModifSucess();

                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("ModifPersonnage","Erreur le personnage n'a pas été modifier ");
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

/*-------------------------------------------------------------------------------------------------------------------------*/
    /*---------------Partie Item ----------------*/

    //récupération de la liste d'item de l'inventaire du personnage
    //modification  d'un nombre d'item
    //suppression d'un item de l'inventaire
    //
    public void selectAllItem(){
        database.collection("Item").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<Item> listItems = new ArrayList<Item>();
                    List<DocumentSnapshot> result = task.getResult().getDocuments();
                    for (DocumentSnapshot document : result) {
                        Item unItem = new Item();
                        unItem.setId(document.getId());
                        unItem.setNom(document.get("nom").toString());
                        //recuperation de la stats du joueur
                        HashMap<String,Integer> mapItemEffet = new HashMap<String,Integer>();
                        mapItemEffet.put(document.get("stats").toString(),Integer.parseInt(document.get("valeur").toString()));
                        unItem.setEffet(mapItemEffet);
                        unItem.setTypeItem(document.get("TypeItem").toString());
                        listItems.add(unItem);
                        Log.i("logNomItem", "Item :" + unItem.getNom());
                        Log.d("logTenshi", document.getId() + " => " + document.getData());

                    }
                    selectAllItemFini(listItems);
                } else {
                    Log.w("selectAll", "Error getting documents.", task.getException());
                }
            }
        });
    }
    //récupération de la liste d'item de l'inventaire
    public void selectAllItemInventaire(final page_inventaire context,Personnage unPersonnage){
        Log.i("EntrerSelectAllItem",unPersonnage.getId());
        database.collection("Inventaire").whereEqualTo("idPerso",unPersonnage.getId()).get(Source.DEFAULT).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.i("onCompleteSelectAllItem","coucou");
                if (task.isSuccessful()) {
//                    ArrayList<Item> listItems = new ArrayList<Item>();
                    ArrayList<String> listIdItems = new ArrayList<String>();
                    List<DocumentSnapshot> result = task.getResult().getDocuments();
                    for (DocumentSnapshot document : result) {
                        //Item unItem = new Item();
                        //unItem.setId(document.get("idItem"));
                        //recuperation de la stats du joueur
                        Log.i("idItem",""+document.get("idItem"));
                        listIdItems.add((String) document.get("idItem"));
                    }
                    Log.i("SelectAllItemInventaireFini","listIdItem size : "+listIdItems.size());
                    selectAllItemInventaireFini(listIdItems, context);
                } else {
                    Log.w("selectAllInventaireItem", "Error getting documents.", task.getException());
                }
            }
        });
    }
    public void selectAllItemInventaireFini(final ArrayList<String> listIdItem, final page_inventaire context){
        Log.i("SelectAllItemInventaireFini","listIdItem size : "+listIdItem.size());
        database.collection("Item").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.i("onCompleteSelectAllItemInventaireFini","2 eme select");
                if (task.isSuccessful()) {
                    ArrayList<Item> listItems = new ArrayList<Item>();
                    List<DocumentSnapshot> result = task.getResult().getDocuments();
                    for (DocumentSnapshot document : result) {
                        for (String unIdItem : listIdItem){
                            Log.i("unItem","unItem : "+unIdItem);
                            Log.i("unItem","documentgetIdItem : "+document.getId());
                            String iddoc =document.getId();
                            if(unIdItem.equals(iddoc)){
                                Log.i("unItem","dans le if identique : ");
                                Item unItem = new Item();
                                unItem.setId(document.getId());
                                unItem.setNom(document.get("nom").toString());
                                //recuperation de la stats du joueur
                                HashMap<String,Integer> mapItemEffet = new HashMap<String,Integer>();
                                mapItemEffet.put(document.get("Stats").toString(),Integer.parseInt(document.get("valeur").toString()));
                                unItem.setEffet(mapItemEffet);
                                unItem.setTypeItem(document.get("TypeItem").toString());
                                listItems.add(unItem);
                                Log.i("logNomItem", "Item :" + unItem.getNom());
                                Log.d("logTenshiItem", document.getId() + " => " + document.getData());
                                break;
                            }
                        }
                    }
                    context.RemplirListeItem(listItems);

                } else {
                    Log.w("selectAll", "Error getting documents.", task.getException());
                }
            }
        });


    }
    public void selectAllItemFini(ArrayList<Item> listItem){

        StaticUtilisateurInfo.getInstance().setListeItemBase(listItem);

    }





    public void SupprItemObjet(final Context context, Personnage unPersonnage, Item unItem ){
        database.collection("Inventaire").document(unItem.getId())
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
    public void ModifitemObject (final Context context,int nombreActuel,Item unItem )
    {
        HashMap<String, Object> itemMap = new HashMap<>();
        itemMap.put("quantite", unItem.getQuantite());


        database.collection("Inventaire").document(unItem.getId()).update(itemMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Log.i("modifItem","Le nombre d'item à été modifier");
                        switch (context.getClass().toString()){
                            case "page_equipement":
                                ((page_equipement) context).ModifSucess();
                                break;

                            case "page_inventaire":
                                ((page_inventaire) context).ModifSucess();
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
    public void InsertitemInventaire (final Personnage unPersonnage,Item unItem, final Context context)
    {

        Map<String, Object> itemMap = new HashMap<>();
        Map<String, Object> itemEffet = new HashMap<>();
        itemMap.put("idPersonnage", unPersonnage.getId());
        itemMap.put("nom", unItem.getNom());
        itemMap.put("TypeItem", unItem.getTypeItem());
        itemMap.put("quantite", unItem.getQuantite());

        // à finir
        //itemMap.put("valeur", );



        database.collection("Inventaire").add(itemMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                            Log.i("ajoutItem","L'item à été Ajouter");
//
                            //context.InsertSuccess();
                        }


                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("ajoutItemErreur","Erreur l'item n'a pas été ajouter correctement ");
            }
        });
    }

    public void SupprItemInventaire(final page_inventaire page_inventaire, Item itemASupprimer) {
        database.collection("Inventaire").whereEqualTo("idItem",itemASupprimer.getId()).whereEqualTo("idPerso",StaticUtilisateurInfo.getInstance().getPersonnageCourant().getId())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<DocumentSnapshot> result = task.getResult().getDocuments();
                        String idItemASuppr = result.get(0).getId();
                        deleteItem(page_inventaire, idItemASuppr);
                        }});
    }

    private void deleteItem(final page_inventaire page_inventaire, String idItemASuppr) {
        database.collection("Inventaire").document(idItemASuppr)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("supprPersonnage", "DocumentSnapshot successfully deleted!");
                        page_inventaire.refresh();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("supprPersonnage", "Error deleting document", e);
                    }
                });
    }
}
