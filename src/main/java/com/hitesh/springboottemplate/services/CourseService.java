package com.hitesh.springboottemplate.services;

import com.hitesh.springboottemplate.modals.Courses;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CourseService implements CourseInterface{

    private List<Courses> list;
    public CourseService() {
       list = new ArrayList<>();
       list.add( new Courses(1, "Core java","Start Learning java"));
       list.add( new Courses(2,"Sprint Boot","Start Learning sprint boot"));

    }

    @Override
    public List<Courses> getCourses() {
        return list;
    }

    @Override
    public Courses getCourseByID(Long courseId) {
        Courses c = null;
        for(Courses course:list){
            if(course.getId() == courseId){
                c= course;
                break;
            }
        }
        return c;
    }

    @Override
    public Courses addCourse(Courses course) {
        list.add(course);
        return course;
    }
}
