package helpers;

import pojo.Bookingdates;
import pojo.CreateBookingPojo;

public class CreateBookingHelper {
    public static CreateBookingPojo createBookingPojo = new CreateBookingPojo();
    private static Bookingdates bookingdates = new Bookingdates();

    public static void setCreateBookingPojo() {
        createBookingPojo.setFirstname("Jim");
        createBookingPojo.setLastname("Brown");
        createBookingPojo.setTotalprice(111);
        createBookingPojo.setDepositpaid(true);
        createBookingPojo.setAdditionalneeds("Breakfast");
        createBookingPojo.setBookingdates(bookingdates);
    }

    public static void setBookingdates() {
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");
    }


}
