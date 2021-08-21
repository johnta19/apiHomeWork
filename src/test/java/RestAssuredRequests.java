import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import specifications.RequestSpec;

import static constants.Endpoints.*;
import static helpers.CreateBookingHelper.*;
import static helpers.CreateTokenHelper.*;
import static helpers.PartialUpdateBookingHelper.partialUpdateBookingPojo;
import static helpers.PartialUpdateBookingHelper.setPartialUpdateBookingPojo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static specifications.ResponseSpec.responseSpec;
import static specifications.ResponseSpec.responseSpecDeleteBooking;

public class RestAssuredRequests {


    private int id;
    private String token;


    @BeforeSuite
    public void setup() {
        RestAssured.requestSpecification = RequestSpec.requestSpec;
    }

    @Test
    public void createToken() {
        setCreateTokenPojo();
        Response response = given()
                .body(createTokenPojo)
                .when()
                .post(POST_REQUEST)
                .then()
                .spec(responseSpec)
                .extract().response();
        token =  response.jsonPath().getString("token");

    }

    @Test(dependsOnMethods = "createToken")
    public void createBooking() {
        setCreateBookingPojo();
        setBookingdates();
        Response response = given()
                .body(createBookingPojo)
                .when()
                .post(PATH_TO_BOOKING)
                .then()
                .spec(responseSpec)
                .extract().response();
        id = response.jsonPath().getInt("bookingid");
    }

    @Test(dependsOnMethods = "createBooking")
    public void getBooking() {
        Response response = given()
                .when()
                .get(PATH_TO_BOOKING + id)
                .then()
                .spec(responseSpec)
//        myAssert();
                .assertThat()
                .body("firstname", equalTo("Jim")
                        , "lastname", equalTo("Brown")
                        , "totalprice", equalTo(111)
                        , "depositpaid", equalTo(true)).extract().response();
    }

    @Test(dependsOnMethods = "getBooking")
    public void partialUpdateBooking() {
        setPartialUpdateBookingPojo();
        given()
                .header("Cookie", "token=" + token)
                .body(partialUpdateBookingPojo)
                .when()
                .patch(PATH_TO_BOOKING + id)
                .then()
                .spec(responseSpec)
                .assertThat()
                .body("firstname", equalTo("James")
                        , "lastname", equalTo("Bond"));
    }

    @Test(dependsOnMethods = "partialUpdateBooking" )
    public void deleteBooking() {
        given()
                .header("Cookie", "token=" + token)
                .when()
                .delete(PATH_TO_BOOKING + id)
                .then()
                .spec(responseSpecDeleteBooking);
    }
}