package rs.aleph.android.example12.activities.async;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import static rs.aleph.android.example12.activities.MainActivity.MESSAGE;

/**
 * Created by hp-zbook-g3 on 22-Feb-18.
 */

public class AsyncMessages extends AsyncTask<String, Void, String> {
    public static final String COMMENTS = "comments";



    Context context;


    public AsyncMessages(Context c){
        context = c;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return strings[0];
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        //start broadcast with new filter intent
        Intent intent = new Intent(COMMENTS);
        intent.putExtra("our message", s);
        context.sendBroadcast(intent);
    }
}
