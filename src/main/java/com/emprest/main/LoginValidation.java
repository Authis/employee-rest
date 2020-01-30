package com.emprest.main;

 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
public class LoginValidation {

    @RequestMapping(value = "/validateLogon" ,method = RequestMethod.POST,produces = "application/json", consumes = "application/json")
    @ResponseBody
    public Message welcomeUser(@RequestBody(required=true) EmpLogin empLogin) {
        Message message = new Message();

        if(empLogin.getUsername().equalsIgnoreCase("admin") && empLogin.getPassword().equalsIgnoreCase("admin")){

            message.setStatusMsg("SUCCESS");
        }else{
            message.setStatusMsg("FAILURE");
        }


        System.out.println("empLogin : >>>>>>"+empLogin.getUsername());
        System.out.println("message : >>>>>>"+message.getStatusMsg());

        return message;
    }
}
