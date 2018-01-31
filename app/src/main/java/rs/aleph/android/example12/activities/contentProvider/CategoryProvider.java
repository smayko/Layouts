package rs.aleph.android.example12.activities.contentProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp-zbook-g3 on 31-Jan-18.
 */

public class CategoryProvider {

    public static List<String> getCategoryNames(){
        List<String> names = new ArrayList<>();
        names.add("Serbian cuisine");
        names.add("Hungarian cuisine");
        return names;
    }
}
