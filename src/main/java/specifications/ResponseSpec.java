package specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpec {
    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification responseSpecDeleteBooking = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();

}
