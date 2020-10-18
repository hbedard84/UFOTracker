package Utilities;

import java.sql.*;
import Models.UfoSighting;


public class DatabaseUtility {
    private static String user = "root";
    private static String password = "password1234";

    /***
     * This method receives a new UFOSighting object, adds it to the database and returns the id
     * @param newUfoSighting
     * @return the sightingID primary key from database
     */
    public static int insertNewSighting (UfoSighting newUfoSighting) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int sightingID = -1;

        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/COMP1011Java", user, password);

            String sql = "INSERT INTO ufoSightings (sightingDate, city, state, country, " +
                    "latitude, longitude, durationSec, ufoShape, reportDetails) VALUES (?,?,?,?,?,?,?,?,?)";

            ps = conn.prepareStatement(sql, new String[] {"sightingID"});

            ps.setDate(1, Date.valueOf(newUfoSighting.getSightingDate()));
            ps.setString(2,newUfoSighting.getCity());
            ps.setString(3,newUfoSighting.getState());
            ps.setString(4,newUfoSighting.getCountry());
            ps.setDouble(5,newUfoSighting.getLatitude());
            ps.setDouble(6,newUfoSighting.getLongitude());
            ps.setInt(7,newUfoSighting.getDurationSec());
            ps.setString(8,newUfoSighting.getUfoShape());
            ps.setString(9,newUfoSighting.getReportDetails());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            while (rs.next()){
                sightingID = rs.getInt(1);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            if (conn != null)
                conn.close();
            if (ps != null)
                ps.close();
            return sightingID;
        }


    }
}
