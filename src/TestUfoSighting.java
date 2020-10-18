import Models.UfoSighting;

import java.time.LocalDate;

public class TestUfoSighting {
    public static void main (String[] args) {
        UfoSighting test2 = new UfoSighting(7200, LocalDate.of(1949,10,10),"lackland afb", "tx","us","light","1949 Lackland AFB&#44 TX.  Lights racing across the sky &amp; making 90 degree turns on a dime.",29.38421,-98.581082);

        System.out.printf("UFO ID %d was viewed for %d seconds in %s, %s", test2.getSightingID(), test2.getDurationSec(), test2.getCity(), test2.getState());
    }
}
