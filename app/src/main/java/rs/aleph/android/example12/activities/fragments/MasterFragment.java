package rs.aleph.android.example12.activities.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
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

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.aleph.android.example12.R;
import rs.aleph.android.example12.activities.contentProvider.MealProvider;
import rs.aleph.android.example12.activities.db.DatabaseHelper;
import rs.aleph.android.example12.activities.model.Meal;

/**
 * Created by hp-zbook-g3 on 05-Feb-18.
 */

public class MasterFragment extends Fragment {


    ListView listViewMeals;
    DatabaseHelper mOpenHelperManager;
    ListAdapter adapter;

    public interface OurClickListener {
        void clicked(int position);
    }

    OurClickListener clickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_master, container, false);

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        clickListener = (OurClickListener) activity;


//        listViewMeals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                clickListener.clicked(i);
//            }
//        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int[] i1 = {R.drawable.sarma, R.drawable.pasulj, R.drawable.gulas};

        List<String> mealsProvider = MealProvider.mealNames();




        listViewMeals = (ListView) getActivity().findViewById(R.id.listViewMeals);


        List<Meal> meals = null;
        try {
            meals = getDatabaseHelper().getMealDao().queryForAll();
            adapter = new ArrayAdapter<Meal>(getActivity(), R.layout.list_item, meals);
            if (meals != null) {
                refresh(listViewMeals, meals);

            } else {
                ListAdapter arrayAdapter = new MasterFragment.MyArrayAdapeter(mealsProvider, i1);
                listViewMeals.setAdapter(arrayAdapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void refresh(ListView listview, List<Meal> mealList) {
  /*      if (listview != null) {
            ArrayAdapter<Meal> adapter = (ArrayAdapter<Meal>) listview.getAdapter();
            adapter = new ArrayAdapter<Meal>(getActivity(), R.layout.list_item, mealList);
            listview.setAdapter(adapter);
            if (adapter != null) {

                //    adapter.clear();

                adapter.addAll(mealList);

                adapter.notifyDataSetChanged();

            }
        }*/
    }

    public class MyArrayAdapeter extends BaseAdapter {
        List<String> titles;
        int[] ivFoodImage;

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

        public MyArrayAdapeter(List<String> title, int[] iv) {
            this.titles = title;
            this.ivFoodImage = iv;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = getActivity().getLayoutInflater();
            View row;
            row = inflater.inflate(R.layout.list_item_with_image, viewGroup, false);
            TextView title;
            ImageView i1;
            title = (TextView) row.findViewById(R.id.tvTitle);
            i1 = (ImageView) row.findViewById(R.id.ivFoodImage);
            title.setText(titles.get(i));

            i1.setImageResource(ivFoodImage[i]);

            return (row);

        }
    }

    public DatabaseHelper getDatabaseHelper() {
        if (mOpenHelperManager == null) {
            mOpenHelperManager = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return mOpenHelperManager;
    }


}
