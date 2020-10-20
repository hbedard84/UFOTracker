import Models.UfoSighting;
import Utilities.DatabaseUtility;

import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseTester {
    public static void main (String[] args) throws SQLException {
        ArrayList<UfoSighting> ufoList = DatabaseUtility.getAllSightings();
        for (UfoSighting ufo : ufoList) {
            System.out.println(ufo);
        }
    }
}
