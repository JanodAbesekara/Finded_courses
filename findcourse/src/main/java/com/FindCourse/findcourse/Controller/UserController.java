package com.FindCourse.findcourse.Controller;


import com.FindCourse.findcourse.Model.FeedBacks;
import com.FindCourse.findcourse.Services.UserServices;
import com.FindCourse.findcourse.dto.AddFeedBacksDTO;
import com.FindCourse.findcourse.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "api/V1")
@CrossOrigin
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
