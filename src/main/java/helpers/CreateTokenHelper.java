package helpers;

import pojo.CreateTokenPojo;

import static constants.Endpoints.LOGIN;
import static constants.Endpoints.PASSWORD;

public class CreateTokenHelper {
    public static CreateTokenPojo createTokenPojo = new CreateTokenPojo();


    public static void setCreateTokenPojo() {
        createTokenPojo.setUsername(LOGIN);
        createTokenPojo.setPassword(PASSWORD);
    }
}
