package Utilities;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

import Models.UfoSighting;


public class DatabaseUtility {
    private static String user = "root";
    private static String password = "password1234";
    private static String dbURL = "jdbc:mysql://localhost:3306/COMP1011Java";

    /*The data used in this application was obtained from a dataset modified from the
    National UFO Reporting Center, sourced from https://www.kaggle.com/NUFORC/ufo-sightings
     */

    /***
     * This method receives a new UFOSighting object, adds it to the database and returns the id
     * @param newUfoSighting
     * @return the sightingID primary key from database
     */
    public static int insertNewSighting(UfoSighting newUfoSighting) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int sightingID = -1;
        
        try {
            conn = DriverManager.getConnection(dbURL, user, password);

            String sql = "INSERT INTO ufoSightings (city, state, country, durationSec, ufoShape) VALUES (?,?,?,?,?)";

            ps = conn.prepareStatement(sql, new String[]{"sightingID"});

            ps.setString(1, newUfoSighting.getCity());
            ps.setString(2, newUfoSighting.getState());
            ps.setString(3, newUfoSighting.getCountry());
            ps.setInt(4, newUfoSighting.getDurationSec());
            ps.setString(5, newUfoSighting.getUfoShape());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            while (rs.next()) {
                sightingID = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.close();
            if (ps != null)
                ps.close();
            return sightingID;
        }
    }

    /***
     * This method retrieves all the records from the ufoSightings database and stores them in an arrayList
     * @return arrayList ufos of all ufo records
     * @throws SQLException
     */
    public static ArrayList<UfoSighting> getAllSightings() throws SQLException {
        ArrayList<UfoSighting> ufos = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //1. connect to the DB
            conn = DriverManager.getConnection(dbURL, user, password);

            //2. create a statement object
            statement = conn.createStatement();

            //3. create/execute the sql query
            resultSet = statement.executeQuery("SELECT * FROM ufoSightings");

            //4. loop over the results
            while (resultSet.next()) {
                UfoSighting newUfo = new UfoSighting(
                        resultSet.getInt("durationSec"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getString("country"),
                        resultSet.getString("ufoShape")
                );
                ufos.add(newUfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException illegalArg){
            System.out.println(illegalArg.getMessage());
        } catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
            return ufos;

        }
    }
}
