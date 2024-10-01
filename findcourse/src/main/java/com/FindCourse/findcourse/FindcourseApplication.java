package com.FindCourse.findcourse;

import com.FindCourse.findcourse.Services.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.security.Principal;

@SpringBootApplication
public class FindcourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindcourseApplication.class, args);
	}


}
