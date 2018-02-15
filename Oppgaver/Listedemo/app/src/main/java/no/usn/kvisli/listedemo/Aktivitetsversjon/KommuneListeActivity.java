package no.usn.kvisli.listedemo.Aktivitetsversjon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import no.usn.kvisli.listedemo.Aktivitetsversjon.MainActivity;
import no.usn.kvisli.listedemo.Kommune;
import no.usn.kvisli.listedemo.KommuneListAdapter;
import no.usn.kvisli.listedemo.R;

public class KommuneListeActivity extends AppCompatActivity {

    private ArrayList<Kommune> kommuneListe = new ArrayList<Kommune>();
    private RecyclerView kommuneRecyclerView;
    private KommuneListAdapter kommuneAdapter;

    private String lesKommuneDataFraFil() {
        InputStream is;
        BufferedReader reader;
        String enLinje;
        StringBuilder heleFilen = new StringBuilder();
        try {
            is = getResources().openRawResource(R.raw.kommunedata);
            reader = new BufferedReader(new InputStreamReader(is));
            while ((enLinje=reader.readLine())!=null) {
                heleFilen = heleFilen.append(enLinje);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return heleFilen.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kommune_liste);

        kommuneRecyclerView = findViewById(R.id.recyclerKommuneView);

        String kommuneData = lesKommuneDataFraFil();
        try {
            kommuneListe = Kommune.lagKommuneListe(kommuneData);
        }
        catch (Exception e) {
            MainActivity.visSnackBar(kommuneRecyclerView,
                    "FEIL: Kan ikke lage kommuneliste.");
        }

        kommuneAdapter = new KommuneListAdapter(this, kommuneListe);
        kommuneRecyclerView.setAdapter(kommuneAdapter);
        kommuneRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
