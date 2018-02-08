package rs.aleph.android.example12.activities;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import rs.aleph.android.example12.R;
import rs.aleph.android.example12.activities.fragments.SettingFragment;

/**
 * Created by nikola.kosmajac on 2/8/2018.
 */

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.master_view, new SettingFragment())
                .commit();
    }
}
