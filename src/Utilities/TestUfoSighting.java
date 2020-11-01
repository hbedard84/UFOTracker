package Utilities;

import Models.UfoSighting;

/***
 * This class was used to test the ufosighting constructor
 */
public class TestUfoSighting {
    public static void main (String[] args) {
        UfoSighting test2 = new UfoSighting(7200, "lackland afb", "tx","us","light");

        System.out.printf("UFO ID# %d was viewed as a %s shaped object for %d seconds in %s, %s, %s at latitude, longitude (%f, %f).", test2.getSightingID(), test2.getUfoShape(), test2.getDurationSec(), test2.getCity(), test2.getState(), test2.getCountry());


    }
}
