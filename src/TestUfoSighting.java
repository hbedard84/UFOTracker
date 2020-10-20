import Models.UfoSighting;

public class TestUfoSighting {
    public static void main (String[] args) {
        UfoSighting test2 = new UfoSighting(7200, "lackland afb", "tx","us","light",29.38421,-98.581082);

        System.out.printf("UFO ID# %d was viewed as a %s shaped object for %d seconds in %s, %s, %s at latitude, longitude (%f, %f).", test2.getSightingID(), test2.getUfoShape(), test2.getDurationSec(), test2.getCity(), test2.getState(), test2.getCountry(), test2.getLatitude(), test2.getLongitude());


    }
}
