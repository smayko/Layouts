package rs.aleph.android.example12.activities.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * Created by hp-zbook-g3 on 29-Jan-18.
 */

@DatabaseTable(tableName = Meal.TABLE_NAME_MEALS)
public class Meal {

    public static final String TABLE_NAME_MEALS = "meals";

    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_CATEGORY = "category";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_INGREDIENTS = "ingredients";
    public static final String COLUMN_NAME_CALORIES = "calories";
    public static final String COLUMN_NAME_PRICE = "price";
    public static final String COLUMN_NAME_IMAGE = "image";

    @DatabaseField(columnName = COLUMN_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = COLUMN_NAME_TITLE)
    private String title;

    @DatabaseField(columnName = TABLE_NAME_MEALS, foreign = true, foreignAutoRefresh = true)
    private Category category;

    @DatabaseField(columnName = COLUMN_NAME_DESCRIPTION)
    private String description;

    @DatabaseField(columnName = COLUMN_NAME_INGREDIENTS)
    private String ingredients;

    @DatabaseField(columnName = COLUMN_NAME_CALORIES)
    private int calories;

    @DatabaseField(columnName = COLUMN_NAME_PRICE)
    private double price;

    @DatabaseField(columnName = COLUMN_NAME_IMAGE)
    private String image;

    public Meal(){

    }

    public Meal(int i, String sarma, Category category, String s, String s1, int i1, double v) {
    }

    public Meal(int id, String title, Category category, String description, String ingredients, int calories, double price, String image) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.ingredients = ingredients;
        this.calories = calories;
        this.price = price;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
