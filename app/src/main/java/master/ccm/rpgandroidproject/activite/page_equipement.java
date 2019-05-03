package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import master.ccm.rpgandroidproject.Entity.Item;
import master.ccm.rpgandroidproject.Entity.Personnage;
import master.ccm.rpgandroidproject.R;

public class page_equipement extends AppCompatActivity {
    private Spinner maListArme;
    private List<Item> listArme;

    private Spinner maListArmure;
    private List<Item> listArmure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_equipement);

        maListArme = findViewById(R.id.bt_comboArme);
        maListArmure = findViewById(R.id.bt_comboArmure);
    }

    public void ModifSucess (){

    }

    public void onClickComboArme(View view) {
    }

    public void onClickComboArmure(View view) {
    }

    public void onClickRetour(View view) {
        Intent monIntent = new Intent (this, MenuPersonnage.class);
        startActivity(monIntent);
        finish();
    }
}
