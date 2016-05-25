package ch.fhnw.edu.emoba.lab6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private final String KEY_MYVALUE= "value";
    private int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        long start = System.nanoTime();
        super.onCreate(savedInstanceState);
        //Log.d("HelloWorld", "onCreate() called " + savedInstanceState);
        setContentView(R.layout.activity_main);
        long duration = System.nanoTime() - start;
        Log.d(TAG, "Duration " + duration + " nanoseconds");
    }

    public void onButtonClick(View v) {
        // call activity using an implicit intent
//        Intent intent = new Intent("ch.fhnw.edu.helloworld.HELLOWORLD");
        // call activity using an explicit intent
    	Intent intent = new Intent(this, MessageActivity.class);

        startActivity(intent);
    }

}
