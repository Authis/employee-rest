package com.emprest.main;

import com.jayway.restassured.http.ContentType;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginValidationIntegTest {


    @Test
    public void myFirstRestAssuredTest()
    {

        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body("{  \"username\": \"admin\", \"password\": \"admin\" }").
                when().
                post("http://localhost:5002/validateLogon").
                then().
                contentType(ContentType.JSON).
                body("statusMsg", equalTo("SUCCESS"));
    }
}
