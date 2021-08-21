package specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static constants.Endpoints.BASE_URL;

public class RequestSpec {
    public static RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();




}
