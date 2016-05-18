package ch.fhnw.edu.emoba.lab4;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ColorService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static String TAG = "ColorService";
    private static final String COLOR_VALUE = "COLOR";
    private static final String NOTIFICATION = "NEW_COLOR";

    private RGBColor color = new RGBColor();


    public ColorService() {
        super("ColorService");
    }

    public ColorService(String name) { super(name);}


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Handle intent on thread" + Thread.currentThread().getName());
        color.r = (int) Math.round(Math.random() * 255);
        color.g = (int) Math.round(Math.random() * 255);
        color.b = (int) Math.round(Math.random() * 255);


        // Intent colorMessage = new Intent(NOTIFICATION);
        // colorMessage.putExtra(COLOR_VALUE, color);
        // LocalBroadcastManager.getInstance(this).sendBroadcast(colorMessage);

        Log.d(TAG, "color is " + color);
    }
}
