package com.FindCourse.findcourse.repo;

import com.FindCourse.findcourse.Model.FeedBacks;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface FeedRepo  extends JpaRepository<FeedBacks , Integer> {

}
