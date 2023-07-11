package com.hitesh.springboottemplate.controller;

import com.hitesh.springboottemplate.modals.Courses;
import com.hitesh.springboottemplate.services.CourseInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableWebSecurity
@RequestMapping("/home")
public class MyController {

    Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    private CourseInterface courseInterface;
    @GetMapping("/GetCourses")
    private List<Courses> getCourse(){
        this.logger.warn("This is working message");
        return this.courseInterface.getCourses();
    }

    @GetMapping("/GetCourses/{courseID}")
    private Courses getCourseById(@PathVariable String courseID){
        return this.courseInterface.getCourseByID(Long.parseLong(courseID));
    }

    @PostMapping("/GetCourses")
    private Courses addCourse( @RequestBody Courses course ){
        return this.courseInterface.addCourse(course);
    }
}
