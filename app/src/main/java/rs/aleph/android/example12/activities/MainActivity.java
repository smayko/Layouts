package rs.aleph.android.example12.activities;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
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

    public static final String START_SYNC = "start sync";
    public static final String COMMENTS = "comments";
    public static final String INTERNET_CONNECTION = "internet connection";
    public static final String MESSAGE = "comment";

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
                //todo start service

                int internetConnection = ReviewerTools.getConnectivityStatus(this);
                Intent intent = new Intent(MainActivity.this, SimpleService.class);
                intent.putExtra(INTERNET_CONNECTION, internetConnection);
                startService(intent);


                Toast.makeText(this, "Start our Service which will start Async and then Broadcast the message", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_delete:
              //todo delete item
                Toast.makeText(this, "Delete item" , Toast.LENGTH_SHORT).show();

                break;

            case R.id.action_edit:
                //todo enter comment


                setDialogue();

                Toast.makeText(this, "Edit item", Toast.LENGTH_SHORT).show();

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
}
