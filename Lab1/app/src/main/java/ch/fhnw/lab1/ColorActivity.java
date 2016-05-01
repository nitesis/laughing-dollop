package ch.fhnw.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
    }

    class ColorTask extends AsyncTask<Void, RGBColor, Void> {
        private boolean error;
        private RGBColor color;
    }

    class RGBColor {
        int r;
        int g;
        int b;
    }
}
