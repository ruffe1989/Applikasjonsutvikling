package no.usn.kvisli.listedemo.Aktivitetsversjon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import no.usn.kvisli.listedemo.Aktivitetsversjon.MainActivity;
import no.usn.kvisli.listedemo.BildeAdapter;
import no.usn.kvisli.listedemo.R;

public class BildeGridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilde_grid);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new BildeAdapter(this));

        // Lyttemetode som kalles når bruker klikker på et av View-objektene
        // (rutene) i GridViewet
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                MainActivity.visSnackBar(v, "Bilde i posisjon: " + position);
            }
        });

    }

}
