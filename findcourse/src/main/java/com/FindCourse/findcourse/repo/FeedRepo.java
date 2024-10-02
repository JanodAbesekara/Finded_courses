package com.FindCourse.findcourse.repo;

import com.FindCourse.findcourse.Model.FeedBacks;
import com.FindCourse.findcourse.Model.User;
import com.FindCourse.findcourse.dto.AddFeedBacksDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface FeedRepo  extends JpaRepository<FeedBacks , Integer> {

    // Optional<FeedBacks> findByEmail(String email);

    List<FeedBacks> findByUser(User foundUser);


}
