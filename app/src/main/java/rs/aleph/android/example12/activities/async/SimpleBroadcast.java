package rs.aleph.android.example12.activities.async;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import rs.aleph.android.example12.R;
import rs.aleph.android.example12.activities.tools.ReviewerTools;

import static rs.aleph.android.example12.activities.MainActivity.COMMENTS;
import static rs.aleph.android.example12.activities.MainActivity.INTERNET_CONNECTION;
import static rs.aleph.android.example12.activities.MainActivity.START_SYNC;

/**
 * Created by hp-zbook-g3 on 21-Feb-18.
 */

public class SimpleBroadcast extends BroadcastReceiver {

    private static int notificationID = 1;
    private static int nmessageID = 2;


    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(START_SYNC)) {
            int connectionType = intent.getIntExtra(INTERNET_CONNECTION, 0);
            makeNotification(context, connectionType);

            //todo add file to list in main activity
            ReviewerTools.readFile(context);
        }

        if(intent.getAction().equals(COMMENTS)){
            String message = intent.getStringExtra("our message");
            messageNotification(context, message);
        }
    }

    public void makeNotification(Context context, int connectionType) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);

        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_wifi_black_24dp);

        Log.i("MY_ANDROID_APP", "NOtif");

        if (connectionType == ReviewerTools.TYPE_NOT_CONNECTED) {
            mBuilder.setSmallIcon(R.drawable.ic_perm_scan_wifi_black_24dp);
            mBuilder.setContentTitle("Something went wrong");
            mBuilder.setContentText("There is NO INTERNET connection!");

        } else if (connectionType == ReviewerTools.TYPE_MOBILE) {
            mBuilder.setSmallIcon(R.drawable.ic_compare_arrows_black_24dp);
            mBuilder.setContentTitle("Mobile data is on");
            mBuilder.setContentText("You might be charged additional cost");
        } else if (connectionType == ReviewerTools.TYPE_WIFI) {
            mBuilder.setSmallIcon(R.drawable.ic_wifi_black_24dp);
            mBuilder.setContentTitle("WI-FI available");
            mBuilder.setContentText("Surf All you like!");
        }

        mBuilder.setLargeIcon(bm);
        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(notificationID, mBuilder.build());

    }

    public void messageNotification(Context context, String message){

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);

        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_message_black_24dp);

        mBuilder.setSmallIcon(R.drawable.ic_perm_scan_wifi_black_24dp);
        mBuilder.setContentTitle("Message");
        mBuilder.setContentText(message);


        mBuilder.setLargeIcon(bm);
        mNotificationManager.notify(nmessageID, mBuilder.build());
    }
}
