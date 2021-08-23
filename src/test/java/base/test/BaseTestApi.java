package base.test;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import pojo.Bookingdates;
import pojo.CreateBookingPojo;
import pojo.CreateTokenPojo;
import pojo.PartialUpdateBookingPojo;
import specifications.RequestSpec;

import static constants.Endpoints.LOGIN;
import static constants.Endpoints.PASSWORD;

public class BaseTestApi {
    private static Bookingdates bookingdates = new Bookingdates("2018-01-01", "2019-01-01");
    public static CreateBookingPojo createBookingPojo = new CreateBookingPojo("Jim", "Brown"
                                        , 111,true, bookingdates, "Breakfast" );
    public static CreateTokenPojo createTokenPojo = new CreateTokenPojo(LOGIN, PASSWORD);
    public static PartialUpdateBookingPojo partialUpdateBookingPojo = new PartialUpdateBookingPojo("James", "Bond");
    public int id;
    public String token;

    @BeforeSuite
    public void setup() {
        RestAssured.requestSpecification = RequestSpec.requestSpec;
    }
}
