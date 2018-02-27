package rs.aleph.android.example12.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.io.File;
import java.util.Date;

import rs.aleph.android.example12.R;
import rs.aleph.android.example12.activities.async.SimpleBroadcast;
import rs.aleph.android.example12.activities.async.SimpleService;
import rs.aleph.android.example12.activities.fragments.DetailFragment;
import rs.aleph.android.example12.activities.fragments.DialogAboutFragment;
import rs.aleph.android.example12.activities.fragments.MasterFragment;
import rs.aleph.android.example12.activities.tools.ReviewerTools;
import rs.aleph.android.example12.activities.fragments.SettingFragment;

// Each activity extends Activity class
public class MainActivity extends AppCompatActivity implements MasterFragment.OurClickListener {


    boolean landscape;
    int position;
    private String[] titles;
    private DrawerLayout drawerLayout;
    private ListView listView;

    DetailFragment detailFragment;
    MasterFragment masterFragment;
    SharedPreferences sharedPrefs;

    public static final String START_SYNC = "start sync";
    public static final String COMMENTS = "comments";
    public static final String INTERNET_CONNECTION = "internet connection";
    public static final String MESSAGE = "comment";
    public static final String NOTIFY = "notify";
    public static final String FILE_NAME = "new_my_practice_file.txt";
    public static final String TAG = MainActivity.class.getName();

    boolean isGetNotifications = false;

    // onCreate method is a lifecycle method called when he activity is starting
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Each lifecycle method should call the method it overrides
        super.onCreate(savedInstanceState);
        // setContentView method draws UI
        setContentView(R.layout.activity_main);

        titles = getResources().getStringArray(R.array.titles);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        listView = (ListView) findViewById(R.id.list_left_drawer);


        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, titles));
        listView.setOnItemClickListener(new DrawerItemClickListener());
        //      listView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, titles));

        if (findViewById(R.id.detail_view) != null) {
            landscape = true;
        }

        masterFragment = new MasterFragment();
        FragmentTransaction tr = getFragmentManager().beginTransaction();
        tr.replace(R.id.master_view, masterFragment);
        tr.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        tr.commit();


        detailFragment = new DetailFragment();


        detailFragment.setPosition(position);
        if (findViewById(R.id.detail_view) != null) {
            landscape = true;
            getFragmentManager().popBackStack();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.detail_view, detailFragment, "Detail_Fragment_1");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //register BroadcastReceiver
        SimpleBroadcast sync = new SimpleBroadcast();
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction(START_SYNC);
        intentfilter.addAction(COMMENTS);
        registerReceiver(sync, intentfilter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_add:
                //todo display file message

                if (isStoragePermissionGranted()) {
                    ReviewerTools.writeToFile(new Date().toString(), this, FILE_NAME);
                }


                /**
                 * this was part of previous assignment
                 */

                getPrefs();
                int internetConnection = ReviewerTools.getConnectivityStatus(this);
                Intent intent = new Intent(MainActivity.this, SimpleService.class);
                intent.putExtra(INTERNET_CONNECTION, internetConnection);
                intent.putExtra(NOTIFY, isGetNotifications);
                startService(intent);

                Toast.makeText(this, getResources().getString(R.string.start_service), Toast.LENGTH_SHORT).show();
                break;


            case R.id.action_delete:
                //todo delete item
                Toast.makeText(this, "Delete item", Toast.LENGTH_SHORT).show();

                break;

            case R.id.action_edit:
                //todo enter comment
                getPrefs();
                setDialogue();
                Toast.makeText(this, "Edit item", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_search:
               File downloadFolder =   Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

                if (ReviewerTools.isFileExists(MainActivity.this, FILE_NAME)) {
                    Toast.makeText(this, "File Exists", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "File does NOT Exist!", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void clicked(int position) {

        this.position = position;

        if (landscape) {
            // If the device is in the landscape mode updates detail fragment's content.
            detailFragment.upDate(position);
        } else {
            // If the device is in the portrait mode sets detail fragment's content and replaces master fragment with detail fragment in a fragment transaction.
            detailFragment.setPosition(position);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.master_view, detailFragment, "Detail_Fragment_1");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }

    }

    public void setDialogue() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        alert.setTitle("Add Comment");

        final EditText name = new EditText(this);
        name.setHint("Name");
        layout.addView(name); // Notice this is an add method

        final EditText comment = new EditText(this);
        comment.setHint("Comment");
        layout.addView(comment); // Another add method

        alert.setView(layout);

        alert.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String ourName = name.getText().toString();
                String ourComment = comment.getText().toString();

                int connectionStatus = ReviewerTools.getConnectivityStatus(MainActivity.this);

                if (connectionStatus == ReviewerTools.TYPE_MOBILE || connectionStatus == ReviewerTools.TYPE_WIFI) {

                    Intent intent = new Intent(MainActivity.this, SimpleService.class);
                    intent.putExtra(MESSAGE, ourComment);
                    intent.putExtra(NOTIFY, isGetNotifications);
                    startService(intent);
                } else {
                    Toast.makeText(MainActivity.this, "No internet connection!", Toast.LENGTH_LONG).show();
                }
                //todo send comment to ..

            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // no action
            }
        });

        alert.show();

    }

    //get Preferences from settings menu
    public void getPrefs() {
        isGetNotifications = sharedPrefs.getBoolean(NOTIFY, false);
    }

    //storage permission
    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }


    class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            selectItem(i);
        }

        private void selectItem(int position) {
            switch (position) {
                case 0:
                    if (findViewById(R.id.detail_view) != null) {
                        landscape = true;
                    }

                    masterFragment = new MasterFragment();
                    FragmentTransaction tr = getFragmentManager().beginTransaction();
                    tr.replace(R.id.master_view, masterFragment);
                    tr.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    tr.commit();

                    detailFragment = new DetailFragment();

                    detailFragment.setPosition(position);
                    if (findViewById(R.id.detail_view) != null) {
                        landscape = true;
                        getFragmentManager().popBackStack();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.detail_view, detailFragment, "Detail_Fragment_1");
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft.commit();
                    }
                    break;
                case 2:
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.master_view, new SettingFragment());
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.commit();
                    break;
                case 3:
                    showDialog();

                    break;
            }
            listView.setItemChecked(position, true);
            setTitle(titles[position]);
            drawerLayout.closeDrawer(listView);

        }

        void showDialog() {
            DialogFragment newFragment = DialogAboutFragment.newInstance(
                    R.string.btn_ok);
            newFragment.show(getFragmentManager(), "dialog");
        }
    }
}
