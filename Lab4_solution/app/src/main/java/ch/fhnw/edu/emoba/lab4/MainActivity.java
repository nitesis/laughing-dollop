package ch.fhnw.edu.emoba.lab4;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends Activity {
	public static final String TAG = "MainActivity";

	private TextView helloWorldView;
//	private Handler workerThreadHandler = null;
    private Handler mainThreadHandler = null;
    private AlarmReceiver alarm;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            RGBColor color = (RGBColor) intent.getSerializableExtra(ColorService.COLOR_VALUE);
            if (color != null) {
                updateHelloWorldView(color);
            }

        }
    }

	private void updateHelloWorldView(RGBColor c) {
		helloWorldView.setBackgroundColor(Color.rgb(c.r,c.g, c.b));
        Log.d(TAG, c.r + " " + c.g + " " + c.b);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		helloWorldView = (TextView) findViewById(R.id.txtView);
		helloWorldView.setText("Hello World");

        alarm = new AlarmReceiver(this);
	}

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(ColorService.NOTIFICATION));

    }

    public void start(View view) {
 //       startService(new Intent(this, ColorService.class));
        if(alarm != null){
            alarm.setAlarm();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        Log.d(TAG, "Successfully unregistered BroadcastReceiver");

    }
}


