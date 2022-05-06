package com.hunglp.spring_rest_control_advice_exception.controller;


import com.hunglp.spring_rest_control_advice_exception.exception.ResourceNotFoundException;
import com.hunglp.spring_rest_control_advice_exception.model.Tutorial;
import com.hunglp.spring_rest_control_advice_exception.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {

        List<Tutorial> tutorials = null;
        if (title == null) {
            tutorials = new ArrayList<>(tutorialRepository.findAll());
        } else {
            tutorials = new ArrayList<>(tutorialRepository.findByTitleContaining(title));
        }

        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") Long id) {
        Tutorial tutorial = tutorialRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        return new ResponseEntity<>(tutorial, HttpStatus.OK);
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        Tutorial existTutorial = tutorialRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        existTutorial.setTitle(tutorial.getTitle());
        existTutorial.setDescription(tutorial.getDescription());
        existTutorial.setPublished(tutorial.isPublished());

        return new ResponseEntity<>(tutorialRepository.save(existTutorial), HttpStatus.OK);
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        Tutorial tutorialNew = tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished()));
        return new ResponseEntity<>(tutorialNew, HttpStatus.CREATED);
    }

    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        tutorialRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") Long id) {
        tutorialRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        List<Tutorial> tutorials = tutorialRepository.findByPublished(true);
        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }
}
