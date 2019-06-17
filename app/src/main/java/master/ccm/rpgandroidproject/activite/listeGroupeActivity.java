package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import master.ccm.rpgandroidproject.R;

public class listeGroupeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_groupe);
    }

    public void onClickRetour(View view) {
        finish();
    }

    public void onClickCreerGroupe(View view) {
        Intent formCreateGroupe = new Intent(this,formCreateGroupeActivity.class);
        startActivity(formCreateGroupe);
    }
}
