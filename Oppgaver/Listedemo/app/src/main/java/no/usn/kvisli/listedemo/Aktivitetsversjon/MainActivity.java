package no.usn.kvisli.listedemo.Aktivitetsversjon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import no.usn.kvisli.listedemo.R;

public class MainActivity extends AppCompatActivity {

    private String[] fylkeTabell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fylkeTabell=getResources().getStringArray(R.array.norske_fylker);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onFylkerClicked(View view) {
        visFylkeListe();
    }
    public static final String FYLKE_TAB_NAVN="no.usn.kvisli.listedemo.fylke_tab";
    public static final String FYLKE_BUNDLE_NAVN="no.usn.kvisli.listedemo.fylke_bundle";
    public static final int FYLKE_LISTE_REQUEST=1;

    private void visFylkeListe(){
        Intent fylkeIntent = new Intent(this, FylkeListeActivity.class);
        Bundle b = new Bundle();
        b.putStringArray(FYLKE_TAB_NAVN,fylkeTabell);
        fylkeIntent.putExtra(FYLKE_BUNDLE_NAVN, b);
        startActivityForResult(fylkeIntent,FYLKE_LISTE_REQUEST );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FYLKE_LISTE_REQUEST && resultCode == Activity.RESULT_OK) {
            String valgtFylke = data.getStringExtra(FylkeListeActivity.VALGTFYLKE);
            visSnackBar(findViewById(R.id.main_layout), valgtFylke);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static void visSnackBar(View view, String melding) {
        Snackbar sb = Snackbar.make(view, melding, Snackbar.LENGTH_LONG);
        sb.setAction("Action",null);
        sb.show();
    }

    public void onBilderClicked(View view) {
        Intent bildeintent = new Intent(this, BildeGridActivity.class);
        startActivity(bildeintent);
    }

    public void onKommunerClicked(View view) {
        Intent kommuneIntent = new Intent(getApplicationContext(),KommuneListeActivity.class);
        startActivity(kommuneIntent);
    }
}
