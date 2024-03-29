package Utilities;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

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

            String sql = "INSERT INTO allUfoSightings (city, state, country, durationSec, ufoShape) VALUES (?,?,?,?,?)";

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
            resultSet = statement.executeQuery("SELECT * FROM allUfoSightings");


            //4. loop over the results
            while (resultSet.next()) {
                UfoSighting newUfo = new UfoSighting(
                        resultSet.getInt("sightingID"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getString("country"),
                        resultSet.getInt("durationSec"),
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

    /***
     * This method retrieves the total ufo sightings per country from the database and stores them in an treemap
     * @return TreeMap<country,total>
     * @throws SQLException
     */
     public static TreeMap<String, Integer> getChartData() throws SQLException {
        TreeMap<String, Integer> chartTree = new TreeMap<>();
        String countryName = "";
        int countryTotal = 0;
        Connection conn = null;
        Statement statementChart = null;
        ResultSet rsChart = null;

        try {
            //1. connect to the DB
            conn = DriverManager.getConnection(dbURL, user, password);

            //2. create a statement object
            statementChart = conn.createStatement();

            //3. create/execute the sql query
            rsChart = statementChart.executeQuery("SELECT country, COUNT(sightingID) as 'Total' FROM allUfoSightings GROUP BY country");

            //4. loop thru result set to create treemap of country/country total pairs

            while (rsChart.next()) {

                countryName= rsChart.getString("country");
                countryTotal= rsChart.getInt("Total");

                chartTree.put(countryName,countryTotal);  //this will be auto sorted alphabetically by country
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
            if (statementChart != null)
                statementChart.close();
            if (rsChart != null)
                rsChart.close();
            return chartTree;
        }
    }

    /***
     * This method retrieves the total ufo sightings per province in Canada from the database and stores them in an treemap
     * @return TreeMap<province,total>
     * @throws SQLException
     */
    public static TreeMap<String, Integer> getCanadaData() throws SQLException {
        TreeMap<String, Integer> chartTree = new TreeMap<>();
        String provinceName = "";
        int provinceTotal = 0;
        Connection conn = null;
        Statement statementChart = null;
        ResultSet rsChart = null;

        try {
            //1. connect to the DB
            conn = DriverManager.getConnection(dbURL, user, password);

            //2. create a statement object
            statementChart = conn.createStatement();

            //3. create/execute the sql query
            rsChart = statementChart.executeQuery("SELECT state, COUNT(sightingID) as 'Total' FROM allUfoSightings WHERE country = 'Canada' GROUP BY state");

            //4. loop thru result set to create treemap of province/province total pairs

            while (rsChart.next()) {

                provinceName= rsChart.getString("state");
                provinceTotal= rsChart.getInt("Total");

                chartTree.put(provinceName,provinceTotal);  //this is auto sorted by province name
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
            if (statementChart != null)
                statementChart.close();
            if (rsChart != null)
                rsChart.close();
            return chartTree;
        }
    }

    /***
     * This method retrieves all the shapes reported worldwide and a total of each shape from the database and stores them in an hashmap
     * @return HashMap<shape,total>
     * @throws SQLException
     */
    public static HashMap<String, Integer> getShapeData() throws SQLException {
        HashMap<String, Integer> shapeHash = new HashMap<>();
        String ufoShape = "";
        int shapeTotal = 0;
        Connection conn = null;
        Statement statementShape = null;
        ResultSet rsShape = null;

        try {
            //1. connect to the DB
            conn = DriverManager.getConnection(dbURL, user, password);

            //2. create a statement object
            statementShape = conn.createStatement();

            //3. create/execute the sql query
            rsShape = statementShape.executeQuery("SELECT ufoShape, COUNT(sightingID) as 'Total' FROM allUfoSightings GROUP BY ufoShape");

            //4. loop thru result set to create hashmap of country/country total pairs

            while (rsShape.next()) {

                ufoShape = rsShape.getString("ufoShape");
                shapeTotal= rsShape.getInt("Total");

                shapeHash.put(ufoShape,shapeTotal);
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
            if (statementShape != null)
                statementShape.close();
            if (rsShape != null)
                rsShape.close();
            return shapeHash;
        }
    }
}
