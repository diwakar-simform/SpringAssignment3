package com.springrest.springrest.controller;

import com.springrest.springrest.entity.Course;
import com.springrest.springrest.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {


    @Autowired
    private CourseService service;

    @GetMapping()
    public List<Course> getAllCourses(){
        return this.service.getAllCourses();
    }

    @GetMapping("/{courseId}")
    public Optional<Course> getCourseById(@PathVariable String courseId) {
        return this.service.getCourseById(Long.parseLong(courseId));
    }

    @PostMapping()
    public Course addCourse(@RequestBody Course course){
        return this.service.addCourse(course);
    }

    @PutMapping()
    public Course updateCourse(@RequestBody Course course){
        return this.service.updateCourse(course);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId){
        try{
            this.service.deleteCourse(Long.parseLong(courseId));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
