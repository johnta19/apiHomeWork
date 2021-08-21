package base.test;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import specifications.RequestSpec;

public class BaseTestApi {
    @BeforeSuite
    public void setup() {
        RestAssured.requestSpecification = RequestSpec.requestSpec;
    }
}
