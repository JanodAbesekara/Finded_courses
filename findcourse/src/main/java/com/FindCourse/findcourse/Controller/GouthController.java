package com.FindCourse.findcourse.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController

public class GouthController {
@GetMapping("/")
    public String getGouth(){
    return  "Welcome to SpringBoot page";
    }

    @RequestMapping("/gouth")
    public Principal user(Principal user){
        return user;
    }
}
