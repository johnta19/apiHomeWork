import base.test.BaseTestApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static constants.Endpoints.PATH_TO_BOOKING;
import static constants.Endpoints.POST_REQUEST;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static specifications.ResponseSpec.responseSpec;
import static specifications.ResponseSpec.responseSpecDeleteBooking;


public class RestAssuredRequests extends BaseTestApi {

    @Test
    public void createToken() {
        Response response = given()
                .body(createTokenPojo)
                .when()
                .post(POST_REQUEST)
                .then()
                .spec(responseSpec)
                .extract().response();
        token = response.jsonPath().getString("token");

    }

    @Test(dependsOnMethods = "createToken")
    public void createBooking() {
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
        given()
                .when()
                .get(PATH_TO_BOOKING + id)
                .then()
                .spec(responseSpec)
                .assertThat()
                .body("firstname", equalTo("Jim")
                        , "lastname", equalTo("Brown")
                        , "totalprice", equalTo(111)
                        , "depositpaid", equalTo(true)).extract().response();
    }

    @Test(dependsOnMethods = "getBooking")
    public void partialUpdateBooking() {
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

    @Test(dependsOnMethods = "partialUpdateBooking")
    public void deleteBooking() {
        given()
                .header("Cookie", "token=" + token)
                .when()
                .delete(PATH_TO_BOOKING + id)
                .then()
                .spec(responseSpecDeleteBooking);
    }
}