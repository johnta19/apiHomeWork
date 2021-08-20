
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class RestAssuredRequests {


    private int id;
    private String token;


    @BeforeSuite
    public static void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void postRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(DataBody.requestBody)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract().response();
        token =  response.jsonPath().getString("token");
    }

    @Test(dependsOnMethods = "postRequest")
    public void createBooking() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(DataBody.createBookingBody)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract().response();
        id = response.jsonPath().getInt("bookingid");
    }

    @Test(dependsOnMethods = "createBooking")
    public void getBooking() {
        given()
                .header("Content-type", "application/json")
                .when()
                .get("/booking/" + id)
                .then()
                .assertThat().body("firstname", equalTo(DataBody.firstname), "lastname"
                , equalTo(DataBody.lastname), "totalprice", equalTo(DataBody.totalprice),
                "depositpaid", equalTo(DataBody.depositpaid));
//                .body("firstname", equalTo(DataBody.firstname))
//                .and()
//                .body("lastname", equalTo(DataBody.lastname))
//                .and()
//                .body("totalprice", equalTo(DataBody.totalprice))
//                .and()
//                .body("depositpaid", equalTo(DataBody.depositpaid));
    }

    @Test(dependsOnMethods = "getBooking")
    public void updateBooking() {
        given()
                .header("Content-type", "application/json")
                .and()
                .header("Cookie", "token=" + token)
                .body(DataBody.partialUpdateBooking)
                .when()
                .patch("/booking/" + id)
                .then()
                .assertThat()
                .body("firstname", equalTo(DataBody.updateFirstName)
                        , "lastname", equalTo(DataBody.updateLastName));
    }

    @Test(dependsOnMethods = "updateBooking" )
    public void deleteBooking() {
        given()
                .header("Content-type", "application/json")
                .and()
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + id)
                .then()
                .statusCode(201);
    }
}