package com.FindCourse.findcourse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTOGET {

    private String useremail;
    private String feedback;




    public String getUseremail() {
        return useremail;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setUseremail( String useremail) {
        this.useremail = useremail;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
