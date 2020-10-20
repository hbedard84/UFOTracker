import Models.UfoSighting;
import Utilities.DatabaseUtility;

import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseTester {
    public static void main (String[] args) throws SQLException {
        ArrayList<UfoSighting> ufoList = DatabaseUtility.getAllSightings();
        for (int i = 0; i < ufoList.size();i++) {
            System.out.printf("UFO# %d: %s ufo sighted in %s,%s, %s.",ufoList.get(i).getSightingID(), ufoList.get(i).getUfoShape(),ufoList.get(i).getCity(),ufoList.get(i).getState(),ufoList.get(i).getCountry());
        }
    }
}
