package com.FindCourse.findcourse.dto;

public class AddFeedBacksDTO {
    private String useremail;
    private String feedback;

    public AddFeedBacksDTO(String useremail, String feedback) {
        this.useremail = useremail;
        this.feedback = feedback;
    }

    public String getUseremail() {
        return useremail;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setUseremail(Long userId) {
        this.useremail = useremail;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
