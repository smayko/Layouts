package rs.aleph.android.example12.activities.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import rs.aleph.android.example12.R;
import rs.aleph.android.example12.activities.contentProvider.CategoryProvider;
import rs.aleph.android.example12.activities.contentProvider.MealProvider;
import rs.aleph.android.example12.activities.model.Meal;

/**
 * Created by hp-zbook-g3 on 05-Feb-18.
 */

public class DetailFragment extends Fragment {

    TextView tvTitle;
    TextView tvDescription;
    TextView tvCategory;
    TextView tvSpices;
    TextView tvPrice;
    TextView tvCalories;
    ImageView ivMealImage;
    Spinner spnMeals;

    int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        findViews();
        //     position = getIntent().getExtras().getInt("position");
        List<String> categories = CategoryProvider.getCategoryNames();
        List<Meal> meals = MealProvider.getMeals();

        spnMeals = (Spinner) getView().findViewById(R.id.spnMeals);
        tvTitle = (TextView) getView().findViewById(R.id.tvTitle);
        tvDescription = (TextView) getView().findViewById(R.id.tvDescription);
        tvCategory = (TextView) getView().findViewById(R.id.tvCategory);
        tvSpices = (TextView) getView().findViewById(R.id.tvIngredients);
        tvPrice = (TextView) getView().findViewById(R.id.tvPrice);
        tvCalories = (TextView) getView().findViewById(R.id.tvCalories);
        ivMealImage = (ImageView) getView().findViewById(R.id.ivMeal);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, categories);
        spnMeals.setAdapter(adapter);
      //  spnMeals.setSelection(meals.get(position).getCategory().getId());

        tvTitle.setText(meals.get(position).getTitle());
        tvDescription.setText(meals.get(position).getDescription());
        tvSpices.setText(meals.get(position).getIngredients());
        tvPrice.setText(String.valueOf(meals.get(position).getPrice() + " din"));
        tvCalories.setText(String.valueOf(meals.get(position).getCalories() + " kcal"));

        switch (position) {
            case 0:
                ivMealImage.setImageResource(R.drawable.sarma);
                break;
            case 1:
                ivMealImage.setImageResource(R.drawable.pasulj);
                break;

            case 2:
                ivMealImage.setImageResource(R.drawable.gulas);
                break;
        }
    }

    public void setPosition(int pos){
        this.position = pos;
    }


    public void upDate(int pos){
        this.position = pos;

        findViews();
        //     position = getIntent().getExtras().getInt("position");
        List<String> categories = CategoryProvider.getCategoryNames();
        List<Meal> meals = MealProvider.getMeals();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, categories);
        spnMeals.setAdapter(adapter);
   //     spnMeals.setSelection(meals.get(position).getCategory().getId());

        tvTitle.setText(meals.get(position).getTitle());
        tvDescription.setText(meals.get(position).getDescription());
        tvSpices.setText(meals.get(position).getIngredients());
        tvPrice.setText(String.valueOf(meals.get(position).getPrice() + " din"));
        tvCalories.setText(String.valueOf(meals.get(position).getCalories() + " kcal"));

        switch (position) {
            case 0:
                ivMealImage.setImageResource(R.drawable.sarma);
                break;
            case 1:
                ivMealImage.setImageResource(R.drawable.pasulj);
                break;

            case 2:
                ivMealImage.setImageResource(R.drawable.gulas);
                break;
        }

    }

    private void findViews() {
        spnMeals = (Spinner) getView().findViewById(R.id.spnMeals);
        tvTitle = (TextView) getView().findViewById(R.id.tvTitle);
        tvDescription = (TextView) getView().findViewById(R.id.tvDescription);
        tvCategory = (TextView) getView().findViewById(R.id.tvCategory);
        tvSpices = (TextView) getView().findViewById(R.id.tvIngredients);
        tvPrice = (TextView) getView().findViewById(R.id.tvPrice);
        tvCalories = (TextView) getView().findViewById(R.id.tvCalories);
        ivMealImage = (ImageView) getView().findViewById(R.id.ivMeal);

    }
}
