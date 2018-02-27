package rs.aleph.android.example12.activities.async;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import rs.aleph.android.example12.activities.tools.ReviewerTools;

import static rs.aleph.android.example12.activities.MainActivity.INTERNET_CONNECTION;
import static rs.aleph.android.example12.activities.MainActivity.MESSAGE;
import static rs.aleph.android.example12.activities.MainActivity.NOTIFY;

/**
 * Created by hp-zbook-g3 on 21-Feb-18.
 */

public class SimpleService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int connectionType = intent.getIntExtra(INTERNET_CONNECTION, 0);
        String message = intent.getStringExtra(MESSAGE);
        boolean isNotify = intent.getBooleanExtra(NOTIFY, false);

        if (isNotify) {
            if (connectionType == ReviewerTools.TYPE_MOBILE || connectionType == ReviewerTools.TYPE_WIFI) {
                new SimpleAsyncTask(getApplicationContext()).execute(connectionType);
            }

            if (message != null) {
                new AsyncMessages(getApplicationContext()).execute(message);
            }

        }
        stopSelf();

        return START_NOT_STICKY;
    }
}
