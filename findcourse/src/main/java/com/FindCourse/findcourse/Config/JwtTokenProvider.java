//package com.FindCourse.findcourse.Config;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtTokenProvider {
//
//    private final String SECRET_KEY = "your_secret_key"; // Replace with a secure key
//
//    public String generateToken(String email) {
//        return JWT.create()
//                .withSubject(email)
//                .withIssuedAt(new Date())
//                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiration
//                .sign(Algorithm.HMAC256(SECRET_KEY));
//    }
//}
