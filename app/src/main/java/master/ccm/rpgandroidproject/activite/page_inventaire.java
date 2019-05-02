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

import java.util.ArrayList;
import java.util.List;

import master.ccm.rpgandroidproject.Entity.Item;
import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.R;

public class page_inventaire extends AppCompatActivity {
    private ListView maListItem;
    private List<Item> listItem;
    private Item[] tabItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_inventaire);

        maListItem=findViewById(R.id.id_listViewItem);

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
        Log.i("logNomTailleTabPerso","taille : "+ tabItem.length);

        for (Item unItem : listItem) {
            tabItem[cpt] = unItem;
            //    tableauChaines[cpt] = unPersonnage.getNom();

            //tableauChaines.add(unPersonnage.getNom());
            Log.i("logNomFor",cpt+". nom : "+ unItem.getNom());
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
                            .inflate(R.layout.descripteur_de_ligne, parent, false);
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


    public void onClickRetour(View view) {
        Intent monIntent = new Intent (this, MenuPersonnage.class);
        startActivity(monIntent);
        finish();
    }

}
