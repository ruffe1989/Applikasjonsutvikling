package no.usn.vikestad.largeimagedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        //Finn bredden på skjerm i pixler
        Display display = getWindowManager().getDefaultDisplay();
        int displayWidth = display.getWidth();
        //Finn dimensjoner på bildet, uten å laste inn
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(),R.drawable.largeimage);
        int width = options.outWidth;
        if (width > displayWidth){
            //Beregn forhold mellom bildebredde og skjermbredde
            int widthRatio = Math.round((float) width/(float) displayWidth);
            options.inSampleSize = widthRatio;
        }
        options.inJustDecodeBounds = false;
        Bitmap scaledBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.largeimage, options);
        imageView.setImageBitmap(scaledBitmap);
    }

    public void refreshImage(View view) {
    }
}
