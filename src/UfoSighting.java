import java.time.LocalDate;

public class UfoSighting {
    private int sightingID, duration;
    private LocalDate dateTime;
    private String city, state, country, ufoShape, reportDetails;
    private Float latitude, longitude;

    public UfoSighting(int duration, LocalDate dateTime, String city, String state, String country, String ufoShape, String reportDetails, Float latitude, Float longitude) {
        setDuration(duration);
        setDateTime(dateTime);
        setCity(city);
        setState(state);
        setCountry(country);
        setUfoShape(ufoShape);
        setReportDetails(reportDetails);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public int getSightingID() {
        return sightingID;
    }

    public void setSightingID(int sightingID) {
        this.sightingID = sightingID;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUfoShape() {
        return ufoShape;
    }

    public void setUfoShape(String ufoShape) {
        this.ufoShape = ufoShape;
    }

    public String getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}
