package rs.aleph.android.example12.activities.model;

import java.util.ArrayList;

/**
 * Created by hp-zbook-g3 on 29-Jan-18.
 */

public class Category {

    private String name;
    private ArrayList<Meal> meals;

    public Category() {
    }

    public Category(String name, ArrayList<Meal> meals) {
        this.name = name;
        this.meals = meals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }
}
