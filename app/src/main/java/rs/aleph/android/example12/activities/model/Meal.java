package rs.aleph.android.example12.activities.model;

/**
 * Created by hp-zbook-g3 on 29-Jan-18.
 */

public class Meal {

    private String title;
    private Category category;
    private String description;
    private String ingredients;
    private int calories;
    private double price;
    private String image;

    public Meal() {
    }

    public Meal(String title, Category category, String description, String ingredients, int calories, double price, String image) {
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
