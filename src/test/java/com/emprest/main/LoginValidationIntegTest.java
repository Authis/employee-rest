package com.emprest.main;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.contains;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginValidationIntegTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        RestAssuredMockMvc.webAppContextSetup(this.webApplicationContext);
    }

    @Test
    public void myFirstRestAssuredTest()
    {

        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body("{  \"username\": \"admin\", \"password\": \"admin\" }").
                when().
                post("http://localhost:"+port+"/validateLogon").
                then().
                contentType(ContentType.JSON).
                //body(Matchers.contains("SUCCESS"));
                        body("statusMsg", equalTo("SUCCESS_SUCCESS"));

    }
}
