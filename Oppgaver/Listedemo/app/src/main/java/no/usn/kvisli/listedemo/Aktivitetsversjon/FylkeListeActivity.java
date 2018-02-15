package no.usn.kvisli.listedemo.Aktivitetsversjon;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import no.usn.kvisli.listedemo.Aktivitetsversjon.MainActivity;
import no.usn.kvisli.listedemo.R;

public class FylkeListeActivity extends AppCompatActivity {

    private String[] fylkeTabell;
    private ListView fylkeListen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fylke_liste);

        // Hent tabell fra Intent
        Intent mottatt=getIntent();
        if (mottatt!=null) {
            Bundle mottatt_bundle = mottatt.getBundleExtra(MainActivity.FYLKE_BUNDLE_NAVN);
            if (mottatt_bundle != null)
                fylkeTabell=mottatt_bundle.getStringArray(MainActivity.FYLKE_TAB_NAVN);
        }
        ArrayAdapter<String> fylkeAdapter =
                new ArrayAdapter<String>(this,
                        R.layout.fylke_list_item, fylkeTabell);
        fylkeListen = findViewById(R.id.fylke_liste);
        if (fylkeListen!=null)
            fylkeListen.setAdapter(fylkeAdapter);

        fylkeListen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                //visFylkeWeb(fylkeTabell[position]);
                returnerFylke(fylkeTabell[position]);
            }
        });
    }

    private void visFylkeWeb(String fylke) {
        String url = "https://no.wikipedia.org/wiki/" + fylke.replace(" ","_");
        Uri uri = Uri.parse(url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, uri);
        if (webIntent.resolveActivity(getPackageManager()) != null)
            startActivity(webIntent);
    }
    public static final String VALGTFYLKE = "no.usn.kvisli.listedemo.valgt_fylke";
    private void returnerFylke(String fylke) {
        Intent resultat = new Intent();
        resultat.putExtra(VALGTFYLKE,fylke);
        setResult(Activity.RESULT_OK, resultat);
        finish();
    }
}
