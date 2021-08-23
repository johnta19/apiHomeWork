import base.test.BaseTestApi;
import data.provider.Builder;
import data.provider.DataProvider;
import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static constants.Endpoints.PATH_TO_BOOKING;
import static constants.Endpoints.POST_REQUEST;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static specifications.ResponseSpec.*;


public class RestAssuredRequests extends BaseTestApi {

    private DataProvider dataProvider = new DataProvider();
    private Builder builder = new Builder();


    @BeforeTest
    public void createToken() {
        Response response = given()
                .body(builder.createToken())
                .when()
                .post(POST_REQUEST)
                .then()
                .spec(checkStatus200)
                .extract().response();
        dataProvider.setToken(response.jsonPath().getString("token"));

    }

    @Test
    public void createBooking() {
        Response response = given()
                .body(builder.createBooking())
                .when()
                .post(PATH_TO_BOOKING)
                .then()
                .spec(checkStatus200)
                .extract().response();
        dataProvider.setId(response.jsonPath().getInt("bookingid"));
    }

    @Test
    public void getBooking() {
        given()
                .when()
                .get(PATH_TO_BOOKING + dataProvider.getId())
                .then()
                .spec(checkStatus200)
                .assertThat()
                .body("firstname", equalTo("Jim")
                        , "lastname", equalTo("Brown")
                        , "totalprice", equalTo(111)
                        , "depositpaid", equalTo(true)).extract().response();
    }


    @Test
    public void partialUpdateBooking() {
        given()
                .header("Cookie", "token=" + dataProvider.getToken())
                .body(builder.updateAuthor())
                .when()
                .patch(PATH_TO_BOOKING + dataProvider.getId())
                .then()
                .spec(checkStatus200)
                .assertThat()
                .body("firstname", equalTo("James"), "lastname", equalTo("Bond"));
    }

    @AfterTest
    public void deleteBooking() {
        given()
                .header("Cookie", "token=" + dataProvider.getToken())
                .when()
                .delete(PATH_TO_BOOKING + dataProvider.getId())
                .then()
                .spec(checkStatus201);
    }
}