package rs.aleph.android.example12.activities.provider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddevelopment on 30.1.18..
 */

public class CategoryProvider {

    public static List<String> getCategoryNames() {
        List<String> names = new ArrayList<>();
        names.add("Domestic food");
        names.add("Hungarian food");
        return names;
    }
}
