package rs.aleph.android.example12.activities.model;

import java.util.ArrayList;

/**
 * Created by hp-zbook-g3 on 29-Jan-18.
 */

public class Category {

    private int id;
    private String name;
    private ArrayList<Meal> meals;

    public Category() {
    }

    public Category(int id, String name, ArrayList<Meal> meals) {
        this.name = name;
        this.meals = meals;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
