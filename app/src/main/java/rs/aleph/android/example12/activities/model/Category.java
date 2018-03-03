package rs.aleph.android.example12.activities.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by hp-zbook-g3 on 29-Jan-18.
 */


@DatabaseTable(tableName = Category.TABLE_NAME_CATEGORIES)
public class Category {

    public static final String TABLE_NAME_CATEGORIES = "category";

    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_CATEGORY_NAME = "category_name";
    public static final String COLUMN_NAME_MEALS = "meals";


    @DatabaseField(columnName = COLUMN_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = COLUMN_NAME_CATEGORY_NAME)
    private String name;

    @ForeignCollectionField(columnName = COLUMN_NAME_MEALS)
    private Collection<Meal> meals;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public Collection<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Collection<Meal> meals) {
        this.meals = meals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
