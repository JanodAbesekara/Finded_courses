package com.FindCourse.findcourse.Controller;

import com.FindCourse.findcourse.Model.FeedBacks;
import com.FindCourse.findcourse.Services.UserServices;
import com.FindCourse.findcourse.dto.AddFeedBacksDTO;
import com.FindCourse.findcourse.dto.UserDTO;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;



//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping(value = "api/V1")
@RestController
public class UserController {

    @Autowired
    private UserServices userServices;



    // Add feedback for a user
    @PostMapping("/addFeedback")
    public FeedBacks addFeedback(@RequestBody AddFeedBacksDTO feedbackDTO) {
        return userServices.saveFeedback(feedbackDTO.getUseremail(), feedbackDTO.getFeedback());
    }

    @GetMapping("/getuserdetails")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email) {
        return userServices.getUserByEmail(email);
    }
}
