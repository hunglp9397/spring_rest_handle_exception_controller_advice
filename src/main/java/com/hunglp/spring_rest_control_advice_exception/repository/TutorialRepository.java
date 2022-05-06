package com.hunglp.spring_rest_control_advice_exception.repository;

import com.hunglp.spring_rest_control_advice_exception.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);

}
