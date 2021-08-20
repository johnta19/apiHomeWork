public class DataBody {
    public static String requestBody = "{\n" +
            "    \"username\" : \"admin\",\n" +
            "    \"password\" : \"password123\"\n" +
            "}";

    public static String firstname = "Jim";
    public static String lastname = "Brown";
    public static int totalprice = 111;
    public static boolean depositpaid = true;

    public static String createBookingBody = "{\n" +
            "    \"firstname\" : \"" + firstname + "\",\n" +
            "    \"lastname\" : \"" + lastname + "\",\n" +
            "    \"totalprice\" : " + totalprice + ",\n" +
            "    \"depositpaid\" : " + depositpaid + ",\n" +
            "    \"bookingdates\" : {\n" +
            "        \"checkin\" : \"2018-01-01\",\n" +
            "        \"checkout\" : \"2019-01-01\"\n" +
            "    },\n" +
            "    \"additionalneeds\" : \"Breakfast\"\n" +
            "}";

    public static String updateFirstName = "James";

    public static String updateLastName = "Bond";

    public static String partialUpdateBooking = "{\n" +
            "    \"firstname\" : \"" + updateFirstName + "\",\n" +
            "    \"lastname\" : \"" + updateLastName + "\"\n" +
            "}";

}
