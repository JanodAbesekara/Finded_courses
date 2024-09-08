package com.FindCourse.findcourse;

import com.FindCourse.findcourse.Services.UserServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Principal;

@SpringBootApplication
public class FindcourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindcourseApplication.class, args);
	}


	public UserServices saveuser(Principal principal) {

		return null;
	}
}
