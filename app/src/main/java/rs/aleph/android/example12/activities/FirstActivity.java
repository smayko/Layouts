package rs.aleph.android.example12.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import rs.aleph.android.example12.R;
import rs.aleph.android.example12.activities.contentProvider.MealProvider;

// Each activity extends Activity class
public class FirstActivity extends Activity {

	ListView listViewMeals;

	// onCreate method is a lifecycle method called when he activity is starting
	@Override
	protected void onCreate(Bundle savedInstanceState) 	{

		// Each lifecycle method should call the method it overrides
		super.onCreate(savedInstanceState);
		// setContentView method draws UI
		setContentView(R.layout.activity_main);

		int[] i1 ={R.drawable.sarma,R.drawable.pasulj, R.drawable.gulas};



		List<String> meals = MealProvider.mealNames();

		ListAdapter arrayAdapter = new MyArrayAdapeter(meals, i1);
		listViewMeals = (ListView) findViewById(R.id.listViewMeals);
		listViewMeals.setAdapter(arrayAdapter);


		listViewMeals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
				intent.putExtra("position", i);
				startActivity(intent);

			}
		});
	}



	// Called when btnStart button is clicked
	public void btnStartActivityClicked(View view) {
		// This is an explicit intent (class property is specified)
        Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
		// startActivity method starts an activity
        startActivity(intent);
	}

	// Called when btnOpen is clicked
    public void btnOpenBrowserClicked(View view) {
		// This is an implicit intent
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com"));
		// startActivity method starts an activity
		startActivity(i);
    }

    public class MyArrayAdapeter extends BaseAdapter {
		List<String> titles;
		int [] ivFoodImage;

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public Object getItem(int i) {
			return null;
		}

		@Override
		public long getItemId(int i) {
			return 0;
		}

		public MyArrayAdapeter(List<String> title, int[] iv){
			this.titles = title;
			this.ivFoodImage = iv;
		}



		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {

			LayoutInflater inflater = getLayoutInflater();
			View row;
			row = inflater.inflate(R.layout.list_item_with_image, viewGroup, false);
			TextView title;
			ImageView i1;
			title = (TextView) row.findViewById(R.id.tvTitle);
			i1=(ImageView)row.findViewById(R.id.ivFoodImage);
			title.setText(titles.get(i));

			i1.setImageResource(ivFoodImage[i]);

			return (row);

		}
	}
}
