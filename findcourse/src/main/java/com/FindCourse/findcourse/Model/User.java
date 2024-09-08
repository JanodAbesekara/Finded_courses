package com.FindCourse.findcourse.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;

    @PrePersist
    protected void prePersist() {
        if (this.role == null) {
            this.role = "Student";
        }
    }
}
