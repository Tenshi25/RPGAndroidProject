package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import master.ccm.rpgandroidproject.Entity.Item;
import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.Entity.StaticUtilisateurInfo;
import master.ccm.rpgandroidproject.R;
import master.ccm.rpgandroidproject.manager.BDDManager;

public class page_inventaire extends AppCompatActivity {
    private ListView maListItem;
    private List<Item> listItem;
    private Item[] tabItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_inventaire);

        maListItem=findViewById(R.id.id_listViewItem);

        BDDManager unBDDManager =  new BDDManager();
        Log.i("ApellSelectAllItem","Voici votre inventaire");
        unBDDManager.selectAllItemInventaire(this, StaticUtilisateurInfo.getInstance().getPersonnageCourant());
        maListItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                maListItem.getChildAt(position).setBackgroundColor(Color.RED);
                //AffichePersonnageClicker(personnageSelectionner);

            }

        });

    }
    public void ModifSucess (){

    }

    public void RemplirListeItem(ArrayList<Item> p_listItem) {

        listItem=p_listItem;
        int cpt = 0;
        tabItem = new Item[p_listItem.size()];
        Log.i("logNomTailleTabItem","taille : "+ tabItem.length);

        for (Item unItem : listItem) {
            tabItem[cpt] = unItem;
            //    tableauChaines[cpt] = unPersonnage.getNom();

            //tableauChaines.add(unPersonnage.getNom());
            Log.i("logNomForItem",cpt+". nom : "+ unItem.getNom());
            cpt++;
        }

        ArrayAdapter<Item> monArrayAdapter = new ArrayAdapter<Item>(this, R.layout.descripteur_de_ligne2, tabItem){
            private int vraiPosition=0;
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.i("logNomPos", "pos : "+ position);

                Item item = tabItem[position];

                if(convertView == null){
                    convertView = getLayoutInflater()
                            .inflate(R.layout.descripteur_de_ligne2, parent, false);
                }
                TextView nomPerso = (TextView) convertView.findViewById(R.id.tv_nom_perso);
                TextView prenomPerso = (TextView) convertView.findViewById(R.id.tv_niv_perso);
                //ImageButton modifPerso = (ImageButton) convertView.findViewById(R.id.bt_modifPerso);
                //ImageButton SupprPerso = (ImageButton) convertView.findViewById(R.id.bt_supprPerso);
                nomPerso.setText(item.getNom());
                //prenomPerso.setText(perso.getPrenom());
                Log.i("logNomPerso","logNomPerso : "+item.getNom());
                //
                vraiPosition++;
                return convertView;
            }
        };
        maListItem.setAdapter(monArrayAdapter);
    }
    public void onClickSupprimer(View view) {
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        Toast.makeText(this,"position : " + position ,Toast.LENGTH_SHORT).show();
        Item ItemASupprimer = listItem.get(position);
        Toast.makeText(this,"Item à supprimer : id : "+ItemASupprimer.getId() ,Toast.LENGTH_SHORT).show();
        BDDManager unBDDManager =  new BDDManager();
        unBDDManager.SupprItemInventaire(this,ItemASupprimer);
        listItem.remove(ItemASupprimer);
        int cpt = 0;
        tabItem = new Item[listItem.size()];

        for (Item unItem : listItem) {
            tabItem[cpt] = unItem;
            cpt++;
        }
    }
    public void onClickRetour(View view) {
        Intent monIntent = new Intent (this, MenuPersonnage.class);
        startActivity(monIntent);
        finish();
    }

    public void refresh() {
        Intent monIntent = new Intent (this, page_inventaire.class);
        startActivity(monIntent);
    }
}
