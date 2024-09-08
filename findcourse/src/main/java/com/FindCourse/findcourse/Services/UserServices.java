package com.FindCourse.findcourse.Services;

import com.FindCourse.findcourse.dto.UserDTO;
import com.FindCourse.findcourse.Model.User;
import com.FindCourse.findcourse.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    // Save user if it doesn't already exist
    public void saveUser(UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());

        if (existingUser.isEmpty()) {
            User user = new User();
            user.setEmail(userDTO.getEmail());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPassword(userDTO.getPassword()); // Only for demonstration; usually hashed

            userRepository.save(user);
        }
    }

    public void processOAuthPostLogin(OAuth2AuthenticationToken token) {
        String email = token.getPrincipal().getAttribute("email");
        String firstName = token.getPrincipal().getAttribute("given_name");
        String lastName = token.getPrincipal().getAttribute("family_name");
        String password = token.getPrincipal().getAttribute("name");
        UserDTO userDTO = new UserDTO(email, firstName, lastName, password);
        saveUser(userDTO);

    }
}
