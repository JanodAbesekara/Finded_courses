package com.FindCourse.findcourse.dto;

public class AddFeedBacksDTO {
    private String useremail;
    private String feedback;

    private int id;

    public AddFeedBacksDTO(String useremail, String feedback , int id) {
        this.useremail = useremail;
        this.feedback = feedback;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
