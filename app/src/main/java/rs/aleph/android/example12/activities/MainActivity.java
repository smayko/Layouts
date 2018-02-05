package rs.aleph.android.example12.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import rs.aleph.android.example12.R;
import rs.aleph.android.example12.activities.contentProvider.MealProvider;
import rs.aleph.android.example12.activities.fragments.DetailFragment;
import rs.aleph.android.example12.activities.fragments.MasterFragment;

// Each activity extends Activity class
public class MainActivity extends Activity implements MasterFragment.OurClickListener{



	boolean landscape;

	int position;

	DetailFragment detailFragment;
	MasterFragment masterFragment;

	// onCreate method is a lifecycle method called when he activity is starting
	@Override
	protected void onCreate(Bundle savedInstanceState) 	{

		// Each lifecycle method should call the method it overrides
		super.onCreate(savedInstanceState);
		// setContentView method draws UI
		setContentView(R.layout.activity_main);

		if(findViewById(R.id.detail_view ) != null){
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


}
