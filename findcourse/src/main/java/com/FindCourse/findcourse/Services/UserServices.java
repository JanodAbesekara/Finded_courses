package com.FindCourse.findcourse.Services;


import com.FindCourse.findcourse.Model.FeedBacks;
import com.FindCourse.findcourse.dto.AddFeedBacksDTO;
import com.FindCourse.findcourse.dto.UserDTO;
import com.FindCourse.findcourse.Model.User;
import com.FindCourse.findcourse.repo.FeedRepo;
import com.FindCourse.findcourse.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedRepo feedRepo;

    @Autowired
    private ModelMapper modelMapper;



    public List<AddFeedBacksDTO> getallFeedbacks() {
        List<FeedBacks> feedbacklist = feedRepo.findAll();
        return modelMapper.map(feedbacklist, new TypeToken<List<AddFeedBacksDTO>>(){}.getType());
    }


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


    public FeedBacks saveFeedback(String userEmail, String feedbackContent) {
        // Find the user by email
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + userEmail));
        // Create and set feedback
        FeedBacks feedback = new FeedBacks();
        feedback.setFeedback(feedbackContent);
        feedback.setUser(user);

        // Save feedback
        FeedBacks savedFeedback = feedRepo.save(feedback);


        return savedFeedback;
    }


    public ResponseEntity<List<AddFeedBacksDTO>> getAllFeedbacks() {
        // Fetch all feedbacks from the repository
        List<FeedBacks> feedbacks = feedRepo.findAll();

        // Map each FeedBacks object to AddFeedBacksDTO
        List<AddFeedBacksDTO> feedbackDTOs = feedbacks.stream()
                .map(feedback -> new AddFeedBacksDTO(feedback.getUser().getEmail(), feedback.getFeedback(), feedback.getId()))
                .collect(Collectors.toList());

        // Return the list of feedback DTOs as a ResponseEntity
        return ResponseEntity.ok(feedbackDTOs);
    }


    public ResponseEntity<AddFeedBacksDTO> deleteFeedback(int id) {
        // Find the feedback by ID
        Optional<FeedBacks> feedback = feedRepo.findById(id);

        if (feedback.isPresent()) {
            FeedBacks foundFeedback = feedback.get();
            feedRepo.delete(foundFeedback);  // Delete the feedback

            return ResponseEntity.ok(new AddFeedBacksDTO(foundFeedback.getUser().getEmail(), foundFeedback.getFeedback(), foundFeedback.getId()));

        } else {

            return ResponseEntity.notFound().build();  // Return 404 if the feedback is not found
        }
    }


    public ResponseEntity<UserDTO> getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            User foundUser = user.get();

            // Create UserDTO with only the necessary fields
            UserDTO userDTO = new UserDTO(
                    foundUser.getEmail(),
                    foundUser.getFirstName(),
                    foundUser.getLastName(),
                    foundUser.getPassword(),
                    foundUser.getRole()  // Role is optional
            );


            return ResponseEntity.ok(userDTO);  // Return 200 OK with the user DTO

        } else {

            return ResponseEntity.notFound().build();  // Return 404 if the user is not found
        }
    }

    public ResponseEntity<List<AddFeedBacksDTO>> getOnlyUsersFeedbacks(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            User foundUser = user.get();
            List<FeedBacks> feedbacks = feedRepo.findByUser(foundUser);
            List<AddFeedBacksDTO> feedbackDTOs = feedbacks.stream()
                    .map(feedback -> new AddFeedBacksDTO(feedback.getUser().getEmail(), feedback.getFeedback(), feedback.getId()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(feedbackDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<AddFeedBacksDTO> deleteAllFeedbacks( int id) {
        feedRepo.delete(feedRepo.getReferenceById(id));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<AddFeedBacksDTO> updateFeedback(AddFeedBacksDTO addFeedBacksDTO) {
        FeedBacks feedback = feedRepo.getReferenceById(addFeedBacksDTO.getId());
        feedback.setFeedback(addFeedBacksDTO.getFeedback());
        feedRepo.save(feedback);
        return ResponseEntity.ok(addFeedBacksDTO);
    }

    public ResponseEntity<UserDTO> updateAdmin(String email) {
        User user = userRepository.findByEmail(email).get();
        user.setRole("ADMIN");
        userRepository.save(user);
        System.out.println("User updated with ID: " + user.getId());
        return ResponseEntity.ok().build();
    }
}
