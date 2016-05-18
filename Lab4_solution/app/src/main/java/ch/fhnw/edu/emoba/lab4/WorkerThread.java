package ch.fhnw.edu.emoba.lab4;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class WorkerThread extends HandlerThread {
    private static final String TAG = "ColorThread";

    public static final String COLOR_VALUE = "ColorValue";
    public static final int STOP_COLOR_THREAD = 0;
    public static final int NEW_COLOR = 1;

    private MainActivity activity;
    private ScheduledFuture workerTask;

    public WorkerThread(String name, MainActivity activity) {
        super(name);
        this.activity = activity;
        start();
    }

    @Override
    protected void onLooperPrepared() {
        // Takes the Looper of the current thread.
        // This is equivalent to "new Handler(getLopper()) {..."
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == STOP_COLOR_THREAD) {
                    workerTask.cancel(true);
                    Log.d(TAG, "WorkerThread stopped: " + Thread.currentThread() );
                }
            }
        };
        activity.setWorkerThreadHandler(handler);
        Thread thread = new ColoringThread(activity.getMainThreadHandler());
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        // Run thread each second
        workerTask = scheduler.scheduleAtFixedRate(thread, 0, 1, TimeUnit.SECONDS);
        Log.d(TAG, "WorkerThread started: " + Thread.currentThread());
    }
}

class ColoringThread extends Thread {
    private static String TAG = "ColorWorkerThread";
    private final Handler mainThreadHandler;

    public ColoringThread(Handler mainThreadHandler) {
        this.mainThreadHandler = mainThreadHandler;
    }

    @Override
    public void run() {
        RGBColor actColor = new RGBColor();
        actColor.r = (int) Math.round(Math.random() * 255);
        actColor.g = (int) Math.round(Math.random() * 255);
        actColor.b = (int) Math.round(Math.random() * 255);
        Message msg = mainThreadHandler.obtainMessage();
        msg.what = WorkerThread.NEW_COLOR;
        Bundle data = new Bundle();
        data.putSerializable(WorkerThread.COLOR_VALUE, actColor);
        msg.setData(data);
        msg.sendToTarget();
    }
}
