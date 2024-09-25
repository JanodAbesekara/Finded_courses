package com.FindCourse.findcourse.Controller;

import com.FindCourse.findcourse.Services.UserServices;
import com.FindCourse.findcourse.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;
import java.util.Map;


@RestController
public class GouthController {

    @Autowired
    private UserServices userServices;

    // This endpoint will handle both user info retrieval and saving
    @GetMapping("/User-info")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        // Get user attributes from the OAuth2 principal
        Map<String, Object> attributes = principal.getAttributes();

        // Extract relevant user info
        String email = (String) attributes.get("email");
        String firstName = (String) attributes.get("given_name");
        String lastName = (String) attributes.get("family_name");
        String password = (String) attributes.get("nonce");  // Example field, not real password

        // Create a UserDTO object and save to database
        UserDTO userDTO = new UserDTO(email, firstName, lastName, password);
        userServices.saveUser(userDTO);

        // Return the user's attributes to the frontend
        return attributes;
    }

    // You can remove this method or keep it for future use
    @GetMapping("/gouth")
    public Principal user(Principal principal) {
        return principal;  // This will simply return the principal without further processing
    }
}
