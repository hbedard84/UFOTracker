package Models;

import Utilities.DatabaseUtility;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class UfoSighting {
    private int sightingID; //Primary key
    private int durationSec;
    private LocalDate sightingDate;
    private String city, state, country, ufoShape, reportDetails;
    private double latitude, longitude;

    public UfoSighting(int durationSec, LocalDate sightingDate, String city, String state, String country, String ufoShape, String reportDetails, double latitude, double longitude) {
        setDurationSec(durationSec);
        setSightingDate(sightingDate);
        setCity(city);
        setState(state);
        setCountry(country);
        setUfoShape(ufoShape);
        setReportDetails(reportDetails);
        setLatitude(latitude);
        setLongitude(longitude);
        try {
            int sightingID = DatabaseUtility.insertNewSighting(this);
            setSightingID(sightingID);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getSightingID() {
        return sightingID;
    }

    private void setSightingID(int sightingID) {
        if (sightingID>0)
            this.sightingID = sightingID;
        else throw new IllegalArgumentException("ID must be greater than 0");
    }

    public int getDurationSec() {
        return durationSec;
    }

    public void setDurationSec(int durationSec) {
        if (durationSec>0)
            this.durationSec = durationSec;
        else throw new IllegalArgumentException("Duration must be greater than 0 seconds");
    }

    public LocalDate getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(LocalDate sightingDate) {
        if (sightingDate.isBefore(LocalDate.now()) || sightingDate.isEqual(LocalDate.now()))
            this.sightingDate = sightingDate;
        else throw new IllegalArgumentException("DateTime must be before the current time and date.");
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (!city.isBlank())
            this.city = city;
        else throw new IllegalArgumentException("City cannot be blank.");
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (!state.isBlank())
        this.state = state;
        else throw new IllegalArgumentException("State cannot be blank.");
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (!country.isBlank())
            this.country = country;
        else throw new IllegalArgumentException("Country cannot be blank.");
    }

    public String getUfoShape() {
        return ufoShape;
    }

    public void setUfoShape(String ufoShape) {
        if (!ufoShape.isBlank())
            this.ufoShape = ufoShape;
        else throw new IllegalArgumentException("Shape cannot be blank");
    }

    public String getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(String reportDetails) {
        if (!reportDetails.isBlank())
            this.reportDetails = reportDetails;
        else throw new IllegalArgumentException("Report details cannot be blank");
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        if (latitude<=90 && latitude>=-90)
            this.latitude = latitude;
        else throw new IllegalArgumentException("Latitude must be between 90 and -90.");
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        if (longitude<=80 && longitude>=-180)
            this.longitude = longitude;
        else throw new IllegalArgumentException("Longitude must be between 80 and -180.");
    }
}
