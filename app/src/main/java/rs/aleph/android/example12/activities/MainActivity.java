package rs.aleph.android.example12.activities;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import rs.aleph.android.example12.R;
import rs.aleph.android.example12.activities.fragments.DetailFragment;
import rs.aleph.android.example12.activities.fragments.DialogAboutFragment;
import rs.aleph.android.example12.activities.fragments.MasterFragment;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_add:
                //todo add item

                Toast.makeText(this, "Add item", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_delete:
                //todo delete item
                Toast.makeText(this, "Delete item", Toast.LENGTH_SHORT).show();

                break;

            case R.id.action_edit:
                //todo edit item
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
