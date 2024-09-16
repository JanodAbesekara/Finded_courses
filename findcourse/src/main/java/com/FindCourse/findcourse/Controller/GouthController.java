package com.FindCourse.findcourse.Controller;

import com.FindCourse.findcourse.Services.UserServices;
import com.FindCourse.findcourse.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;
import java.util.Map;

@RestController
public class GouthController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/")
    public String getGouth() {
        return "Welcome to SpringBoot page";
    }

    @GetMapping("/gouth")
    public Principal user(Principal principal) {
        if (principal instanceof OAuth2AuthenticationToken authToken) {
            Map<String, Object> attributes = authToken.getPrincipal().getAttributes();

            String email = (String) attributes.get("email");
            String firstName = (String) attributes.get("given_name");
            String lastName = (String) attributes.get("family_name");
            String password = (String) attributes.get("nonce");  // Only for testing, not real password

            UserDTO userDTO = new UserDTO(email, firstName, lastName, password);
            userServices.saveUser(userDTO);
        }
        return principal;
    }


}
