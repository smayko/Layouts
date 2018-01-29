package rs.aleph.android.example12.activities;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import rs.aleph.android.example12.R;
import rs.aleph.android.example12.activities.model.Category;
import rs.aleph.android.example12.activities.model.Meal;

// Each activity extends Activity class
public class SecondActivity extends Activity {

    TextView tvTitle;
    TextView tvDescription;
    TextView tvCategory;
    TextView tvSpices;
    TextView tvPrice;
    TextView tvCalories;
    ImageView ivMealImage;

    // onCreate method is a lifecycle method called when he activity is starting
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Each lifecycle method should call the method it overrides
        super.onCreate(savedInstanceState);
        // setContentView method draws UI
        setContentView(R.layout.activity_second);
        findViews();

        String desc = getResources().getString(R.string.description);
        String ingred = "Sour Cabbage ,Minced meat ,Salt ,Pepper";
        String imageUri = "drawable://" + R.drawable.sarma;


        Category cat = new Category("Domestic cuisine", null);

        Meal meal = new Meal("Sarma", cat, desc, ingred, 450, 350.99, imageUri );

        int imageId = getResources().getIdentifier("sarma", "drawable", getPackageName());


        tvTitle.setText(meal.getTitle());
        tvDescription.setText(meal.getDescription());
        tvCategory.setText(meal.getCategory().getName());
        tvSpices.setText(meal.getIngredients());
        tvPrice.setText(String.valueOf(meal.getPrice()) + " din");
        tvCalories.setText(String.valueOf(meal.getCalories()) + " kcal");
        ivMealImage.setImageResource(imageId);


        // Shows a toast message (a pop-up message)
        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onCreate()", Toast.LENGTH_SHORT);
        toast.show();
    }



    // onStart method is a lifecycle method called after onCreate (or after onRestart when the
    // activity had been stopped, but is now again being displayed to the user)
    @Override
    protected void onStart() {
        super.onStart();

        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onStart()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onRestart method is a lifecycle method called after onStop when the current activity is
    // being re-displayed to the user
    @Override
    protected void onRestart() {
        super.onRestart();

        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onRestart()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onResume method is a lifecycle method called after onRestoreInstanceState, onRestart, or
    // onPause, for your activity to start interacting with the user
    @Override
    protected void onResume() {
        super.onResume();

        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onResume()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onPause method is a lifecycle method called when an activity is going into the background,
    // but has not (yet) been killed
    @Override
    protected void onPause() {
        super.onPause();

        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onPause()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onStop method is a lifecycle method called when the activity are no longer visible to the user
    @Override
    protected void onStop() {
        super.onStop();

        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onStop()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onDestroy method is a lifecycle method that perform any final cleanup before an activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onDestroy()", Toast.LENGTH_SHORT);
        toast.show();
    }
    private void findViews() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvCategory = (TextView) findViewById(R.id.tvCategory);
        tvSpices = (TextView) findViewById(R.id.tvTitle);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvCalories = (TextView) findViewById(R.id.tvCalories);
        ivMealImage = (ImageView) findViewById(R.id.ivMeal);

    }
}
