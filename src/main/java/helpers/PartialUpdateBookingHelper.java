package helpers;

import pojo.PartialUpdateBookingPojo;

public class PartialUpdateBookingHelper {
    public static PartialUpdateBookingPojo partialUpdateBookingPojo = new PartialUpdateBookingPojo();

    public static void setPartialUpdateBookingPojo() {
        partialUpdateBookingPojo.setFirstname("James");
        partialUpdateBookingPojo.setLastname("Bond");
    }

}
