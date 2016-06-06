package ch.fhnw.edu.emoba.lab4;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by nitesis on 18.05.16.
 */
public class AlarmReceiver extends WakefulBroadcastReceiver {

public AlarmReceiver(Context context) {
    alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    Intent i = new Intent("new.fhnw.edu.emoba.HELLOWORLD");
    alarmIntent = PendingIntent.getBroadcast(context, 0, i, 0);
}

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Alert received");
        Intent service = new Intent(context, ColorService.class);
        startWakefulService(context, service);

    }

    public void setAlarm(MainActivity context) {
      //  nicht unter 1 minute
        alarmMgr.setRepeating(AlarmManager.ELAPSED_REALTIME, 5000, 1000*60, alarmIntent);
        Log.d(TAG, "Successfully st alarm");
    }

    public void cancelAlarm() {
        //  nicht unter 1 minute
        if (alarmMgr != null) {
            alarmMgr.cancel(alarmIntent);
        }
    }
}
