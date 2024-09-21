package com.FindCourse.findcourse.Services;

import com.FindCourse.findcourse.Model.FeedBacks;
import com.FindCourse.findcourse.dto.UserDTO;
import com.FindCourse.findcourse.Model.User;
import com.FindCourse.findcourse.repo.FeedRepo;
import com.FindCourse.findcourse.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedRepo feedRepo;




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

    @Transactional
    public FeedBacks saveFeedback(String userEmail, String feedbackContent) {
        // Find the user by email
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + userEmail));

        System.out.println("User found with ID: " + user.getId());

        // Create and set feedback
        FeedBacks feedback = new FeedBacks();
        feedback.setFeedback(feedbackContent);
        feedback.setUser(user);

        // Save feedback
        FeedBacks savedFeedback = feedRepo.save(feedback);
        System.out.println("Feedback saved with ID: " + savedFeedback.getId());

        return savedFeedback;
    }


    @Transactional
    public ResponseEntity<User> getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            System.out.println("User found with ID: " + user.get().getId());
            return ResponseEntity.ok(user.get());  // Return 200 OK with the user details
        } else {
            System.out.println("User not found with email: " + email);
            return ResponseEntity.notFound().build();  // Return 404 if the user is not found
        }
    }

}
