import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static constants.Endpoints.*;
import static helpers.CreateBookingHelper.*;
import static helpers.CreateTokenHelper.*;
import static helpers.PartialUpdateBookingHelper.partialUpdateBookingPojo;
import static helpers.PartialUpdateBookingHelper.setPartialUpdateBookingPojo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredRequests {


    private int id;
    private String token;


    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void createToken() {
        setCreateTokenPojo();
        Response response = given()
                .contentType(ContentType.JSON)
                .and()
                .body(createTokenPojo)
                .when()
                .post(POST_REQUEST)
                .then()
                .statusCode(200)
                .extract().response();
        token =  response.jsonPath().getString("token");

    }

    @Test(dependsOnMethods = "createToken")
    public void createBooking() {
        setCreateBookingPojo();
        setBookingdates();
        Response response = given()
                .contentType(ContentType.JSON)
                .and()
                .body(createBookingPojo)
                .when()
                .post(CREATE_BOOKING)
                .then()
                .statusCode(200)
                .extract().response();
        id = response.jsonPath().getInt("bookingid");
    }

    @Test(dependsOnMethods = "createBooking")
    public void getBooking() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(GET_BOOKING + id)
                .then()
//        myAssert();
                .assertThat()
                .body("firstname", equalTo("Jim")
                        , "lastname", equalTo("Brown")
                        , "totalprice", equalTo(111)
                        , "depositpaid", equalTo(true));
    }

    @Test(dependsOnMethods = "getBooking")
    public void partialUpdateBooking() {
        setPartialUpdateBookingPojo();
        given()
                .contentType(ContentType.JSON)
                .and()
                .header("Cookie", "token=" + token)
                .body(partialUpdateBookingPojo)
                .when()
                .patch(UPDATE_BOOKING + id)
                .then()
                .assertThat()
                .body("firstname", equalTo("James")
                        , "lastname", equalTo("Bond"));
    }

    @Test(dependsOnMethods = "partialUpdateBooking" )
    public void deleteBooking() {
        given()
                .contentType(ContentType.JSON)
                .and()
                .header("Cookie", "token=" + token)
                .when()
                .delete(DELETE_BOOKING + id)
                .then()
                .statusCode(201);
    }
}