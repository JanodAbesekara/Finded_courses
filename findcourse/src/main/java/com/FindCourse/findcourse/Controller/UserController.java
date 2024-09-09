package com.FindCourse.findcourse.Controller;


import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "api/V1/")
@CrossOrigin
@RestController
public class UserController {
    @GetMapping("/user")
    public String getUser() {
        return "Welcome to SpringBoot page";
    }

    @PostMapping("/users")
    public String createUser() {
        return "User created";
    }

}
