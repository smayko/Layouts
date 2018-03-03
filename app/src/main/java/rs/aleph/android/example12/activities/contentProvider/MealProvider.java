package rs.aleph.android.example12.activities.contentProvider;

import java.util.ArrayList;
import java.util.List;

import rs.aleph.android.example12.activities.model.Category;
import rs.aleph.android.example12.activities.model.Meal;

/**
 * Created by hp-zbook-g3 on 31-Jan-18.
 */

public class MealProvider {

   public static List<String> mealNames(){
       List<String> names = new ArrayList<>();

       names.add("Sarma");
       names.add("Pasulj");
       names.add("Gulas");
       return names;
   }

   public static List<Meal> getMeals(){

       Category cat = new Category(0, "Serbian food");
       Category cat2 = new Category(1, "Hungarian food");

       List<Meal> meals = new ArrayList<>();
       Meal meal = new Meal(0, "Sarma", cat, "Beautiful sour cabbage food described", "cabbage, meat", 400, 350.99, "sarma.png");
       Meal meal1 = new Meal(1, "Pasulj", cat, "Beautiful beans described", "beans, meat", 600, 150.99, "pasulj.jpg");
       Meal meal2= new Meal(2, "Gulas", cat2, "Hungarian recipe made in Vojvodina", "onion, meat, tomato sauce", 600, 450.99, "gulas.jpg");

       meals.add(meal);
       meals.add(meal1);
       meals.add(meal2);


       return meals;

   }
}
