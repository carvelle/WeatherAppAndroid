package za.co.dvt.helpers;

import android.content.Context;
import android.os.Handler;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sibusiso on 2017/09/17.
 */

public class CronJobRunner {

    Handler handler;

    public CronJobRunner(Handler handler) {
        this.handler = handler;
    }

    public void QueueJob(int seconds, Runnable mRunnable) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new QueuedJobRunner(mRunnable), 0, seconds * 1000);
    }

    public class QueuedJobRunner extends TimerTask {
        Runnable mRunnable;
        public QueuedJobRunner(Runnable mRunnable) {
            this.mRunnable = mRunnable;
        }

        @Override
        public void run() {
            handler.post(mRunnable);
        }
    }
}
