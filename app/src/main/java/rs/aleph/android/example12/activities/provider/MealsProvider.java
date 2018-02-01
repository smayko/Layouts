package rs.aleph.android.example12.activities.provider;

import java.util.ArrayList;
import java.util.List;

import rs.aleph.android.example12.activities.model.Category;
import rs.aleph.android.example12.activities.model.Meal;

/**
 * Created by androiddevelopment on 30.1.18..
 */

public class MealsProvider {



    public static List<Meal> getMeals(){
        Category category = new Category("Domestic food", null);
        Category category2 = new Category("Hungarian food", null);

        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(0, "Sarma" , category, "nice food", "cabbage, meat", 600, 399.99));
        meals.add(new Meal(1, "Cevap" , category, "great food", "meat, meat, meat", 400, 199.99));
        meals.add(new Meal(2, "Gulas" , category2, "best food", "potato, meat", 258, 299.99));


      return null;
    }

    public static List<String> getMealNames() {

        List<String> names = new ArrayList<>();
        names.add("Sarma");
        names.add("Cevap");
        names.add("Gulas");
        return names;
    }
}
