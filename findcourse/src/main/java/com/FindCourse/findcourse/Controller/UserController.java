package com.FindCourse.findcourse.Controller;

import com.FindCourse.findcourse.Model.FeedBacks;
import com.FindCourse.findcourse.Services.UserServices;
import com.FindCourse.findcourse.dto.AddFeedBacksDTO;
import com.FindCourse.findcourse.dto.FeedbackDTOGET;
import com.FindCourse.findcourse.dto.UserDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping(value = "api/V1")
@RestController
public class UserController {

    @Autowired
    private UserServices userServices;



    // Add feedback for a user
    @PostMapping("/addFeedback")
    public FeedBacks addFeedback(@RequestBody FeedbackDTOGET feedbackDTO) {
        return userServices.saveFeedback(feedbackDTO.getUseremail(), feedbackDTO.getFeedback());
    }

    @GetMapping("/getuserdetails")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email) {
        return userServices.getUserByEmail(email);
    }

    @GetMapping("/getallfeedbacks")
    public ResponseEntity<List<AddFeedBacksDTO>> getAllFeedbacks() {
        return userServices.getAllFeedbacks();
    }

    @DeleteMapping("/deletefeedback")
    public ResponseEntity<AddFeedBacksDTO> deleteFeedback(@RequestParam int id) {
        return userServices.deleteFeedback(id);
    }

    @GetMapping("/getonlyusersfeedbacks")
    public ResponseEntity<List<AddFeedBacksDTO>> getOnlyUsersFeedbacks(@RequestParam String email) {
        return userServices.getOnlyUsersFeedbacks(email);
    }

    @DeleteMapping("/deleteallfeedbacks")
    public ResponseEntity<AddFeedBacksDTO> deleteAllFeedbacks(@RequestParam int id) {
        return userServices.deleteAllFeedbacks(id);
    }

    @PutMapping("/updatefeedback")
    public ResponseEntity<AddFeedBacksDTO> updateFeedback(@RequestBody AddFeedBacksDTO addFeedBacksDTO) {
        return userServices.updateFeedback(addFeedBacksDTO);
    }

    @PostMapping("/UpdateAdmin")
    public ResponseEntity<UserDTO> updateAdmin(@RequestParam String email) {
        return userServices.updateAdmin(email);
    }
}
