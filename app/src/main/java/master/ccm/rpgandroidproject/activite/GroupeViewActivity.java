package master.ccm.rpgandroidproject.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import master.ccm.rpgandroidproject.R;

public class GroupeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupe_view);
    }

    public void onClickCommencerCombat(View view) {
        Intent formCreateGroupe= new Intent(this,combatBossActivity.class);
        startActivity(formCreateGroupe);
    }

    public void onClickRetour(View view) {
        finish();
    }

    public void UpdateSucess() {
    }
}
