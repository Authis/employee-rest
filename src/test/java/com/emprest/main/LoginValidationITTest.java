package com.emprest.main;

 import com.emprest.main.EmpLogin;
 import com.emprest.SpringBootRestApplication;
 import org.junit.Before;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.context.SpringBootTest;
 import org.springframework.boot.test.web.client.TestRestTemplate;
 import org.springframework.boot.web.server.LocalServerPort;
 import org.springframework.http.*;
 import org.springframework.stereotype.Controller;
 import org.springframework.test.context.junit4.SpringRunner;
 import org.springframework.web.bind.annotation.*;

 import java.net.URL;
 import java.util.Arrays;

 import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootRestApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginValidationITTest {

    @LocalServerPort
    private int port;

    private URL baseUrl;

    private static final String PATH_GET_SCORE = "/validateLogon";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private EmpLogin empLogin;

    @Before
    public void setUp() throws Exception {
        this.baseUrl = new URL("http://localhost:" + this.port + PATH_GET_SCORE);
    }

    @Test
    public void testGetApiResponse_WithValidInput_ShouldReturnValidResponse() throws Exception {
        String endpoint = this.baseUrl.toString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


        empLogin.setUsername("admin");
        empLogin.setPassword("admin");
        HttpEntity<EmpLogin> request = new HttpEntity<>(empLogin, headers);

        ResponseEntity<String> response = testRestTemplate.postForEntity(endpoint, empLogin, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
    }
}
