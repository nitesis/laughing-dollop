package ch.fhnw.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // R. ist die Ressource auf den Layout File, dient dazu, Ressourcen anzusprechen
        // R(essourcen) File wird automatisch von Studio generiert, damit die ids (xml) schneller gefunden werden
        setContentView(R.layout.activity_main);
        Log.d("HelloWorld", "onCreate() called");
    }


    protected void onStart() {
        super.onStart(); // must be called

        Log.d("HelloWorld", "onStart() called"); // Use android.util.Log
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("HelloWorld", "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("HelloWorld", "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("HelloWorld", "onStop() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("HelloWorld", "onRestart() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("HelloWorld", "onDestroy() called");
    }


     public void onButtonClick (View v) {

          startActivity(new Intent("ch.fhnw.lab1.MESSAGE"));
         // call activity using an explicit intent
         //Intent intent = new Intent(this, MessageActivity.class);
         //startActivity(intent);
     }
}
