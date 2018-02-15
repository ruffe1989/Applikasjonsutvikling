package no.usn.kvisli.listedemo;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Jon on 01.02.2018.
 */

public class BildeAdapter extends BaseAdapter {
    // Tabell med referanser til bilderessursene
    private Integer[] mineBilder = {
            R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4,
            R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2,
            R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7, R.drawable.sample_0,
            R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6,
            R.drawable.sample_7
    };

    private Context minContext;
    // Kontruktør som tar vare på Aktivitetens Context-objekt for Adapter-klassen
    public BildeAdapter(Context c) {  minContext = c;  }

    public int getCount()               { return mineBilder.length;   }
    public Object getItem(int position) { return mineBilder[position];  }
    public long getItemId(int position) { return position;     }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView currentImageView;
        // Første gang metoden kalles vil convertView være null
        // Senere "gjenbrukes" convertView-objektet
        if (convertView == null) {
            // Lag et nytt ImageView objekt
            currentImageView = new ImageView(minContext);
            // Sett høyde og bredde for ImageView-objektet - hvert bilde tilpasses denne størrelsen
            currentImageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            // Angir hvordan bildet skal skaleres/beskjæres for å passe til ImageView'et
            currentImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);  // Beskjær mot senter av bildet
            // Padding rundt ImageView-objektet
            currentImageView.setPadding(2, 2, 2, 2);
        } else {
            // Gjenbruker View-objektet fra forrige metodekall
            currentImageView = (ImageView) convertView;
        }
        // Bruk bildet i riktig position fra tabellen som bilderessurs for ImageViewet
        currentImageView.setImageResource(mineBilder[position]);
        //  Returner ImageView-objektet med bildefilen satt
        return currentImageView;
    } // End of getView()
} // End of class BildeAdapter

