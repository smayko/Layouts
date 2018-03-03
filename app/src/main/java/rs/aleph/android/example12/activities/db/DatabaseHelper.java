package rs.aleph.android.example12.activities.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import rs.aleph.android.example12.activities.model.Category;
import rs.aleph.android.example12.activities.model.Meal;

/**
 * Created by hp-zbook-g3 on 02-Mar-18.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "my_demo_app.db";

    private static final int DATABASE_VERSION = 2;

    private Dao<Meal, Integer> mealDao = null;
    private Dao<Category, Integer> categoryDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, Meal.class);
            TableUtils.createTable(connectionSource, Category.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(connectionSource, Meal.class, true);
            TableUtils.dropTable(connectionSource, Category.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //create Dao Object
    public Dao<Meal, Integer> getMealDao() throws SQLException {
        if (mealDao == null) {
            mealDao = getDao(Meal.class);
        }
        return mealDao;
    }

    public Dao<Category, Integer> getCategoryDao() throws SQLException {
        if (categoryDao == null) {
            categoryDao = getDao(Category.class);
        }
        return categoryDao;
    }

    //close resources when done with db!
    @Override
    public void close() {

        mealDao = null;
        super.close();
    }
}
