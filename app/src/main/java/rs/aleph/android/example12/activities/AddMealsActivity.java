package rs.aleph.android.example12.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.aleph.android.example12.R;
import rs.aleph.android.example12.activities.db.DatabaseHelper;

import rs.aleph.android.example12.activities.model.Category;
import rs.aleph.android.example12.activities.model.Meal;

/**
 * Created by hp-zbook-g3 on 02-Mar-18.
 */

public class AddMealsActivity extends AppCompatActivity {

    Spinner spnChooseMeal;
    Button btnAddMeal;
    ImageView ivImageMealPreview;
    int mealPosition;
    DatabaseHelper mDatabaseHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meals_layout);
        spnChooseMeal = (Spinner) findViewById(R.id.spChooseMeal);
        btnAddMeal = (Button) findViewById(R.id.btnAddMeal);
        ivImageMealPreview = (ImageView) findViewById(R.id.ivMealPreview);
        mDatabaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        addCategoryToDb();

    }

    @Override
    protected void onResume() {
        super.onResume();
        populateSpinner();
    }

    public void populateSpinner() {
        List<String> list = new ArrayList<String>();
        list.add("Sarma");
        list.add("Pasulj");
        list.add("Gulas");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spnChooseMeal.setAdapter(dataAdapter);
        chooseMeal();
    }


    public void chooseMeal() {

        spnChooseMeal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setImagePreview(i);
                addMeal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void setImagePreview(int position) {
        mealPosition = position;
        switch (position) {
            case 0:
                ivImageMealPreview.setImageResource(R.drawable.sarma);
                break;
            case 1:
                ivImageMealPreview.setImageResource(R.drawable.pasulj);
                break;
            case 2:
                ivImageMealPreview.setImageResource(R.drawable.gulas);
                break;
        }

    }

    public void addMeal() {

        btnAddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add meal to db
                Meal meal = new Meal();
                Category category = new Category(1 ,"Neka kategorija");
                switch (mealPosition) {
                    case 0:
                        meal.setImage("sarma.jpg");
                        meal.setCalories(300);
                        meal.setDescription("lepa rana");
                        meal.setCategory(category);
                        meal.setIngredients("so, biber, kupus");
                        meal.setPrice(1200);
                        meal.setTitle("Sarmita");
                        break;
                    case 1:
                        meal.setImage("pasulj.jpg");
                        meal.setCalories(340);
                        meal.setCategory(category);
                        meal.setDescription("lepa rana pasulj");
                        meal.setCategory(category);
                        meal.setIngredients("so, biber, suva svinja");
                        meal.setPrice(120);
                        meal.setTitle("Pasulj");
                        break;
                    case 2:
                        meal.setImage("gulas.jpg");
                        meal.setCalories(305);
                        meal.setDescription("lepa rana");
                        meal.setIngredients("so, biber, gul");
                        meal.setPrice(140);
                        meal.setTitle("GULASHH");
                        break;
                }

                try {
                    mDatabaseHelper.getMealDao().create(meal);
                    Toast.makeText(AddMealsActivity.this, "Meal inserted", Toast.LENGTH_SHORT).show();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void addCategoryToDb() {
        Category category = new Category(2,"Home Made Food");

        try {
            mDatabaseHelper.getCategoryDao().create(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //release resources
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mDatabaseHelper != null) {
            OpenHelperManager.releaseHelper();
            mDatabaseHelper = null;
        }
    }
}


