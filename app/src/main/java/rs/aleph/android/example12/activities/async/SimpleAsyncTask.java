package rs.aleph.android.example12.activities.async;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import static rs.aleph.android.example12.activities.MainActivity.INTERNET_CONNECTION;
import static rs.aleph.android.example12.activities.MainActivity.START_SYNC;

/**
 * Created by hp-zbook-g3 on 21-Feb-18.
 */

public class SimpleAsyncTask extends AsyncTask<Integer, Void, Integer> {

    Context context;


    public SimpleAsyncTask(Context c){
        context = c;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Toast.makeText(context, "AsyncTask Started", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Integer doInBackground(Integer... params) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return params[0];
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        Toast.makeText(context, "Async Finnished. Notify Broadcast!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(START_SYNC);
        intent.putExtra(INTERNET_CONNECTION, integer);
        context.sendBroadcast(intent);

    }
}
