package rs.aleph.android.example12.activities.tools;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import rs.aleph.android.example12.R;

import static rs.aleph.android.example12.activities.MainActivity.FILE_NAME;

/**
 * Created by hp-zbook-g3 on 21-Feb-18.
 */

public class ReviewerTools {

    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectionType(Integer type){
        switch (type){
            case 1:
                return "WIFI";
            case 2:
                return "Mobilni internet";
            default:
                return "";
        }
    }

    public static int calculateTimeTillNextSync(int minutes){
        return 1000 * 60 * minutes;
    }

    public static void writeToFile(String data,Context context, String filename) {

        try {
            FileOutputStream outputStream = context.openFileOutput(filename, Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    public static String readFromFile(Context context, String file){

        String returnString = "";

        try {
            InputStream is = context.openFileInput(file);

            if(is != null ){
                InputStreamReader reader = new InputStreamReader(is);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String receiveingString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while((receiveingString = bufferedReader.readLine()) != null){
                    stringBuilder.append(receiveingString);

                }
                is.close();

                returnString = stringBuilder.toString();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnString;
    }

    public static void readFile(Context context){
        String text = ReviewerTools.readFromFile(context, FILE_NAME);
        String[] data = text.split("\n");

        fillAdapter(data, context);
    }

    public static boolean isFileExists(Context context, String filename){
        File file = new File(context.getApplicationContext().getFilesDir(),filename);
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

    public static void fillAdapter(String[] products, Context context){
        // Create an ArrayAdaptar from the array of Strings
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.list_item, products);
        ListView listView = (ListView) ((Activity)context).findViewById(R.id.listViewMeals);

        // Assign adapter to ListView
        listView.setAdapter(adapter);
    }
}
