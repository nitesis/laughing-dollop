package ch.fhnw.edu.emoba.lab4;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends Activity {
	public static final String TAG = "MainActivity";

	private TextView helloWorldView;
	private Handler workerThreadHandler = null;
    private Handler mainThreadHandler = null;

	private void updateHelloWorldView(RGBColor c) {
		helloWorldView.setBackgroundColor(Color.rgb(c.r,c.g, c.b));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		helloWorldView = (TextView) findViewById(R.id.txtView);
		helloWorldView.setText("Hello World");

//        mainThreadHandler = new MyUIHandler(this);
        this.mainThreadHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case WorkerThread.NEW_COLOR:
                        Bundle data = msg.getData();
                        if (data.containsKey(WorkerThread.COLOR_VALUE)) {
                            final RGBColor color = (RGBColor) data.get(WorkerThread.COLOR_VALUE);
                            updateHelloWorldView(color);
                        }
                        break;
                    default:
                        super.handleMessage(msg);
                }
            }
        };
	}

    public Handler getMainThreadHandler() {
        return mainThreadHandler;
    }

    public void setWorkerThreadHandler(Handler workerThreadHandler) {
        this.workerThreadHandler = workerThreadHandler;
    }

    public void start(View view) {
        if (workerThreadHandler == null) {
            WorkerThread workerThread = new WorkerThread("ColorThread", this);
        }
    }

    public void stop(View view) {
        doStop();
	}

    private void doStop() {
        if (this.workerThreadHandler != null) {
            Message msg = workerThreadHandler.obtainMessage();
            msg.what = WorkerThread.STOP_COLOR_THREAD;
            msg.sendToTarget();
            this.workerThreadHandler = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        doStop();
    }

    private static class MyUIHandler extends Handler {
        private final WeakReference<MainActivity> context;

        MyUIHandler(MainActivity context) {
            this.context = new WeakReference<MainActivity>(context);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WorkerThread.NEW_COLOR:
                    Bundle data = msg.getData();
                    if (data.containsKey(WorkerThread.COLOR_VALUE)) {
                        final RGBColor color = (RGBColor) data.get(WorkerThread.COLOR_VALUE);
                        context.get().updateHelloWorldView(color);
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}


