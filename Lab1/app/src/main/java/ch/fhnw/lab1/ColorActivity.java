package ch.fhnw.lab1;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Date;

public class ColorActivity extends AppCompatActivity {

    private Color c;
    private TextView helloWorldView;
    private AsyncTask<Void, RGBColor, Void> colorTask;


    private void updateHelloWorldView(RGBColor c) {
        helloWorldView.setBackgroundColor(Color.rgb(c.r, c.g, c.b));
        //Log.d("Color: " + c.r + " " + c.g + " " + c.b);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        helloWorldView = (TextView) findViewById(R.id.txtView);
        helloWorldView.setText("Hello World");
        if((savedInstanceState != null) && (savedInstanceState.containsKey(DISPLAY_SERVICE))) {
            colorTask = new ColorTask();
            colorTask.execute();
        }
    }

    class ColorTask extends AsyncTask<Void, RGBColor, Void> {
        private boolean error;
        private RGBColor color;

        @Override
        protected void onPreExecute() {
            error = false;
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Running on Worker Thread
            while (!error && !isCancelled()) {
                try {
                    color.r = (int) Math.round(Math.random() * 255);
                    color.g = (int) Math.round(Math.random() * 255);
                    color.b = (int) Math.round(Math.random() * 255);
                    publishProgress(color);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    error = true;
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(RGBColor... values) {
            // Ruunning on UI Thread
            updateHelloWorldView(values[0]);
        }
    }

    class RGBColor {
        int r;
        int g;
        int b;
    }
}
