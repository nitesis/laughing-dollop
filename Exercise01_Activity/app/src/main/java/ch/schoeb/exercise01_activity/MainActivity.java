package ch.schoeb.exercise01_activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Find button which is defined in the XML
        Button b = (Button)findViewById(R.id.buttonShowText);
        // TODO: Add an OnClickListener to react on the user Click
        b.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                     // TODO: Use Toast.makeText(...).show() to show a Toast
                                     Toast.makeText(MainActivity.this, "Hello FHNW", Toast.LENGTH_LONG).show();
                                 }
                             });

    }

}
