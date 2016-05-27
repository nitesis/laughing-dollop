package ch.fhnw.edu.emoba.lab6;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private final String KEY_MYVALUE= "value";
    private int value;
    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        long start = System.nanoTime();
        super.onCreate(savedInstanceState);
        //Log.d("HelloWorld", "onCreate() called " + savedInstanceState);
        setContentView(R.layout.activity_main);
        long duration = System.nanoTime() - start;
        Log.d(TAG, "Duration " + duration + " nanoseconds");

        if (findViewById(R.id.helloworld_container) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    public void onButtonClick(View v) {
        if (isTwoPane) {
            FragmentManager fm = getFragmentManager();
            Fragment f = fm.findFragmentById(R.id.helloworld_container);
            if (f == null) {
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.add(R.id.helloworld_container, new MessageFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        } else {
            // call activity using an implicit intent
//        Intent intent = new Intent("ch.fhnw.edu.helloworld.HELLOWORLD");
            // call activity using an explicit intent
            Intent intent = new Intent(this, MessageActivity.class);

            startActivity(intent);
        }

    }

}
