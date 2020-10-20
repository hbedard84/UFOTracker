package Models;

import Utilities.DatabaseUtility;

import java.sql.SQLException;


public class UfoSighting {
    private int sightingID; //Primary key
    private int durationSec;
    private String city, state, country, ufoShape;

    public UfoSighting(int durationSec, String city, String state, String country, String ufoShape) {
        setDurationSec(durationSec);
        setCity(city);
        setState(state);
        setCountry(country);
        setUfoShape(ufoShape);
        try {
            sightingID = DatabaseUtility.insertNewSighting(this);
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
        if (durationSec==0)
            this.durationSec = 0;
        if (durationSec>0)
            this.durationSec = durationSec;
        else throw new IllegalArgumentException("Duration must be greater than 0 seconds");
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city == null)
            this.city = "undefined";
        else if (!city.isBlank())
            this.city = city;
        else throw new IllegalArgumentException("City cannot be blank.");
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (state == null)
            this.state = "undefined";
        else if (!state.isBlank())
        this.state = state;
        else throw new IllegalArgumentException("State cannot be blank.");
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country == null)
            this.country = "undefined";
        else if (!country.isBlank())
            this.country = country;
        else throw new IllegalArgumentException("Country cannot be blank.");
    }

    public String getUfoShape() {
        return ufoShape;
    }

    public void setUfoShape(String ufoShape) {
        if (ufoShape == null)
            this.ufoShape = "undefined";
        else if (!ufoShape.isBlank())
            this.ufoShape = ufoShape;
        else throw new IllegalArgumentException("Shape cannot be blank");
    }

    public String toString(){
        return String.format("UFO# %d: %s ufo sighted in %s,%s, %s.",getSightingID(), getUfoShape(),getCity(),getState(),getCountry());
    }
}
