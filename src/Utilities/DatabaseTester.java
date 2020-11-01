package Utilities;

import Models.UfoSighting;
import Utilities.DatabaseUtility;

import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseTester {
    /***
     * This method gets all the records from the ufoSightings database and displays them in the console to test the database connection and output
     * @param args
     * @throws SQLException
     */
    public static void main (String[] args) throws SQLException {
        ArrayList<UfoSighting> ufoList = DatabaseUtility.getAllSightings();
        for (UfoSighting ufo : ufoList) {
            System.out.println(ufo);
        }
    }
}
