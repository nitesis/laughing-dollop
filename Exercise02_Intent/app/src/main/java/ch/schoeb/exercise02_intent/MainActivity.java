package ch.schoeb.exercise02_intent;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Find the button and add a click listener
        Button b = (Button) findViewById(R.id.buttonNavigate);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO: In the OnClickListener create a new Intent which targets your DetailActivity
                // Weil wir oben mit new View.OnClickListener() eine anonyme Klasse erzeugen,
                // muss der Context hier mit MainActivity.this übergeben werden.
                // Sonst zeigt es nicht auf die äussere Klasse
                Intent intent = new Intent(MainActivity.this, TargetActivity.class);
                // TODO: Add custom data (Extras) to your intent
                intent.putExtra("My Extra", "Hallo");
                // TODO: Start DetailActivity and show passed data in a textView on the DetailActivity
                startActivity(intent);
            }
        });



    }
}
