package com.hitesh.springboottemplate.services;

import com.hitesh.springboottemplate.modals.Courses;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
public interface CourseInterface {
    public List<Courses> getCourses();
    public Courses getCourseByID(Long courseId);

    public Courses addCourse(@RequestBody Courses course);
}