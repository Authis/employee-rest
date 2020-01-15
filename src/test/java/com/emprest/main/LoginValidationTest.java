package com.emprest.main;

  
 import com.emprest.main.EmpLogin
 import com.emprest.main.LoginValidation;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import org.junit.Before;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.mockito.InjectMocks;
 import org.mockito.Mock;
 import org.mockito.Mockito;
 import org.mockito.MockitoAnnotations;
 import org.springframework.http.MediaType;
 import org.springframework.stereotype.Controller;
 import org.springframework.test.context.junit4.SpringRunner;
 import org.springframework.test.web.servlet.MockMvc;
 import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 import org.springframework.web.bind.annotation.*;

 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class LoginValidationTest {

    private MockMvc mockMvc;

    @InjectMocks
    private LoginValidation loginValidation;

    @Mock
    EmpLogin empLogin;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(loginValidation).build();

        Mockito.when(empLogin.getUsername())
                .thenReturn("admin");
        Mockito.when(empLogin.getPassword())
                .thenReturn("admin");
    }

    @Test
    public void returnsSuccessWithValidRequestParams() throws Exception {

        this.mockMvc.perform(post("/validateLogon")
                .content(asJsonString (getEmpDetails()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            String objs = new ObjectMapper().writeValueAsString(obj);
            System.out.println("Vendor Request"+objs);
            return objs;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    private EmpLogin getEmpDetails() {
        EmpLogin empLogin = new EmpLogin();
        empLogin.setUsername("admin");
        empLogin.setPassword("admin");
        return  empLogin;
    }


}
