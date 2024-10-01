package com.FindCourse.findcourse.repo;

import com.FindCourse.findcourse.Model.FeedBacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface FeedRepo  extends JpaRepository<FeedBacks , Integer> {


}
