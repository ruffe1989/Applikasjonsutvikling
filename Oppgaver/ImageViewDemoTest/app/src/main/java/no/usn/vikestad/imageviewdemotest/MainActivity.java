package no.usn.vikestad.imageviewdemotest;


import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    ImageView imageView;
    private int numClicks=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        imageView = (ImageView) findViewById(R.id.imageView);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioCenter:
                        imageView.setScaleType(ImageView.ScaleType.CENTER);
                        break;
                    case R.id.radioCenterCrop:
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        break;
                    case R.id.radioCenterInside:
                        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        break;
                    case R.id.radioFitCenter:
                        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        break;
                    case R.id.radioFitStart:
                        imageView.setScaleType(ImageView.ScaleType.FIT_START);
                        break;
                    case R.id.radioFitEnd:
                        imageView.setScaleType(ImageView.ScaleType.FIT_END);
                        break;
                    case R.id.radioFitXY:
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        break;
                }
            }
        });
    }
    // Lyttemetode for ROTATE knappen
    public void rotateImage(View v) {
        float vinkel=10f*(++numClicks);
        //rotate(imageView, vinkel);
        rotateWithMatrix(imageView, vinkel);
        Snackbar.make(findViewById(R.id.imageView),"Vinkel="+vinkel, Snackbar.LENGTH_SHORT).show();
    }

    public void rotate(View view, float degrees) {
        view.setPivotX(view.getWidth()/2);
        view.setPivotY(view.getHeight()/2);
        view.setRotation(degrees);
    }

    public void rotateWithMatrix(ImageView iv, float degrees) {
        Matrix matrix = iv.getImageMatrix();
        iv.setScaleType(ImageView.ScaleType.MATRIX);
        matrix.postRotate(degrees, iv.getWidth()/2, iv.getHeight()/2);
        iv.setImageMatrix(matrix);
    }

    public void draw(View v) {
        Bitmap imageBitmap = Bitmap.createBitmap(imageView.getWidth()/2,
                imageView.getHeight()/2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(imageBitmap);
        float scale = getResources().getDisplayMetrics().density;
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        int x1 = canvas.getWidth()/4;
        int y1 = canvas.getHeight()/4;
        int x2 = x1 + canvas.getWidth()/2;
        int y2 = y1 + canvas.getHeight()/2;
        canvas.drawRect(x1, y1, x2, y2, p);
        p.setColor(Color.BLUE);
        p.setTextSize(24*scale);
        canvas.drawText("Hello", (x1*1.2f), y1+((y2-y1)/2), p);
        imageView.setImageBitmap(imageBitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
