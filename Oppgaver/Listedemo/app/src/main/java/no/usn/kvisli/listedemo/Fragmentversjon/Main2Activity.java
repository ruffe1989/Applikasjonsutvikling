package no.usn.kvisli.listedemo.Fragmentversjon;


import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import no.usn.kvisli.listedemo.R;

public class Main2Activity extends AppCompatActivity implements KnappeFragment.OnMenyButtonListener,FylkeListeFragment.OnFragmentInteractionListener {

    private String[] fylkeTabell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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

        if (findViewById(R.id.fragment_container) != null){
            KnappeFragment knappeFragment = new KnappeFragment();
            knappeFragment.setArguments(getIntent().getExtras());
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, knappeFragment);
            transaction.commit();
        }
    }

    @Override
    public void onMenyButtonClicked(int button) {
        switch (button){
            case R.id.fylkeKnapp : {
                visFylkeliste();
                break;
            }
        }
    }
    public static final String FYLKE_TAB_NAVN="no.usn.kvisli.listedemo.fylke_tab";
    private void visFylkeliste(){
        FylkeListeFragment fylkeFragment = new FylkeListeFragment();
        Bundle args = new Bundle();
        args.putStringArray(FYLKE_TAB_NAVN, fylkeTabell);
        fylkeFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fylkeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
